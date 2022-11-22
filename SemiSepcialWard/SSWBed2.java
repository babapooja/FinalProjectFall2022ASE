/*      Copyright 2002 Arizona Board of regents on behalf of
 *                  The University of Arizona
 *                     All Rights Reserved
 *         (USE & RESTRICTION - Please read COPYRIGHT file)
 *
 *  Version    : DEVSJAVA 2.7
 *  Date       : 08-15-02
 */


package FinalProjectFall2022ASE.SemiSepcialWard;

import simView.*;


import java.lang.*;

import FinalProjectFall2022ASE.AppConstants;
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class SSWBed2 extends ViewableAtomic{

	entity patientJob = null;
	public static String currentPhase = AppConstants.PASSIVE_PHASE;
	
	public SSWBed2() {this("sswBed2");}
	
	public SSWBed2(String name){
	    super(name);
	    addInport(AppConstants.SSW_INPUTPORT[1]);
	    addOutport(AppConstants.SSW_OUTPUTPORT[1]);
	}
	
	public void initialize(){
	     passivate();
	     currentPhase = AppConstants.PASSIVE_PHASE;
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, AppConstants.SSW_INPUTPORT[1], i)) {
					patientJob = x.getValOnPort(AppConstants.SSW_INPUTPORT[1], i);
					holdIn("active", AppConstants.getPatientProcessingTime(2));
					currentPhase = AppConstants.ACTIVE_PHASE;
				}
			}
		}
	}
	
	public void  deltint( ){
		if(phaseIs("active")) {
			passivate();
			currentPhase = AppConstants.PASSIVE_PHASE;
		}
	}
	
	public message out( ) {
	   message  m = new message();
	   if(phaseIs("active")) {
		   m.add(makeContent(AppConstants.SSW_OUTPUTPORT[1], new entity(patientJob.getName())));
	   }
	   return m;
	}
}

