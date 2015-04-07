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
	public double speed;
	public double flightZoneRadius;
	public double width;
	public double length;
	public double independenceLevel;
	public double anxietyLevel;
	public double anxietyThreshold;
	
	def step(){
		//make sure doesn't hit another cow
		forward(1)
	}
	def graze(){
		
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
}
