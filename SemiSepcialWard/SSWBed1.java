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
import genDevs.modeling.*;
import genDevs.simulation.*;
import GenCol.*;

public class SSWBed1 extends ViewableAtomic{

	double patientServingTime = 20;
	entity patientJob = null;
	public static String currentPhase = "passive";
	
	public SSWBed1() {this("sswBed1");}
	
	public SSWBed1(String name){
	    super(name);
	    addInport("sswBed1In");
	    addOutport("sswBed1Out");
	}
	
	public void initialize(){
	     passivate();
	     currentPhase = "passive";
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "sswBed1In", i)) {
					patientJob = x.getValOnPort("sswBed1In", i);
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
		   m.add(makeContent("sswBed1Out", new entity(patientJob.getName())));
	   }
	   return m;
	}
}

