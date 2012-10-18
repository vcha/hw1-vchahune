package ner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ner.types.GeneAnnotation;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

/**
 * Cas Consumer responsible for outputing the annotations. 
 */
public class AnnotationPrinter extends CasConsumer_ImplBase {

  /**
   * The output file writer.
   */
  private BufferedWriter output;

  /**
   * Initializer for the CAS consumer.
   * Reads the output parameter from the descriptor and creates a file writer.
   * 
   * @throws ResourceInitializationException when the output file cannot be open for writing.
   */
  @Override
  public void initialize() throws ResourceInitializationException {
    String outputFile = (String) getConfigParameterValue("output");
    try {
      output = new BufferedWriter(new FileWriter(outputFile));
    } catch (IOException e) {
      throw new ResourceInitializationException(e);
    }
  }


  /**
   * Processes annotations of type {@link GeneAnnotation} in the CAS and writes them to the output file.
   * 
   * @param cas The CAS in which are stored the annotations.
   * @throws ResourceProcessException when the JCAS API cannot be obtained.
   * @throws ResourceProcessException when writing to the output file fails.
   */
  @Override
  public void processCas(CAS cas) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = cas.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }

    FSIterator<Annotation> it = jcas.getAnnotationIndex(GeneAnnotation.type).iterator();
    while(it.hasNext()) {
      GeneAnnotation annotation = (GeneAnnotation) it.next();
      try {
        output.write(String.format("%s|%d %d|%s\n", annotation.getSentenceId(), 
                annotation.getBegin(), annotation.getEnd(), annotation.getName()));
      } catch (IOException e) {
        throw new ResourceProcessException(e);
      }
    }
  }
  /**
   * After the entire input has been processed, closes the output file.
   * 
   * @param pt The process execution trace.
   */
  @Override
  public void collectionProcessComplete(ProcessTrace pt) 
          throws ResourceProcessException, IOException {
    output.close();
  }

}