package FinalProjectFall2022ASE;

import java.util.Random;

public interface AppConstants {
	
	public static int minTime = 5; // 10 is default
	public static int maxTime = 10; // 20 is default
	public static int maxPriority = 3;
	public static int minPriority = 1;
	public static Random r = new Random();
	
	
	public static int PATIENT_GENERATION_TIME = 10;
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
		
		// if priority is 1
	  	case 1: {
		  return 5; // default is 5
	  	}
	  	
	  	// if priority is 2
	  	case 2: {
		  return 10; // default is 10
		}
	  	
	  	// if priority is 3
	  	case 3: {
		  return 15; // default is 15
		}
	  }
		return 5;
	}
	
	// returns random time for patient generation
	public static double interPatientGenerationTime() {
		return r.nextDouble((maxTime - minTime) + 1) + minTime;
		// return minTime + (maxTime - minTime) * r.nextDouble();
	}
	
	// returns random priority for patient generated
	public static int generateRandomPriority() {
		return r.nextInt((maxPriority - minPriority) + 1) + minPriority;
	}
	
	// patient serving time as per priority
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
