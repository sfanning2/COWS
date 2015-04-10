package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import cows.ReLogoObserver;

class UserObserver extends ReLogoObserver{

	
	 /*Observer methods*/

		@Setup
		def setup(){
			clearAll()
			setDefaultShape(Cow, "circle")
			
			createCows(numCows){
				//won't get on top of other turtles
				while(count(other(turtlesHere()))>0){
					setxy(randomPxcor(), randomPycor())
				}
				flightZoneRadius = Utility.random(5) + 2; // Generates integer between 2 and 6
				anxietyLevel = 1
				anxietyThreshold = Utility.random(30)+ 21
			}
			setDefaultShape(Herder, "person")
			createHerders(numHerders){
				//won't get on top of other turtles
				while(count(other(turtlesHere()))>0){
					setxy(randomPxcor(), randomPycor())
				}
			}
			
			setDefaultShape(Tree, "tree")
			createTrees((int)(obstacleDensity*fieldArea/100)){
				while(count(other(turtlesHere()))>0){
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
	 

}