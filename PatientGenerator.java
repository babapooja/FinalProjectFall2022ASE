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


import java.lang.*;
import java.util.Random;

import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;
import util.*;
import statistics.*;

public class PatientGenerator extends ViewableAtomic{
	protected static int priority = 1;
	protected static double interGenTime = AppConstants.PATIENT_GENERATION_TIME;
	protected static int count;
	protected static int processingTime;
	
	
	public PatientGenerator() 
	{
		this("ptGenr", 5);
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
		   interGenTime = AppConstants.interPatientGenerationTime();
		   holdIn("active",interGenTime);
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

