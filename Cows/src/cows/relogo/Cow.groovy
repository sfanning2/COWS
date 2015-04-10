package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.AgentSet
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import cows.ReLogoTurtle;

class Cow extends ReLogoTurtle {
	public double flightZoneRadius;
	public double width;
	public double length;
	public double independenceLevel;
	public double anxietyLevel;
	public double anxietyThreshold;
	
	def step(){
		
		/*while(count(other(turtlesHere()))>0){
			setxy(randomPxcor(), randomPycor())
		}*/
		
		def distance = 1
		def direction = this.heading // Defaults are to not move and remain facing the same direction
		

		def herdersInRange = inRadius(herders(), flightZoneRadius)
		def cowsInRange = inRadius(cows(), 10)
		
		if (herdersInRange.size() > 0) {
			for (herder in herdersInRange) {
				anxietyLevel = anxietyLevel*2
			}
			// Move amount is high and away from herder
			distance = 3
			this.face(herdersInRange.get(0))
			if (this.getHeading() < 180) {
				this.setHeading(this.getHeading() + Utility.random(90) + 90)
			} else {
				this.setHeading(this.getHeading() - Utility.random(90) - 90)
			}
			
		} else if (cowsInRange.size() >= 3){
			// Calculate average heading
			def heading_0_45 = 0
			def heading_45_90 = 0
			def heading_90_135 = 0
			def heading_135_180 = 0
			def heading_180_225 = 0
			def heading_225_270 = 0
			def heading_270_315 = 0
			def heading_315_360 = 0
			for (cow in cowsInRange) {
				if (cow.getHeading() > 0 && cow.getHeading() <= 45) heading_0_45++
				if (cow.getHeading() > 45 && cow.getHeading() <= 90) heading_45_90++
				if (cow.getHeading() > 90 && cow.getHeading() <= 135) heading_90_135++
				if (cow.getHeading() > 135 && cow.getHeading() <= 180) heading_135_180++
				if (cow.getHeading() > 180 && cow.getHeading() <= 225) heading_180_225++
				if (cow.getHeading() > 225 && cow.getHeading() <= 270) heading_225_270++
				if (cow.getHeading() > 270 && cow.getHeading() <= 315) heading_270_315++
				if (cow.getHeading() > 315 && cow.getHeading() <= 360) heading_315_360++
			}
			def largest = Math.max(heading_0_45, heading_45_90)
			largest = Math.max(largest, heading_90_135)
			largest = Math.max(largest, heading_135_180)
			largest = Math.max(largest, heading_180_225)
			largest = Math.max(largest, heading_225_270)
			largest = Math.max(largest, heading_270_315)
			largest = Math.max(largest, heading_315_360)
			if (heading_0_45 == largest) { 
				direction = 22
			} else if (heading_45_90 == largest){ 
				direction = 67
			} else if (heading_90_135 == largest){ 
				direction = 112
			} else if (heading_135_180 == largest) { 
				direction = 157
			} else if (heading_180_225 == largest){ 
				direction = 202
			} else if (heading_225_270 == largest){ 
				direction = 249
			} else if (heading_270_315 == largest){ 
				direction = 292
			} else if (heading_315_360 == largest){ 
				direction = 337
			}
			distance = Utility.random(2) + 2 // Movement is 2 or 3
			anxietyLevel -= 3 //Cows moving in a group become less stressed
			// Alternate movement specification; implement if time exist
			// If cows are moving together
				// Cow movement along with the other cows
			// Else if movement is erratic
				// No movement
				// Increase anxiety
		}
		//Check anxiety level against threshold and override other movement if over
		if (anxietyLevel > anxietyThreshold ) {
			distance = Utility.random(4)+2
			direction = Utility.random(360)
			anxietyLevel -= 2
		}
		
		move(distance)
		setHeading((double) direction)
		
		//Cow avoids other turtles 
		
		
		
		//Check if cow ended up in the endpoint

	}
}
