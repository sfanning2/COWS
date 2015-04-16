
package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.parameter.Parameters
import repast.simphony.relogo.AgentSet
import repast.simphony.relogo.Patch
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import cows.ReLogoObserver;
import cows.relogo.Herder.Role
import java.util.Random;
import repast.simphony.parameter.Parameters;
import repast.simphony.engine.environment.RunEnvironment;
import groovy.transform.TimedInterrupt;
import java.util.concurrent.TimeUnit;
/**
 * Object representing observer of the simulation and configures environmental characteristics. 
 * @author Lynnea
 *
 */

class UserObserver extends ReLogoObserver{

	/**
	 * Setup of environment with agents and obstacles
	 * @return void
	 */
	@Setup
	def setup(){
		clearAll()
		/*timeout as necessary for creating environment*/
		long expireTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(20, TimeUnit.SECONDS)
		/*if this run is part of a batch environment get parameter values from params_batch file*/
		if(RunEnvironment.getInstance().isBatch()){
			Parameters params = RunEnvironment.getInstance().getParameters()
			obstacleDensity = params.getValue("obstacleDensity")
			numHerders = params.getValue("numHerders")
			numCows = params.getValue("numCows")
		}
		
		Random randomGenerator = new Random()
		/* color field green*/
		for (UserPatch p : patches()){
			p.pcolor = 62
		}
		/* add fence*/
		setDefaultShape(Fence, "x")
		createFenceAroundField()
		
		/* add trees at random positions */
		setDefaultShape(Tree, "tree")
		double sqarea = worldHeight()*worldWidth()
		int numTrees =  (int)obstacleDensity *sqarea / 36
		createTrees(numTrees){
			size = 3
			while(count(inRadius(turtles(), 6))>1){
				setxy(randomPxcor(), randomPycor())
			}
		}
		/* add cows randomly not within 6 ft of another */
		setDefaultShape(Cow, "circle")

		createCows(numCows){
			setxy(randomPxcor(), randomPycor())
			size = 3

			/*randomly place cows so they don't hit other objects*/
			while(count(inRadius(turtles(), 6))>1){
					if(expireTime < System.nanoTime()){//timeout if takes too long because field may not be able to handle all agents
						RunEnvironment.getInstance().endRun();
					}else{
						setxy(randomPxcor(), randomPycor())
					}
				}

			
			flightZoneRadius = 6
			setHeading(Utility.random(360))
			anxietyLevel = 0
			anxietyThreshold = Utility.randomNormal(100, 20)
			sightRange = Utility.randomNormal(15, 5)
			independenceLevel = Utility.random(0.2) //Percent of the time the cow will group, b/w 5 and 0 %		
		}
		/* create herders*/
		setDefaultShape(Herder, "person")
		createHerders(numHerders){
			setxy(randomPxcor(), randomPycor())
			size = 3
			/*randomly place herder so they don't hit other objects*/
			while(count(inRadius(turtles(), 3))>1){
					if(expireTime < System.nanoTime()){//timeout if necessary
						RunEnvironment.getInstance().endRun();
					}else{
						setxy(randomPxcor(), randomPycor())
					}
				}
			/* randomly select grouper vs. mover role*/
			double roleNum = Utility.randomNormal(1, 1)
			if(roleNum > 0){
				//set as mover
				role = Role.Mover
				setColor(135)
			}else{
				//set as grouper
				role = Role.Grouper
				setColor(95)
			}

		}


	}



/**
 * Initiate asking cows and herders to act
 * @return
 */
	@Go
	def go(){
		ask(herders()){ herd() }
		ask(cows()){ step() }
	}
/**
 * Set remainingCows global
 * @return void
 */
	def remainingCows() {
		if(count(cows())==0){
			 RunEnvironment.getInstance().endRun()
		 }else{
		 	count(cows())
		 }
	}
	/**
	 * Create fences 
	 * @return void
	 */
	def createFenceAroundField(){
		int maxX = getMaxPxcor()
		int maxY = getMaxPycor()
		int minX  = getMinPxcor()
		int minY = getMinPycor()
		/*iterate over all x values*/
		for(int i=minX; i<= maxX; i++){
			createFences(1){
				setxy(i, minY)
				setColor(60)
			}
			createFences(1){
				setxy(i, maxY)
				setColor(60)
			}
		}
		/*iterate over all y values*/
		for(int j=minY; j< maxY-10; j++){
			createFences(1){
				setxy(minX, j)
				setColor(60)
			}
			createFences(1){
				setxy(maxX, j)
				setColor(60)
			}
		}
		for(int k=maxY-10; k <= maxY; k++){
			createFences(1){
				setxy(minX, k)
				setColor(60)
			}
		}
	}
	/** 
	 * Get groupers
	 * @return set of grouping herders
	 */
	def AgentSet getGroupers(){
		AgentSet clone = herders().clone()
		for(Herder h: clone){
			if(h.role == Role.Grouper){
				
			}else{
				clone.remove(h)
			}
		}
		return clone
	}
	/**
	 * Get movers
	 * @return set of moving herders
	 */
	def AgentSet getMovers(){
		AgentSet clone = herders().clone()
		for(Herder h: clone){
			if(h.role == Role.Mover){
				
			}else{
				clone.remove(h)
			}
		}
		return clone
	}

}