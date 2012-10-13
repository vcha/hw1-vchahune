

/* First created by JCasGen Sun Oct 07 17:07:09 EDT 2012 */
package ner.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 07 17:17:36 EDT 2012
 * XML source: /Users/vchahun/Dropbox/Documents/CMU/Eng/workspace/hw1-vchahune/src/main/resources/uima/TypeSystemDescriptor.xml
 * @generated */
public class InputSentence extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(InputSentence.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  public InputSentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public InputSentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public InputSentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public InputSentence(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: sentenceId

  /** getter for sentenceId - gets 
   * @generated */
  public String getSentenceId() {
    if (InputSentence_Type.featOkTst && ((InputSentence_Type)jcasType).casFeat_sentenceId == null)
      jcasType.jcas.throwFeatMissing("sentenceId", "ner.types.InputSentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((InputSentence_Type)jcasType).casFeatCode_sentenceId);}
    
  /** setter for sentenceId - sets  
   * @generated */
  public void setSentenceId(String v) {
    if (InputSentence_Type.featOkTst && ((InputSentence_Type)jcasType).casFeat_sentenceId == null)
      jcasType.jcas.throwFeatMissing("sentenceId", "ner.types.InputSentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((InputSentence_Type)jcasType).casFeatCode_sentenceId, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated */
  public String getText() {
    if (InputSentence_Type.featOkTst && ((InputSentence_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "ner.types.InputSentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((InputSentence_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated */
  public void setText(String v) {
    if (InputSentence_Type.featOkTst && ((InputSentence_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "ner.types.InputSentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((InputSentence_Type)jcasType).casFeatCode_text, v);}    
  }

    