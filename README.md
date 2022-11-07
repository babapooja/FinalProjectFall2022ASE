## DEVSJAVA model for simulation of patient flow in hospital


### TODOs

* import patientEntity in PatientQueue.java class, assign holdIn time 
according to processingTime of patient priority. Create Patient Queue and 
insert patients in the queue if processing at the reception is happening.

* If patient is in queue at patientQueue, there is need of additional 
brain storm here because if the bedcount at a ward is full we cannot send 
the patient to ward even if he is processed at patientQueue.java model.

* once patient is arrived at ward, decrement the bedcount variable in that 
ward. assign patientServingTime differently for each ward. After patient's 
treatment is over he will be sent off from ward.

* Big picture: perform analysis on bedcount, generationTime, processTime, 
patientServingTime, etc.

