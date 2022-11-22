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

public class GWBed1 extends ViewableAtomic{

	double patientServingTime = 20;
	entity patientJob = null;
	public static String currentPhase = "passive";
	
	
	public GWBed1() {this("gwBed1");}
	
	public GWBed1(String name){
	    super(name);
	    
	    addInport("gwBed1In");
	    addOutport("gwBed1Out");
	}
	
	public void initialize() {
		 currentPhase = "passive";
	     passivate();
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
				
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "gwBed1In", i)) {
					patientJob = x.getValOnPort("gwBed1In", i);
					holdIn("active", patientServingTime);
					currentPhase = "active";
				}
			}
		}
	}
	
	public void  deltint( ){
		if(phaseIs("active")) {
			currentPhase = "passive";
			passivate();
		}
	}
	
	public message out( ) {
	   message  m = new message();
	   if(phaseIs("active")) {
		   m.add(makeContent("gwBed1Out", new entity(patientJob.getName())));
	   }
	   return m;
	}
}
