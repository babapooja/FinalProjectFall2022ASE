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

public class SpecialWardQueue extends ViewableAtomic{

	double patientServingTime = 20;
	entity patientJob = null;
	
	public SpecialWardQueue() {this("patientQueue");}
	
	public SpecialWardQueue(String name){
	    super(name);
	    addInport("specialWardIn");
	    addOutport("dischargeFromSpecialWard");
	}
	
	public void initialize(){
	     passivate();
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "specialWardIn", i)) {
					patientJob = x.getValOnPort("specialWardIn", i);
					holdIn("active", patientServingTime);
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
		   m.add(makeContent("dischargeFromSpecialWard", new entity(patientJob.getName())));
	   }
	   return m;
	}
}

