package FinalProjectFall2022ASE;

import GenCol.*;

public class PatientEntity extends entity{
  
  protected int processingTime;
  protected int priority;
  protected String patientName;
  
  
  /*
  	http://www.patientnavigatortraining.org/healthcare_system/module1/2_typesofpatientcare.htm
  	2 cases for each ward
  	higher the priority number higher the priority to attend the patient
  	general: processingTime is 15 units diseases cured in small time frame (1-2 days)
  	semi-special: processingTime 10 units is not life threatening but requires immediate attention (5 to 10 days)
  	special: processingTime is 5 units, long term care required (7 to 14 days)
  */
  protected String[] cases = {
		  "CARDIAC",				// special
		  "SERIOUS ACCIDENT",		// special
		  "BONE FRACTURE",			// semi-special
		  "SEPSIS",				    // semi-special // Sepsis develops when an existing infection triggers an extreme immune system response in your body.
		  "VIRAL INFECTION",		// general
		  "STOMACHACHES"			// general
  };
  
  public PatientEntity() {
	  this("patient",1, 5);
  }
  
  // get name, const processing time and radom priority each time for patient entity
  public PatientEntity(String name, int _priority, int _processingTime){
	  super(name);
	  
	  patientName = name;
	  switch(_priority) {
	  	case 1: {
		  processingTime = 5;
		  break;
	  	}
	  	case 2: {
		  processingTime = 10;
		  break;
		}
	  	case 3: {
		  processingTime = 15;
		  break;
		}
	  }
	  priority = _priority;
  }
	
  public int getProcessingTime(){
	  return processingTime;
  }
  
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
