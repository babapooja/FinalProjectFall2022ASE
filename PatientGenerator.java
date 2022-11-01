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
import util.*;
import statistics.*;

public class PatientGenerator extends ViewableAtomic{


  protected double interGenTime = 15;
  //PatientEntity job;
  protected int count;
  public PatientGenerator() {this("ptGenr", 7);}

public PatientGenerator(String name,double period){
   super(name);
   addOutport("out");
//   interGenTime = period ;
//   
//   addTestInput("in",new entity("an entity"),7);
}

public void initialize(){
   holdIn("active", interGenTime);
   count = 0;
}


public void  deltext(double e,message x)
{
Continue(e);
//   for (int i=0; i< x.getLength();i++){
//     if (messageOnPort(x, "in", i)) { //the stop message from tranducer
//       passivate();
//     }
//   }
}

public void  deltint( )
{
	if(phaseIs("active")){
	   count = count +1;
	   holdIn("active",interGenTime);
	}
}

public message  out( )
{
   message  m = new message();
   content con = makeContent("out", new entity("patient" + " " + count));
   m.add(con);

  return m;
}


}

