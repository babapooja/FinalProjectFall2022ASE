package FinalProjectFall2022ASE;

import GenCol.*;

public class PatientEntity extends entity{
  protected double processingTime;
  //protected double price;
  protected int priority;
  
  public PatientEntity(){
	  this("patient",10, 1);
  }
  
  public PatientEntity(String name, double _procTime, int _priority){
	  super(name);
	  processingTime = _procTime;
	  priority = _priority;
  }
	
  public double getProcessingTime(){
	  return processingTime;
  }
	/*
	 * public double getPrice(){ return price; }
	 */
  
  public int getPriority(){
	  return priority;
  }
  
  public String toString(){
	  return name+"_"+((double)((int)(processingTime*100)))/100;
  }
		
}
