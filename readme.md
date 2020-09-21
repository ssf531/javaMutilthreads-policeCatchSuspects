This project involved simulating a Police communication system, managing the
picking up and detaining of suspects.

The content: police units picking up a police dog, attending crime scenes, picking up suspects, and dropping them off at a police station.

The data input: “police.csv” and “suspects.csv”:

	- The Police Units: These police units are loaded in through the “police.csv” file. Each police unit has an ID number, an (x,y) location, a current status, a Yes/No field for if it has a police dog and an assigned suspect ID. Police units who haven’t been assigned a suspect will have an empty suspect ID.
	
	- The Suspects: The suspects are loaded in through the “suspects.csv” file. Each suspect has an ID number, an (x,y) location, a current status and an assigned police unit ID. Suspects who haven’t yet been assigned a police unit will have an empty police unit ID.
	
	- The Kennel: This holds a set of police dogs at location (50,50) in the city grid.
	
	-The Police Stations: There are 4 stations at different locations
The output: the updated information to “police-output.csv” and “suspects-output.csv”



The police unit status:

- Standby: Check if there are any suspects to pick up. 
- Approaching Kennel: Move the police unit towards the Kennel.
- At Kennel: If the police unit is collecting a police dog, remove one dog from the kennel and
  assign it to the police unit then change the status to Approaching Suspect. If the police unit
  is returning a police dog, un-assign it from the police unit, return it to the kennel and change status to Returning.
- Approaching Suspect: Move the police unit towards the assigned suspect by 4 moves. If
  the police unit reaches the suspect, change the status to At Scene.
- At Scene: If the police unit has been at the scene for four seconds, change the status to Approaching Kennel. Otherwise do nothing.
- Returning: Move the police unit towards the nearest available station

The suspect unit status:

- When a police unit is assigned a suspect, the suspect's status is changed to Assigned
- When the police unit reaches the suspect, the suspect's status is changed to Caught
- When the police unit reaches the station, with the caught suspect, the suspect's status is changed to Jailed. The suspect is then unassigned from the police unit.

![image-20200922085906113](H:\githubFiles\javaMutilthreads\image-20200922085906113.png)