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
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class GWBed2 extends ViewableAtomic{

	double patientServingTime = 20;
	entity patientJob = null;
	public static String currentPhase = "passive";
	
	public GWBed2() {this("gwBed2");}
	
	public GWBed2(String name){
	    super(name);
	    addInport("gwBed2In");
	    addOutport("gwBed2Out");
	}
	
	public void initialize() {
	     passivate();
	     currentPhase = "passive";
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "gwBed2In", i)) {
					patientJob = x.getValOnPort("gwBed2In", i);
					holdIn("active", patientServingTime);
					currentPhase = "active";
				}
			}
		}
	}
	
	public void  deltint( ){
		if(phaseIs("active")) {
			passivate();
			currentPhase = "passive";
		}
	}
	
	public message out( ) {
	   message  m = new message();
	   if(phaseIs("active")) {
		   m.add(makeContent("gwBed2Out", new entity(patientJob.getName())));
	   }
	   return m;
	}
}
