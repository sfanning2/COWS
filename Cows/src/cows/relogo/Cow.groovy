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
import repast.simphony.space.continuous.NdPoint;
import cows.ReLogoTurtle;
import cows.dstarlite.State

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
	

	def step(){


		//If the cow is at the goal, remove them from the field 
		int maxY = getMaxPycor()
		int minX  = getMaxPxcor()
		Patch p = patchHere()
		for(int j = maxY-10; j <= maxY; j++){
			if(p.pxcor == minX || p.pxcor == minX-1|| p.pxcor == minX-2){
				if(p.pycor == j){
					die()
					return
				}
			}
		}
			
		/*
		 * If the cows aren't being impacted by any of the conditions below they make
		 * the default movement of .05 ahead in the current direction.
		 */
		def distance = 0.2
		def direction = this.getHeading()
		if (direction >= 360) direction = direction - 360
		

		def herdersInRange = inRadius(herders(), flightZoneRadius)
		def cowsInRange = inRadius(cows(), sightRange)
		
		// If any herders are within the flight zone, the cow will have more anxiety and move away from the first herder it "sees"
		if (herdersInRange.size() > 0) {
			for (herder in herdersInRange) {
				anxietyLevel = anxietyLevel+2
			}
			// Move amount is high and away from herder
			distance = 3

			this.face(herdersInRange.get(0))
			if (this.getHeading() < 180) {
				direction = this.getHeading() + Utility.randomNormal(180,20)
			} else {
				direction = this.getHeading() - Utility.randomNormal(180,10)
			}
			setHeading(direction)
			
		// if there are 2 or more cows in the sight range of the cow,
		// or the cow's independence level (percentage of time that the cow should move with the group)
		// is greater than the random number created
		}
		if (cowsInRange.size() >= 2 && Utility.random(100) < (independenceLevel*100)){
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
			}
			if (heading_45_90 == largest){ 
				direction = 67
			}
			if (heading_90_135 == largest){ 
				direction = 112
			}
			if (heading_135_180 == largest) { 
				direction = 157
			} 
			if (heading_180_225 == largest){ 
				direction = 202
			}
			if (heading_225_270 == largest){ 
				direction = 249
			}
			if (heading_270_315 == largest){ 
				direction = 292
			}
			if (heading_315_360 == largest){ 
				direction = 337
			}
			direction = Utility.randomNormal(0, 15) + direction
			if (direction >= 360) direction = direction - 360
			distance = 2
			anxietyLevel = anxietyLevel - 3 //Cows moving in a group become less stressed
		}
		
		//Check anxiety level against threshold and override other movement if over
		if (anxietyLevel > anxietyThreshold ) {
			distance = Utility.randomNormal(4, 2) + 2
			direction = Utility.random(360)
			anxietyLevel -= 10
		}
				
		setHeading((double) direction)
		move(distance)
		// Cow avoids other turtles
		// Moves cows max of two times
		for (int i = 0; i < 2; i++) {
			if(count(inRadius(turtles(), 3)) > 1) {
				def temp = getHeading()
				def moovement = direction+((Utility.random(2)-1) * Utility.random(135))
				if (moovement < 0) {
					moovement = moovement + 360
				} else {
					moovement = floor(moovement) % 360
				}
				move(1)
				setHeading((temp + Utility.random(10)) % 360)
			}
		}
	

	}
	def int numObstaclesOn(Patch p){
		return count(turtlesOn(p)) - count(herdersOn(p))
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
