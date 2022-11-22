/*      Copyright 2002 Arizona Board of regents on behalf of
 *                  The University of Arizona
 *                     All Rights Reserved
 *         (USE & RESTRICTION - Please read COPYRIGHT file)
 *
 *  Version    : DEVSJAVA 2.7
 *  Date       : 08-15-02
 */


package FinalProjectFall2022ASE.GeneralWard;

import simView.*;


import java.lang.*;

import FinalProjectFall2022ASE.AppConstants;
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class GWBed1 extends ViewableAtomic{

	entity patientJob = null;
	public static String currentPhase = AppConstants.PASSIVE_PHASE;
	
	
	public GWBed1() {this("gwBed1");}
	
	public GWBed1(String name){
	    super(name);
	    
	    addInport(AppConstants.GW_INPUTPORT[0]);
	    addOutport(AppConstants.GW_OUTPUTPORT[0]);
	}
	
	public void initialize() {
		 currentPhase = AppConstants.PASSIVE_PHASE;
	     passivate();
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
				
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, AppConstants.GW_INPUTPORT[0], i)) {
					patientJob = x.getValOnPort(AppConstants.GW_INPUTPORT[0], i);
					holdIn("active", AppConstants.getPatientServingTime(3));
					currentPhase = AppConstants.ACTIVE_PHASE;
				}
			}
		}
	}
	
	public void  deltint( ){
		if(phaseIs("active")) {
			currentPhase = AppConstants.PASSIVE_PHASE;
			passivate();
		}
	}
	
	public message out( ) {
	   message  m = new message();
	   if(phaseIs("active")) {
		   m.add(makeContent(AppConstants.GW_OUTPUTPORT[0], new entity(patientJob.getName())));
	   }
	   return m;
	}
}
