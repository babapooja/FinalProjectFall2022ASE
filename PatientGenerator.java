/*      Copyright 2002 Arizona Board of regents on behalf of
 *                  The University of Arizona
 *                     All Rights Reserved
 *         (USE & RESTRICTION - Please read COPYRIGHT file)
 *
 *  Version    : DEVSJAVA 2.7
 *  Date       : 08-15-02
 */

// Patient is generated in every 15 units of time

package FinalProjectFall2022ASE;

import simView.*;
import genDevs.modeling.*;


public class PatientGenerator extends ViewableAtomic{
	
	public static int priority = 1;
	public static double interGenTime = AppConstants.PATIENT_GENERATION_TIME;
	public static int count;
	public static int processingTime;
	
	
	public PatientGenerator() 
	{
		this("ptGenr", interGenTime);
	}

	public PatientGenerator(String name,double period)
	{
	   super(name);
	   addOutport(AppConstants.PATIENT_GENERATOR_OUTPUTPORT);
	   interGenTime = period;
	}
		
	public void initialize()
	{
	   holdIn("active", interGenTime);
	   count = 0;
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
	}
	
	public void  deltint( )
	{
		if(phaseIs("active")){
		   count = count + 1;		   
		   if(count > 99) 
		   {
			   System.out.println("patient "+count+ "generated");
			   // stop generating the patients - for simulation purpose and to analyze the results for a limited patients count
			   passivate();   
		   }
		   else
		   {
			   interGenTime = AppConstants.interPatientGenerationTime();
			   holdIn("active",interGenTime);
		   }
		}
	}
	
	public message  out( )
	{		
	   message  m = new message();
	   
	   // randomly generate a x priority patient
	   priority = AppConstants.generateRandomPriority();
	   processingTime = AppConstants.getPatientProcessingTime(priority);
	
	   content con = makeContent(AppConstants.PATIENT_GENERATOR_OUTPUTPORT, new PatientEntity("patient" + " " + count, priority, processingTime));
	   m.add(con);
	
	   return m;
	}
	
	


}

