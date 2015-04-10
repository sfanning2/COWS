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
	public double speed;
	public double flightZoneRadius;
	public double width;
	public double length;
	public double independenceLevel;
	public double anxietyLevel;
	public double anxietyThreshold;
	
	def updateAnxietyLevel() {
		//returns set of handlers in flight zone
		AgentSet herders = inRadius(herders(), flightZoneRadius)
		//herders in blind spot
				
		//increase anxiety level based on herder's actions
		for(Herder h: herders){
			anxietyLevel +=  h.currentTaskAnxiety
		}
		
	} 
	def step(){

		def distance = 0.1
		def direction = 30
		//every time cow steps check if they are in the pen  
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
			
			forward(1)
		
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
		//Check if cow ended up in the endpoint
		
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
