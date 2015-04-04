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
				setxy(randomXcor(), randomYcor())
			}
			setDefaultShape(Herder, "person")
			createHerders(numHerders){
				setxy(randomXcor(), randomYcor())
			}
		}
		
	
		@Go
		def go(){
			ask(herders()){
				left(random(90))
				right(random(90))
				forward(random(10))
			}
			ask(cows()){
				left(random(90))
				right(random(90))
				forward(random(10))
			}
		}

	 def remainingCows() {
		 count(cows())
	 }
	 

}