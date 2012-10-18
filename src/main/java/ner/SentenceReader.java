package ner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import ner.types.InputSentence;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.Progress;

/**
 * Collection Reader responsible for parsing the input file and producing {@link InputSentence} instances. 
 */

public class SentenceReader extends CollectionReader_ImplBase {
  
  /**
   * Scanner of the input file content.
   */
  private Scanner scanner;  
  
  /**
   * Initializer for the Analysis Engine.
   * Opens the input file provided as a parameter in the descriptor and creates an input scanner.
   * 
   * @throws ResourceInitializationException when the input file cannot be read.
   */
  @Override
  public void initialize() throws ResourceInitializationException {
    String inputFile = (String) getConfigParameterValue("input");
    try {
      BufferedReader input = new BufferedReader(new FileReader(inputFile));
      scanner = new Scanner(input);
    } catch (FileNotFoundException e) {
      throw new ResourceInitializationException(e);
    }
  }

  /**
   * Reads the next input sentence and produces an {@link InputSentence} instance in the CAS.
   * 
   * @param cas The CAS in which to put the input sentences.
   * @throws ResourceProcessException when the JCAS API cannot be obtained.
   * @throws CollectionException when the input file is badly formatted.
   */
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
    jcas.setDocumentText(text);
    sentence.setSentenceId(id);
    sentence.addToIndexes();
  }

  /**
   * Closes the input file scanner.
   */
  @Override
  public void close() throws IOException {
    scanner.close();
  }

  /**
   * The Collection Reader progress is unknown.
   * 
   * @return The (empty) progress of the Collection Reader.
   */
  @Override
  public Progress[] getProgress() {
    return new Progress[]{};
  }

  /**
   * Checks the availability of a new input sentence in the input.
   * 
   * @return true if the end of the file has not been reached yet.
   */
  @Override
  public boolean hasNext() throws IOException, CollectionException {
    return scanner.hasNextLine();
  }

}
