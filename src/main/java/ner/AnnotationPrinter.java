package ner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ner.types.NameAnnotation;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

public class AnnotationPrinter extends CasConsumer_ImplBase {
  
  BufferedWriter output;

  @Override
  public void initialize() throws ResourceInitializationException {
    String outputFile = (String) getConfigParameterValue("output");
    try {
      output = new BufferedWriter(new FileWriter(outputFile));
    } catch (IOException e) {
      throw new ResourceInitializationException(e);
    }
  }
  
  @Override
  public void processCas(CAS cas) throws ResourceProcessException {
    System.out.println("AnnotationPrinter.processCas()");
    JCas jcas;
    try {
      jcas = cas.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
   
    FSIterator<Annotation> it = jcas.getAnnotationIndex(NameAnnotation.type).iterator();
    while(it.hasNext()) {
      NameAnnotation annotation = (NameAnnotation) it.next();
      try {
        output.write(String.format("%s|%d %d|%s\n", annotation.getSentenceId(), 
                annotation.getBegin(), annotation.getEnd(), annotation.getName()));
      } catch (IOException e) {
        throw new ResourceProcessException(e);
      }
    }
  }
  
  @Override
  public void collectionProcessComplete(ProcessTrace pt) 
          throws ResourceProcessException, IOException {
    output.close();
  }

}