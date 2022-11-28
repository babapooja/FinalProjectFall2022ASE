package FinalProjectFall2022ASE;

import GenCol.entity;

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
  	
  	Following cases for bed allocations:
	1. Patient with Priority 1 has 3 options for bed
	2. Patient with Priority 2 has 2 options for bed
	3. Patient with Priority 3 has 1 option for bed
	
	Following cases analyzed:-
	1. Patients being generated at SLOW PACE: maxTime - minTime = 10 units & NO SHIFTING logic applied
	2. Patients being generated at SLOW PACE: maxTime - minTime = 10 units & SHIFTING logic applied   
	3. Patients being generated at FAST PACE: maxTime - minTime = 5 units & NO SHIFTING logic applied
	4. Patients being generated at SLOW PACE: maxTime - minTime = 5 units & SHIFTING logic applied
  */
  protected String[] cases = {
		  "CARDIAC",				// special
		  "SERIOUS ACCIDENT",		// special
		  "BONE FRACTURE",			// semi-special
		  "SEPSIS",				    // semi-special
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
	  processingTime = AppConstants.getPatientProcessingTime(_priority);
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
