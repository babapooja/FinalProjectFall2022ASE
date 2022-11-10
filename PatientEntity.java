package FinalProjectFall2022ASE;

import GenCol.*;

public class PatientEntity extends entity{
  
  protected int processingTime;
  //protected double price;
  protected int priority;
  
  protected String patientName;
  
  
  /*
  	http://www.patientnavigatortraining.org/healthcare_system/module1/2_typesofpatientcare.htm
  	2 cases for each ward
  	general: processingTime is 15 mins diseases cured in small time frame (1-2 days)
  	semi-special: processingTime 10 mins is not life threatning but requires immediate attention (5 to 10 days)
  	special: processingTime is 5 mins, long term care required (7 to 14 days)
  */
  protected String[] cases = {
		  "CARDIAC",				// special
		  "BONE FRACTURE",			// special
		  "SERIOUS ACCIDENT",		// semi-special
		  "BURNS",					// semi-special
		  "VIRAL INFECTION",		// general
		  "STOMACHACHES"			// general
  };
  
  public PatientEntity(){
	  this("patient",1, 5);
  }
  
  // get name, const processing time and radom priority each time for patient entity
  public PatientEntity(String name, int _priority, int _processingTime){
	  super(name);
	  
	  patientName = name;
	  
	  if(_priority == 1)
	  {
		  // this is special ward patient
		  processingTime = 5;
	  }
	  
	  else if(_priority == 2)
	  {
		  // this is semi special ward patient
		  processingTime = 10;
	  }
	  
	  else
	  {
		  // this is general ward patient
		  processingTime = 15;
	  }
	  
	  priority = _priority;
	  processingTime = _processingTime;
  }
	
  public int getProcessingTime(){
	  return processingTime;
  }
	/*
	 * public double getPrice(){ return price; }
	 */
  
  public int getPriority(){
	  return priority;
  }
  
  public String getPatientName() {
	  return patientName;
  }
  
  public String toString(){
	  return patientName+"_"+"priority"+"_"+priority;
  }
		
}
