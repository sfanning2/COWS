package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
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
		
		def distance = 0
		def direction = this.heading // Defaults are to not move and remain facing the same direction
		

		def herdersInRange = inRadius(turtles().findAll { it.class == Herder }, flightZoneRadius)
		def cowsInRange = inRadius(turtles().findAll { it.class == Cow }, 10)
		
		if (herdersInRange.size > 0) {
			for (herder in herdersInRange) {
				anxietyLevel = anxietyLevel*2
			}
			// Move amount is high and away from herder
			distance = 3
			this.face(herdersInRange.get(0))
			direction = this.heading + Utility.random(180) + 90
		} else if (cowsInRange.size >= 3){
			// If moving is normal
				// Cow movement along with the other cows
			// Else if movement is erratic
				// No movement
				// Increase anxiety
		}
		//Check anxiety level against threshold and override other movement if over
		//If the desired movement runs into a fence, cow, or tree
			//Have the cow move up to the object
			//Have the cow move along the object
		//Else
			// Move as already computed
			move(distance)
			setHeading((double) direction)
		//Check if cow ended up in the endpoint

		//Decrease cow anxiety
		
	}
}
