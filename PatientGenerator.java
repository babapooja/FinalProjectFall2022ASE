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

import FinalProjectFall2022ASE.GeneralWard.GWBed1;
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;
import util.*;
import statistics.*;

public class PatientGenerator extends ViewableAtomic{
	
	public static int priority = 1;
	public static double interGenTime = AppConstants.PATIENT_GENERATION_TIME;
	public static int count;
	public static int processingTime;
	
	
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
		   
		   if(count > 50) 
		   {
			   // print data analysis statistics
			   printStatistics();
			   // stop simulation
			   passivate();   
			   // exit code
			   System.exit(0);
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
	
	public static void printStatistics()
	{
		System.out.println("****************************");
		
		System.out.println("gw1_count: "+PatientProcessor.gw1_count);
		System.out.println("gw2_count: "+PatientProcessor.gw2_count);
		
		System.out.println("ssw1_count: "+PatientProcessor.ssw1_count);
		System.out.println("ssw2_count: "+PatientProcessor.ssw2_count);
		
		System.out.println("sw1_count: "+PatientProcessor.sw1_count);
		System.out.println("sw2_count: "+PatientProcessor.sw2_count);
		
		System.out.println("patient_exit_count: "+PatientProcessor.patient_exit_count);
		
		System.out.println("****************************");
	}


}

