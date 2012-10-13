

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
public class NameAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NameAnnotation.class);
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
  public NameAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NameAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NameAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NameAnnotation(JCas jcas, int begin, int end) {
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
    if (NameAnnotation_Type.featOkTst && ((NameAnnotation_Type)jcasType).casFeat_sentenceId == null)
      jcasType.jcas.throwFeatMissing("sentenceId", "ner.types.NameAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NameAnnotation_Type)jcasType).casFeatCode_sentenceId);}
    
  /** setter for sentenceId - sets  
   * @generated */
  public void setSentenceId(String v) {
    if (NameAnnotation_Type.featOkTst && ((NameAnnotation_Type)jcasType).casFeat_sentenceId == null)
      jcasType.jcas.throwFeatMissing("sentenceId", "ner.types.NameAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NameAnnotation_Type)jcasType).casFeatCode_sentenceId, v);}    
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated */
  public String getName() {
    if (NameAnnotation_Type.featOkTst && ((NameAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "ner.types.NameAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NameAnnotation_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated */
  public void setName(String v) {
    if (NameAnnotation_Type.featOkTst && ((NameAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "ner.types.NameAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NameAnnotation_Type)jcasType).casFeatCode_name, v);}    
  }

    