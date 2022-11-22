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
import FinalProjectFall2022ASE.GeneralWard.GWBed1;
import FinalProjectFall2022ASE.GeneralWard.GWBed2;
import FinalProjectFall2022ASE.SemiSepcialWard.SSWBed1;
import FinalProjectFall2022ASE.SemiSepcialWard.SSWBed2;
import FinalProjectFall2022ASE.SpecialWard.SWBed1;
import FinalProjectFall2022ASE.SpecialWard.SWBed2;
import genDevs.modeling.*;
import GenCol.*;

public class PatientProcessor extends ViewableAtomic{

	public static PatientEntity patientJob, currentPatientJob;
	public static DEVSQueue q;
	
	public static boolean shiftingAlgorithm = false;
	
	public PatientProcessor() {this("patientProcessor");}
	
	public PatientProcessor(String name){
	    super(name);
	    addInport("patientIn");
	    
	    
	    addOutport("pqGWBed1");
	    addOutport("pqGWBed2");
	    
	    addOutport("pqSSWBed1");
	    addOutport("pqSSWBed2");
	    
	    addOutport("pqSWBed1");
	    addOutport("pqSWBed2");
	    
	    addOutport("pqExit");
	   
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
//					System.out.println("received patient data is as follows: ");
//					System.out.println("name: "+ patientJob.patientName);
//					System.out.println("processingTime: "+ patientJob.processingTime);
//					System.out.println("priority: "+ patientJob.priority);
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
//					System.out.println("following patient will be added in queue: ");
//					System.out.println("name: "+ patientJob.patientName);
//					System.out.println("processingTime: "+ patientJob.processingTime);
//					System.out.println("priority: "+ patientJob.priority);
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
	   
	   int m_priority = currentPatientJob.getPriority();
	   
	   String outputPort = "";
	      
	   if(shiftingAlgorithm == false)
	   {
		   if(m_priority == 3)
		   {
			   if(GWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqGWBed1";
			   }
			   else if(GWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqGWBed2";
			   }
			   else
			   {
				   outputPort = "pqExit";
			   }
		   }
		   
		   else if(m_priority == 2)
		   {
			   if(SSWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqSSWBed1";
			   }
			   else if(SSWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqSSWBed2";
			   }
			   else
			   {
				   outputPort = "pqExit";
			   }

		   }
		   
		   else if(m_priority == 1)
		   {
			   if(SWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqSWBed1";
			   }
			   else if(SWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqSWBed2";
			   }
			   else
			   {
				   outputPort = "pqExit";
			   }
		   }
		   
		   System.out.println("outputPort: "+ outputPort);
			   
		   m.add(makeContent(outputPort, 
				   new PatientEntity(
						   currentPatientJob.getPatientName(), 
						   currentPatientJob.getPriority(), 
						   currentPatientJob.getProcessingTime()
						   )
				   ));
	   }
	   
	   else
	   {
		   // shifting algorithm is ON
		   // implement logic here
		   
		   if(m_priority == 3)
		   {
			   if(GWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqGWBed1";
			   }
			   else if(GWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqGWBed2";
			   }
			   else
			   {
				   outputPort = "pqExit";
			   }
		   }
		   
		   else if(m_priority == 2)
		   {
			   // check SSW beds
			   if(SSWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqSSWBed1";
			   }
			   
			   else if(SSWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqSSWBed2";
			   }
			   
			   // if occupied check GW beds
			   else if(GWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqGWBed1";
			   }
			   else if(GWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqGWBed2";
			   }
			   
			   else
			   {
				   outputPort = "pqExit";
			   }
		   }
		   
		   else if(m_priority == 1)
		   {
			   // check SW beds
			   if(SWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqSWBed1";
			   }
			   else if(SWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqSWBed2";
			   }
			   
			   // if occupied check SSW beds
			   else if(SSWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqSSWBed1";
			   }
			   else if(SSWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqSSWBed2";
			   }
			   
			   // if occupied check GW beds
			   else if(GWBed1.currentPhase == "passive")
			   {
				   outputPort = "pqGWBed1";
			   }
			   else if(GWBed2.currentPhase == "passive")
			   {
				   outputPort = "pqGWBed2";
			   }
			   
			   else
			   {
				   outputPort = "pqExit";
			   }
		   }
		   
		   System.out.println("outputPort: "+ outputPort);
			   
		   m.add(makeContent(outputPort, 
				   new PatientEntity(
						   currentPatientJob.getPatientName(), 
						   currentPatientJob.getPriority(), 
						   currentPatientJob.getProcessingTime()
						   )
				   ));
		   
	   }
	   
	   
	   return m;
	}
	
	public String getTooltipText(){
		if(currentPatientJob!=null)
		return super.getTooltipText()+"\n number of patients in queue:"+q.size()+
		"\n my current job is:" + currentPatientJob.toString();
		else return "initial value";
	}
}

