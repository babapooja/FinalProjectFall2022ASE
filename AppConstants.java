package FinalProjectFall2022ASE;

import java.util.Random;

public interface AppConstants {
	
	int minTime = 10;
	int maxTime = 20;
	int maxPriority = 3;
	int minPriority = 1;
	static Random r = new Random();
	
	
	public int PATIENT_GENERATION_TIME = 10;
	public static Boolean APPLY_SHIFTING_LOGIC = false;
	public static String PASSIVE_PHASE = "passive";
	public static String ACTIVE_PHASE = "active";
	public static String PATIENT_GENERATOR_OUTPUTPORT = "patientIncomingHospital"; 
	public static String PATIENT_PROCESSOR_INPUTPORT = "patientIn";
	public static String[] PATIENT_PROCESSOR_OUTPUTPORT = {"pqExit","pqGWBed1", "pqGWBed2", "pqSSWBed1", "pqSSWBed2", "pqSWBed1", "pqSWBed2"};
	
	public static String [] GW_INPUTPORT = {"gwBed1In", "gwBed2In"};
	public static String [] GW_OUTPUTPORT = {"gwBed1Out", "gwBed2Out"};
	
	public static String [] SSW_INPUTPORT = {"sswBed1In", "sswBed2In"};
	public static String [] SSW_OUTPUTPORT = {"sswBed1Out", "sswBed2Out"};
	
	public static String [] SW_INPUTPORT = {"swBed1In", "swBed2In"};
	public static String [] SW_OUTPUTPORT = {"swBed1Out", "swBed2Out"};
	
	
	// CONSTANT FUNCTIONS
	public static int getPatientProcessingTime(int priority) {
		switch(priority) {
	  	case 1: {
		  return 5;
	  	}
	  	case 2: {
		  return 10;
		}
	  	case 3: {
		  return 15;
		}
	  }
		return 5;
	}
	
	public static double interPatientGenerationTime() {
		return minTime + (maxTime - minTime) * r.nextDouble();
	}
	
	public static int generateRandomPriority() {
		return r.nextInt((maxPriority - minPriority) + 1) + minPriority;
	}
	
	public static int getPatientServingTime(int priority) {
		switch(priority) {
	  	case 1: {
		  return 20;
	  	}
	  	case 2: {
		  return 20;
		}
	  	case 3: {
		  return 20;
		}
	  }
		return 20;
	}
	
}
