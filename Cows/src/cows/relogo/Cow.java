package cows.relogo;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.relogo.AgentSet;
import repast.simphony.relogo.Patch;
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import cows.ReLogoTurtle;
import cows.dstarlite.State;

import java.util.logging.Handler;

public class Cow extends ReLogoTurtle {
	double speed;
	double flightZoneRadius;
	double width;
	double length;
	double independenceLevel;
	double anxietyLevel;
	double anxietyThreshold;
	double sightRange;
	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;

	public Cow(ContinuousSpace<Object> space, Grid<Object> grid){
		this.space = space;
		this.grid = grid;
	}
	public Cow(){
		
	}
	@SuppressWarnings("unchecked")
	@ScheduledMethod ( start = 1 , interval = 1)
	public void step(){


		//If the cow is at the goal, remove them from the field 
		int maxY = getMaxPycor();
		int minX  = getMaxPxcor();
		Patch p = patchHere();
		//iterate over patches
		for(int j = maxY-10; j <= maxY; j++){
			if(p.getPxcor() == minX || p.getPxcor() == minX-1|| p.getPxcor() == minX-2){
				if(p.getPycor() == j){
					die();
					break;
				}
			}
		}
			
		double distance = 0.05;
		double direction = this.getHeading();
		if (direction >= 360) direction = direction - 360;
		

		AgentSet<Herder> herdersInRange = inRadius(herders(), flightZoneRadius);
		AgentSet<Cow> cowsInRange = inRadius(cows(), sightRange);
		
		if (herdersInRange.size() > 0) {
			for (Herder h : herdersInRange) {
				anxietyLevel = anxietyLevel+2;
			}
			// Move amount is high and away from herder
			distance = 3;

			this.face(herdersInRange.get(0));
			if (this.getHeading() < 180) {
				direction = this.getHeading() + Utility.randomNormal(180,20);
			} else {
				direction = this.getHeading() - Utility.randomNormal(180,10);
			}
			
		} else if (cowsInRange.size() >= 2 && Utility.random(1) < independenceLevel){
			// Calculate average heading
			int heading_0_45 = 0     ;
			int heading_45_90 = 0    ;
			int heading_90_135 = 0   ;
			int heading_135_180 = 0  ;
			int heading_180_225 = 0  ;
			int heading_225_270 = 0  ;
			int heading_270_315 = 0  ;
			int heading_315_360 = 0  ;
			for (Cow cow : cowsInRange) {
				if (cow.getHeading() > 0 && cow.getHeading() <= 45) heading_0_45++;
				if (cow.getHeading() > 45 && cow.getHeading() <= 90) heading_45_90++;
				if (cow.getHeading() > 90 && cow.getHeading() <= 135) heading_90_135++;
				if (cow.getHeading() > 135 && cow.getHeading() <= 180) heading_135_180++;
				if (cow.getHeading() > 180 && cow.getHeading() <= 225) heading_180_225++;
				if (cow.getHeading() > 225 && cow.getHeading() <= 270) heading_225_270++;
				if (cow.getHeading() > 270 && cow.getHeading() <= 315) heading_270_315++;
				if (cow.getHeading() > 315 && cow.getHeading() <= 360) heading_315_360++;
			}
			int largest = Math.max(heading_0_45, heading_45_90);
			largest = Math.max(largest, heading_90_135);
			largest = Math.max(largest, heading_135_180);
			largest = Math.max(largest, heading_180_225);
			largest = Math.max(largest, heading_225_270);
			largest = Math.max(largest, heading_270_315);
			largest = Math.max(largest, heading_315_360);
			if (heading_0_45 == largest) { 
				direction = 22;
			}
			if (heading_45_90 == largest){ 
				direction = 67;
			}
			if (heading_90_135 == largest){ 
				direction = 112;
			}
			if (heading_135_180 == largest) { 
				direction = 157;
			} 
			if (heading_180_225 == largest){ 
				direction = 202;
			}
			if (heading_225_270 == largest){ 
				direction = 249;
			}
			if (heading_270_315 == largest){ 
				direction = 292;
			}
			if (heading_315_360 == largest){ 
				direction = 337;
			}
			direction = Utility.randomNormal(0, 15) + direction;
			if (direction >= 360) direction = direction - 360;
			distance = 1;
			anxietyLevel = anxietyLevel - 3; //Cows moving in a group become less stressed
		}
		
		//Check anxiety level against threshold and override other movement if over
		if (anxietyLevel > anxietyThreshold ) {
			distance = Utility.randomNormal(4, 2) + 2;
			direction = Utility.random(360);
			anxietyLevel -= 10;
		}
				
		setHeading((double) direction);
		move(distance);
		//Cow avoids other turtles 
		if(count(turtlesHere()) > 1) {
			setHeading(floor(direction+Utility.random(135)) % 360);
			move(0.01);
		}
	

	}
	public int numObstaclesOn(Patch p){
		return count(turtlesOn(p)) - count(herdersOn(p));
	}
	public void setSpeed(double speed){
		this.speed = speed;
	}
	public void setAnxietyLevel(double level){
		this.anxietyLevel = level;
	}
	public void setAnxietyThreshold(double level){
		this.anxietyThreshold = level;
	}
	public void setIndependenceLevel(double level){
		this.independenceLevel = level;
	}
	public void setFlightZoneRadius( double level){
		this.flightZoneRadius = level;
	}
	public void setSightRange(double level) {
		this.sightRange = level;
	}
}
