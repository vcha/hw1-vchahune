

/* First created by JCasGen Sun Oct 14 13:10:50 EDT 2012 */
package ner.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 14 13:10:50 EDT 2012
 * XML source: /Users/vchahun/Dropbox/Documents/CMU/Eng/workspace/hw1-vchahune/src/main/resources/uima/TypeSystemDescriptor.xml
 * @generated */
public class GeneAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GeneAnnotation.class);
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
  protected GeneAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public GeneAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public GeneAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public GeneAnnotation(JCas jcas, int begin, int end) {
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
    if (GeneAnnotation_Type.featOkTst && ((GeneAnnotation_Type)jcasType).casFeat_sentenceId == null)
      jcasType.jcas.throwFeatMissing("sentenceId", "ner.types.GeneAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneAnnotation_Type)jcasType).casFeatCode_sentenceId);}
    
  /** setter for sentenceId - sets  
   * @generated */
  public void setSentenceId(String v) {
    if (GeneAnnotation_Type.featOkTst && ((GeneAnnotation_Type)jcasType).casFeat_sentenceId == null)
      jcasType.jcas.throwFeatMissing("sentenceId", "ner.types.GeneAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneAnnotation_Type)jcasType).casFeatCode_sentenceId, v);}    
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated */
  public String getName() {
    if (GeneAnnotation_Type.featOkTst && ((GeneAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "ner.types.GeneAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneAnnotation_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated */
  public void setName(String v) {
    if (GeneAnnotation_Type.featOkTst && ((GeneAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "ner.types.GeneAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneAnnotation_Type)jcasType).casFeatCode_name, v);}    
  }

    