package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.AgentSet
import repast.simphony.relogo.Patch
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import repast.simphony.space.continuous.ContinuousSpace;
import cows.ReLogoTurtle;

import java.util.logging.Handler

class Cow extends ReLogoTurtle {
	def double speed;
	def double flightZoneRadius;
	def double width;
	def double length;
	def double independenceLevel;
	def double anxietyLevel;
	def double anxietyThreshold;
	def double sightRange;
	
	/*def updateAnxietyLevel() {
		//returns set of handlers in flight zone
		AgentSet herders = inRadius(herders(), flightZoneRadius)
		//herders in blind spot
				
		//increase anxiety level based on herder's actions
		for(Herder h: herders){
			anxietyLevel +=  h.currentTaskAnxiety
		}
		
	} */
	def step(){

		//If the cow is at the goal, remove them from the field 
		int maxY = getMaxPycor()
		int minX  = getMaxPxcor()
		Patch p = patchHere()
		//iterate over patches
		for(int j = maxY-10; j <= maxY; j++){
			if(p.pxcor == minX || p.pxcor == minX-1|| p.pxcor == minX-2){
				if(p.pycor == j){
					die()
					return
				}
			}
		}
		
		/* def movementMade = false
		def startingAnxiety = anxietyLevel
		def startingDirection = this.getHeading()
		while (!movementMade) {*/
			
			def distance = 0.1
			def direction = this.getHeading()
			if (direction >= 360) direction = direction - 360
			
	
			def herdersInRange = inRadius(herders(), flightZoneRadius)
			def cowsInRange = inRadius(cows(), sightRange)
			
			if (herdersInRange.size() > 0) {
				for (herder in herdersInRange) {
					anxietyLevel = anxietyLevel+2
				}
				// Move amount is high and away from herder
				distance = 3
				this.face(herdersInRange.get(0))
				if (this.getHeading() < 180) {
					this.setHeading(this.getHeading() + Utility.random(90) + 45)
				} else {
					this.setHeading(this.getHeading() - Utility.random(90) - 45)
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
				direction = Utility.randomNormal(0, 15) + direction
				if (direction >= 360) direction = direction - 360
				distance = Utility.random(2) + 2 // Movement is 2 or 3
				anxietyLevel = anxietyLevel - 3 //Cows moving in a group become less stressed
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
			
			setHeading((double) direction)
			move(distance)
			
			//Cow avoids other turtles 
			/*if (count(turtlesHere()) > 1) {
				anxietyLevel = startingAnxiety
				def resetDirection = direction + 180
				if (resetDirection >= 360) resetDirection = resetDirection - 360
				setHeading(resetDirection)
				move(distance)
				setHeading(startingDirection)
			} else {
				movementMade = true
			}
		
		}*/

	}
	def setSpeed(double speed){
		this.speed = speed
	}
	def setAnxietyLevel(double level){
		this.anxietyLevel = level
	}
	def setAnxietyThreshold(double level){
		this.anxietyThreshold = level
	}
	def setIndependenceLevel(double level){
		this.independenceLevel = level
	}
	def setFlightZoneRadius( double level){
		this.flightZoneRadius = level
	}
}
