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
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class PatientQueue extends ViewableAtomic{

	public static PatientEntity patientJob, currentPatientJob;
	public static DEVSQueue q;
	
	
	public PatientQueue() {this("patientQueue");}
	
	public PatientQueue(String name){
	    super(name);
	    addInport("patientIn");
	    
	    
	    addOutport("pqGWBed1");
	    addOutport("pqGWBed2");
	    
	    addOutport("pqSSWBed1");
	    addOutport("pqSSWBed2");
	    
	    addOutport("pqSWBed1");
	    addOutport("pqSWBed2");
	   
	    // addTestInput("patientIn", new entity("testPatient"));
	}
	
	public void initialize(){
		q = new DEVSQueue();
	    passivate();
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "patientIn", i)) 
				{
					patientJob = (PatientEntity) x.getValOnPort("patientIn", i);
					System.out.println("received patient data is as follows: ");
					System.out.println("name: "+ patientJob.patientName);
					System.out.println("processingTime: "+ patientJob.processingTime);
					System.out.println("priority: "+ patientJob.priority);
					currentPatientJob = patientJob;
					holdIn("active", currentPatientJob.getProcessingTime());
				}
			}
		}
		
		else if(phaseIs("active")) {
			for(int i=0;i<x.getLength();i++) 
			{
				if(messageOnPort(x, "patientIn", i)) {
					patientJob = (PatientEntity) x.getValOnPort("patientIn", i);
					System.out.println("following patient will be added in queue: ");
					System.out.println("name: "+ patientJob.patientName);
					System.out.println("processingTime: "+ patientJob.processingTime);
					System.out.println("priority: "+ patientJob.priority);
					q.add(patientJob);
				}
			}
		}
	}
	
	public void  deltint()
	{
		if(phaseIs("active")) {
			if(!q.isEmpty())
			{
				currentPatientJob = (PatientEntity) q.remove();
				// q.remove();
				holdIn("active", currentPatientJob.getProcessingTime());
			}
			else
			{
				passivate();
			}
		}
	}
	
	public message out( ) {
	   message  m = new message();
	   m.add(makeContent("outGeneralWard", 
			   new PatientEntity(
					   currentPatientJob.getPatientName(), 
					   currentPatientJob.getPriority(), 
					   currentPatientJob.getProcessingTime()
					   )
			   ));
	   return m;
	}
	
	public String getTooltipText(){
		if(currentPatientJob!=null)
		return super.getTooltipText()+"\n number of patients in queue:"+q.size()+
		"\n my current job is:" + currentPatientJob.toString();
		else return "initial value";
	}
}

