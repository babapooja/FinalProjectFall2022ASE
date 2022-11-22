/*      Copyright 2002 Arizona Board of regents on behalf of
 *                  The University of Arizona
 *                     All Rights Reserved
 *         (USE & RESTRICTION - Please read COPYRIGHT file)
 *
 *  Version    : DEVSJAVA 2.7
 *  Date       : 08-15-02
 */


package FinalProjectFall2022ASE.SpecialWard;

import simView.*;


import java.lang.*;

import FinalProjectFall2022ASE.AppConstants;
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class SWBed1 extends ViewableAtomic{

	entity patientJob = null;
	public static String currentPhase = AppConstants.PASSIVE_PHASE;
	
	public SWBed1() {this("swBed1");}
	
	public SWBed1(String name){
	    super(name);
	    addInport(AppConstants.SW_INPUTPORT[0]);
	    addOutport(AppConstants.SW_OUTPUTPORT[0]);
	}
	
	public void initialize(){
	     passivate();
	     currentPhase = AppConstants.PASSIVE_PHASE;
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		System.out.println(x);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, AppConstants.SW_INPUTPORT[0], i)) {
					patientJob = x.getValOnPort(AppConstants.SW_INPUTPORT[0], i);
					holdIn("active", AppConstants.getPatientServingTime(1));
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
		   m.add(makeContent(AppConstants.SW_OUTPUTPORT[0], new entity(patientJob.getName())));
	   }
	   return m;
	}
}

