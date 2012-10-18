package ner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import ner.types.GeneAnnotation;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.apache.uima.util.ProcessTrace;

/**
 * Cas Consumer responsible for evaluation of the annotators. 
 */

public class AnnotationEvaluator extends CasConsumer_ImplBase {

  /**
   * Map of sentenceId -> gold annotations.
   * For every sentence with gold annotations, a mapping begin -> end is provided. 
   */
  private HashMap<String, HashMap<Integer, Integer>> goldAnnotations;

  /**
   * Count of true positives.
   */
  private int truePositives = 0;

  /**
   * Count of false positives.
   */
  private int falsePositives = 0;

  /**
   * Count of false negatives.
   */
  private int falseNegatives = 0;

  /**
   * Constructor for the class.
   */
  public AnnotationEvaluator() {
    goldAnnotations = new HashMap<String, HashMap<Integer, Integer>>();
  }

  /**
   * Initalizer for the CAS consumer.
   * Reads the gold annotation file if it is specified as a parameter in the descriptor.
   * 
   * @throws ResourceInitializationException if the annotation file cannot be read.
   */
  @Override
  public void initialize() throws ResourceInitializationException {
    String goldFile = (String) getConfigParameterValue("goldAnnotation");

    if(goldFile == null) {
      getUimaContext().getLogger().log(Level.INFO, "Gold annotations not available.");
      return;
    }

    try {
      BufferedReader gold = new BufferedReader(new FileReader(goldFile));
      Scanner scanner = new Scanner(gold);
      while(scanner.hasNextLine()) {
        scanner.useDelimiter(Pattern.compile("\\|| "));
        String sid = scanner.next();
        Integer begin = scanner.nextInt();
        Integer end = scanner.nextInt();
        scanner.nextLine();
        HashMap<Integer, Integer> sentenceAnnotations = goldAnnotations.get(sid);
        if(sentenceAnnotations == null) {
          sentenceAnnotations = new HashMap<Integer, Integer>();
          goldAnnotations.put(sid, sentenceAnnotations);
        }
        sentenceAnnotations.put(begin, end);
        falseNegatives++;
      }
    } catch (FileNotFoundException e) {
      throw new ResourceInitializationException(e);
    }
  }

  /**
   * Processes annotations of type {@link GeneAnnotation} in the CAS and collects sufficient statistics.
   * 
   * @param cas The CAS in which are stored the annotations.
   * @throws ResourceProcessException when the JCAS API cannot be obtained.
   */
  @Override
  public void processCas(CAS cas) throws ResourceProcessException {

    if(goldAnnotations.isEmpty()) return;

    JCas jcas;
    try {
      jcas = cas.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }

    FSIterator<Annotation> it = jcas.getAnnotationIndex(GeneAnnotation.type).iterator();
    while(it.hasNext()) {
      GeneAnnotation annotation = (GeneAnnotation) it.next();

      String sid = annotation.getSentenceId();
      int begin = annotation.getBegin(), end = annotation.getEnd();
      HashMap<Integer, Integer> sentenceAnnotations = goldAnnotations.get(sid);
      if(sentenceAnnotations != null &&
              sentenceAnnotations.containsKey(begin) && 
              sentenceAnnotations.get(begin) == end) {
        truePositives++;
        falseNegatives--;
      }
      else
        falsePositives++;
    }
  }

  /**
   * After the whole input has been processed, prints the evaluation results.
   * 
   * @param pt The process execution trace.
   */
  @Override
  public void collectionProcessComplete(ProcessTrace pt) 
          throws ResourceProcessException, IOException {

    if(goldAnnotations.isEmpty()) return;

    float precision = truePositives/((float) (truePositives + falsePositives));
    float recall = truePositives/((float) (truePositives + falseNegatives));
    float f1 = 2/(1/precision + 1/recall);

    Logger logger = getUimaContext().getLogger();

    logger.log(Level.INFO, "TP: {0} FP: {1} FN: {2}", new Object[]{truePositives, falsePositives, falseNegatives});
    logger.log(Level.INFO, "Precision: {0} Recall: {1} F1: {2}", new Object[]{precision, recall, f1});
  }

}