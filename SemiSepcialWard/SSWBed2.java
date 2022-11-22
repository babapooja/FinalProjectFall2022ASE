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

public class SSWBed2 extends ViewableAtomic{

	double patientServingTime = 20;
	entity patientJob = null;
	public static int bedCount = 10;
	
	public SSWBed2() {this("patientQueue");}
	
	public SSWBed2(String name){
	    super(name);
	    addInport("sswBed2In");
	    addOutport("sswBed2Out");
	}
	
	public void initialize(){
	     passivate();
	}
	
	public void  deltext(double e,message x)
	{
		Continue(e);
		
		if(phaseIs("passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "semiSpecialWardIn", i)) {
					patientJob = x.getValOnPort("semiSpecialWardIn", i);
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
		   m.add(makeContent("dischargeFromSemiSpecialWard", new entity(patientJob.getName())));
		   bedCount++;
	   }
	   return m;
	}
}
