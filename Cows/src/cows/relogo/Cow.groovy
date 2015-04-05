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
		
		def distance = 0.1
		def direction = 30
		
		//If herder is in range
			// Move amount is high and away from herder
			// Increase anxiety
		//Else if other cows around it are moving
			// If moving is normal
				// Cow movement along with the other cows
			// Else if movement is erratic
				// No movement
				// Increase anxiety
		//Check anxiety level against threshold and override other movement if over
		//If the desired movement runs into a fence, cow, or tree
			//Have the cow move up to the object
			//Have the cow move along the object
		//Else
			// Move as already computed
			move(distance)
			setHeading((double) direction)
		//Check if cow ended up in the endpoint

		
		
	}
}
