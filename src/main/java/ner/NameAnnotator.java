package ner;

import java.util.Map;
import java.util.Map.Entry;

import ner.types.InputSentence;
import ner.types.NameAnnotation;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

public class NameAnnotator extends JCasAnnotator_ImplBase {
  
  private PosTagNamedEntityRecognizer recognizer;
  
  public NameAnnotator() {
    try {
      recognizer = new PosTagNamedEntityRecognizer();
    } catch (ResourceInitializationException e) {
      // TODO use logger
    }
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    FSIterator<Annotation> it = jcas.getAnnotationIndex(InputSentence.type).iterator();
    while(it.hasNext()) {
      InputSentence sentence = (InputSentence) it.next();
      Map<Integer, Integer> spans = recognizer.getGeneSpans(sentence.getText());
      for(Entry<Integer, Integer> entry: spans.entrySet()) {
        int begin = entry.getKey(), end = entry.getValue();
        NameAnnotation annotation = new NameAnnotation(jcas);
        annotation.setBegin(begin);
        annotation.setEnd(end);
        annotation.setSentenceId(sentence.getSentenceId());
        annotation.setName(sentence.getText().substring(begin, end));        
        annotation.addToIndexes();
      } 
    }
  }
}