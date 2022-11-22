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
	public static int bedCount = 15;
	
	public GWBed2() {this("generalWardQueue");}
	
	public GWBed2(String name){
	    super(name);
	    addInport("gwBed2In");
	    addOutport("gwBed2Out");
	}
	
	public void initialize() {
	     passivate();
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "generalWardIn", i)) {
					patientJob = x.getValOnPort("generalWardIn", i);
					holdIn("active", patientServingTime);
					bedCount--;
				}
			}
		}
	}
	
	public void  deltint( ){
		if(phaseIs("active")) {
			passivate();
		}
	}
	
	public message out( ) {
	   message  m = new message();
	   if(phaseIs("active")) {
		   m.add(makeContent("dischargeFromGeneralWard", new entity(patientJob.getName())));
		   bedCount++;
	   }
	   return m;
	}
}