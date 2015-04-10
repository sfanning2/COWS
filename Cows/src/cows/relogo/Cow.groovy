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
	def double speed = 1.0;
	def double orientation;
	def double flightZoneRadius;
	def double width;
	def double length;
	def double independenceLevel;
	def double anxietyLevel;
	def double anxietyThreshold;
	
	def step(){
		//make sure doesn't hit another cow
		//forward(0.5*speed)
//		while(count(other(turtlesHere()))>0) {
//			forward
//			setxy(randomPxcor(), randomPycor())
//		}
	}
	
	def graze() {
		
	}
}
