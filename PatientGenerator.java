/*      Copyright 2002 Arizona Board of regents on behalf of
 *                  The University of Arizona
 *                     All Rights Reserved
 *         (USE & RESTRICTION - Please read COPYRIGHT file)
 *
 *  Version    : DEVSJAVA 2.7
 *  Date       : 08-15-02
 */


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

	protected static double randomTime = 0;
	protected static int priority = 1, maxPriority = 3, minPriority = 1;
	protected static double interGenTime = 15;
	protected static double maxTime = 20, minTime = 10;
	protected static int count;
	protected static int processingTime;
	protected static Random r = new Random();
	
	public PatientGenerator() 
	{
		this("ptGenr", 5);
	}

	public PatientGenerator(String name,double period)
	{
	   super(name);
	   addOutport("patientIncomingHospital");
	   
	   interGenTime = period;
	   //   
	   //   addTestInput("in",new entity("an entity"),7);
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
		   // next patient will come at random time 
		   interGenTime = minTime + (maxTime - minTime) * r.nextDouble();
		   // System.out.println("patient genrated after: " + interGenTime + "minutes");
		   holdIn("active",interGenTime);
		}
	}
	
	public message  out( )
	{
	   message  m = new message();
	   
	   // randomly (1,2 or 3) decide the priority of this patient
	   priority = r.nextInt((maxPriority - minPriority) + 1) + minPriority;
	   
	   if(priority == 1) processingTime = 5;
	   else if(priority == 2) processingTime = 10;
	   else processingTime = 15;
	   
	   content con = makeContent("patientIncomingHospital", new PatientEntity("patient" + " " + count, priority, processingTime));
	   m.add(con);
	
	   return m;
	}


}

