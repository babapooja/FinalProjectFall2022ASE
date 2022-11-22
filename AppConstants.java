package FinalProjectFall2022ASE;

import java.util.Random;

public interface AppConstants {
	
	int minTime = 10;
	int maxTime = 20;
	int maxPriority = 3;
	int minPriority = 1;
	static Random r = new Random();
	
	
	public int PATIENT_GENERATION_TIME= 10;
	public String PATIENT_GENERATOR_OUTPUTPORT = "patientIncomingHospital"; 
	
	
	
	
	
	
	
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
	
}
