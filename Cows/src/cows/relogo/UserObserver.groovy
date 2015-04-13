
package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Patch
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import cows.ReLogoObserver;
import java.util.Random;

class UserObserver extends ReLogoObserver{
	
	 /*Observer methods*/

		@Setup
		def setup(){
			clearAll()
			Random randomGenerator = new Random()
			for (UserPatch p : patches()){
				p.pcolor = 62
			}
			setDefaultShape(Fence, "x")
			createFenceAroundField()
			
			setDefaultShape(Tree, "tree")
			double sqarea = worldHeight()*worldWidth()
			int numTrees =  (int)obstacleDensity *sqarea / 36
			createTrees(numTrees){
				size = 6
				while(count(inRadius(turtles(), 6))>1){
					setxy(randomPxcor(), randomPycor())
				}
			}
			setDefaultShape(Cow, "fish")
			
			createCows(numCows){
				setxy(randomPxcor(), randomPycor())
				size = 6
				//randomly place cows so they don't hit other objects
				
				while(count(inRadius(turtles(), 6))>1){
					setxy(randomPxcor(), randomPycor())
				}
			
				/*double s = randomGenerator.nextGaussian() * 0.5
				if(s<0){
					s = 0
				}else if(s>3){
					s = 3
				}
				setSpeed(s)
				setHeading(randomGenerator.nextInt(360))
				setAnxietyLevel(0)
				double t = randomGenerator.nextGaussian()*2+ 10
				if(t<5){
					t = 5
				}
				setAnxietyThreshold(t)
				setIndependenceLevel(randomGenerator.nextInt(8))

				setFlightZoneRadius(randomGenerator.nextInt(3)+1); // Generates integer between 1 and 3
				*/

				flightZoneRadius = Math.abs(Utility.randomNormal(8, 2)); // Generates integer between 2 and 6
				setHeading(Utility.random(360))
				anxietyLevel = 0
				anxietyThreshold = Utility.randomNormal(100, 20)
				sightRange = Utility.randomNormal(10, 5)
			}
			setDefaultShape(Herder, "person")
			createHerders(numHerders){
				setxy(randomPxcor(), randomPycor())
				//randomly orient cows
				size = 3
				//randomly place cows so they don't hit other objects
				while(count(inRadius(turtles(), 3))>1){
					setxy(randomPxcor(), randomPycor())
				}
			}
			
			
			}

	
		
	
		@Go
		def go(){
			ask(herders()){
				herd()
			}
			ask(cows()){
				step()
			}
		}

	 def remainingCows() {
		 count(cows())
	 }
	 def createFenceAroundField(){
		 int maxX = getMaxPxcor()
		 int maxY = getMaxPycor()
		 int minX  = getMinPxcor()
		 int minY = getMinPycor()
		 //iterate over all x values
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
			 //iterate over all y values
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
	 

}