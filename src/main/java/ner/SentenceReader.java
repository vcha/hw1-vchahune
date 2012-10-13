package ner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ner.types.InputSentence;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

public class SentenceReader extends CollectionReader_ImplBase {
  
  private Scanner scanner;
  private int n = 0;
  
  
  @Override
  public void initialize() throws ResourceInitializationException {
    String inputFile = (String) getConfigParameterValue("input");
    try {
      BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
      scanner = new Scanner(input);
    } catch (FileNotFoundException e) {
      throw new ResourceInitializationException(e);
    }
  }


  @Override
  public void getNext(CAS cas) throws IOException, CollectionException {
    JCas jcas;
    try {
      jcas = cas.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }
    InputSentence sentence = new InputSentence(jcas);
    String id = scanner.next();
    if(!scanner.hasNext())
      throw new CollectionException("Badly formatted line @ {0}", new Object[]{id});
    String text = scanner.nextLine().trim();
    sentence.setSentenceId(id);
    sentence.setText(text);
    sentence.addToIndexes();
    n++;
  }

  @Override
  public void close() throws IOException {
    scanner.close();
  }

  @Override
  public Progress[] getProgress() {
    return new Progress[]{};
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    return scanner.hasNextLine() && n < 10;
  }

}
