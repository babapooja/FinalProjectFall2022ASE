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
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class SWBed1 extends ViewableAtomic{

	double patientServingTime = 20;
	entity patientJob = null;
	public static String currentPhase = "passive";
	
	public SWBed1() {this("swBed1");}
	
	public SWBed1(String name){
	    super(name);
	    addInport("swBed1In");
	    addOutport("swBed1Out");
	}
	
	public void initialize(){
	     passivate();
	     currentPhase = "passive";
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		System.out.println(x);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "swBed1In", i)) {
					patientJob = x.getValOnPort("swBed1In", i);
					System.out.println("BEEEDD 1 patient received: "+patientJob.getName());
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
		   m.add(makeContent("swBed1Out", new entity(patientJob.getName())));
	   }
	   return m;
	}
}

