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
			for (UserPatch p : patches()){
				p.pcolor = 62
			}
			setDefaultShape(Fence, "x")
			createFenceAroundField()
			
			setDefaultShape(Cow, "circle")
			
			createCows(numCows){
				setxy(randomPxcor(), randomPycor())
				//randomly place cows so they don't hit other objects
				while(count(other(turtlesHere()))>0){
					setxy(randomPxcor(), randomPycor())
				}
			}
			setDefaultShape(Herder, "person")
			createHerders(numHerders){
				setxy(randomPxcor(), randomPycor())
				//randomly place cows so they don't hit other objects
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
	 def createFenceAroundField(){
		 int maxX = getMaxPxcor()
		 int maxY = getMaxPycor()
		 int minX  = getMinPxcor()
		 int minY = getMinPycor()
		 //iterate over all x values
	 		for(int i=minX; i<= maxX; i++){
			 createFences(1){
				 setxy(i, minY)
			 }
			 createFences(1){
				 setxy(i, maxY)
			 }
		 } 
			 //iterate over all y values
			 for(int j=minY; j< maxY-10; j++){
				 createFences(1){
					 setxy(minX, j)
				 }
				 createFences(1){
					 setxy(maxX, j)
				 }
			 }
			 for(int k=maxY-10; k <= maxY; k++){
				 createFences(1){
					 setxy(minX, k)
			 }
			 }
	 }
	 

}