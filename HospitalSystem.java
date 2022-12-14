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

import java.awt.*;
import FinalProjectFall2022ASE.GeneralWard.GWBed1;
import FinalProjectFall2022ASE.GeneralWard.GWBed2;


import FinalProjectFall2022ASE.SemiSepcialWard.SSWBed1;
import FinalProjectFall2022ASE.SemiSepcialWard.SSWBed2;

import FinalProjectFall2022ASE.SpecialWard.SWBed1;
import FinalProjectFall2022ASE.SpecialWard.SWBed2;

public class HospitalSystem extends ViewableDigraph{


public HospitalSystem(){
    this("Hospital System");
}

public HospitalSystem(String nm){
    super(nm);
    hospitalSystemConstruct();
}

public void hospitalSystemConstruct(){

    this.addOutport("hospitalExit");

    ViewableAtomic patientGenr = new PatientGenerator("patientGenr", AppConstants.PATIENT_GENERATION_TIME);
    ViewableAtomic patientProcessor = new PatientProcessor("patientProcessor");
    

    ViewableAtomic gwBed1 = new GWBed1("GWBed1");
    ViewableAtomic gwBed2 = new GWBed2("GWBed2");
    
    ViewableAtomic sswBed1 = new SSWBed1("SSWBed1");
    ViewableAtomic sswBed2 = new SSWBed2("SSWBed2");
    
    ViewableAtomic swBed1 = new SWBed1("SWBed1");
    ViewableAtomic swBed2 = new SWBed2("SWBed2");
   
	add(patientGenr);
	add(patientProcessor);
	
	add(gwBed1);
	add(gwBed2);
	
	add(sswBed1);
	add(sswBed2);
	
	add(swBed1);
	add(swBed2);
	
	// patient genr to patientQueue
	addCoupling(patientGenr,"patientIncomingHospital",patientProcessor,"patientIn");
	
	// pq to GW
	addCoupling(patientProcessor,"pqGWBed1",gwBed1,"gwBed1In");
	addCoupling(patientProcessor,"pqGWBed2",gwBed2,"gwBed2In");
	
	// pq to SSW
	addCoupling(patientProcessor,"pqSSWBed1",sswBed1,"sswBed1In");
	addCoupling(patientProcessor,"pqSSWBed2",sswBed2,"sswBed2In");
	
	// pq to SW
	addCoupling(patientProcessor,"pqSWBed1",swBed1,"swBed1In");
	addCoupling(patientProcessor,"pqSWBed2",swBed2,"swBed2In");
	
	// GW to out
	addCoupling(gwBed1,"gwBed1Out",this,"hospitalExit");
	addCoupling(gwBed2,"gwBed2Out",this,"hospitalExit");
	
	// SSW to out
	addCoupling(sswBed1,"sswBed1Out",this,"hospitalExit");
	addCoupling(sswBed2,"sswBed2Out",this,"hospitalExit");
	
	// SW to out
	addCoupling(swBed1,"swBed1Out",this,"hospitalExit");
	addCoupling(swBed2,"swBed2Out",this,"hospitalExit");
	
	// pq Exit to hospitalExit
	addCoupling(patientProcessor,"pqExit",this,"hospitalExit");
}


    /**
     * Automatically generated by the SimView program.
     * Do not edit this manually, as such changes will get overwritten.
     */
    public void layoutForSimView()
    {
        preferredSize = new Dimension(1263, 644);
        if((ViewableComponent)withName("patientProcessor")!=null)
             ((ViewableComponent)withName("patientProcessor")).setPreferredLocation(new Point(272, 183));
        if((ViewableComponent)withName("GWBed2")!=null)
             ((ViewableComponent)withName("GWBed2")).setPreferredLocation(new Point(555, 152));
        if((ViewableComponent)withName("SWBed1")!=null)
             ((ViewableComponent)withName("SWBed1")).setPreferredLocation(new Point(550, 450));
        if((ViewableComponent)withName("GWBed1")!=null)
             ((ViewableComponent)withName("GWBed1")).setPreferredLocation(new Point(557, 47));
        if((ViewableComponent)withName("SSWBed2")!=null)
             ((ViewableComponent)withName("SSWBed2")).setPreferredLocation(new Point(550, 350));
        if((ViewableComponent)withName("patientGenr")!=null)
             ((ViewableComponent)withName("patientGenr")).setPreferredLocation(new Point(103, 282));
        if((ViewableComponent)withName("SWBed2")!=null)
             ((ViewableComponent)withName("SWBed2")).setPreferredLocation(new Point(550, 550));
        if((ViewableComponent)withName("SSWBed1")!=null)
             ((ViewableComponent)withName("SSWBed1")).setPreferredLocation(new Point(553, 253));
    }
}
