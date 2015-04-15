
package cows.relogo;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.AgentSet;
import repast.simphony.relogo.Patch;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import cows.ReLogoObserver;
import cows.relogo.Herder.Role;
import java.util.Random;

public class UserObserver extends ReLogoObserver{

	/*Observer methods*/


	@Setup
	public void setup(){
		clearAll();
		int numCows = 10;
		double obstacleDensity = .1;
		int numHerders = 10;
		
		Random randomGenerator = new Random();
		for (Patch p : patches()){;
			p.setPcolor(62);
		}
		createFenceAroundField();
		for(Fence f : fences()){
			f.setShape("x");
		}
		double sqarea = worldHeight()*worldWidth();
		int numTrees =  (int)(obstacleDensity *sqarea / 36);
		
		createTrees(numTrees);
		for(Tree t : trees()){
			t.setShape("tree");
			t.setSize(3);
			while(count(t.inRadius(turtles(), 6))>1){
				t.setxy(randomPxcor(), randomPycor());
			}
		}

		createCows(numCows);
		for(Cow c: cows()){
			c.setxy(randomPxcor(), randomPycor());
			c.setSize(6);
			c.setShape("fish");
			//randomly place cows so they don't hit other objects

			while(count(c.inRadius(turtles(), 6))>1){
				c.setxy(randomPxcor(), randomPycor());
			}
			
			c.setFlightZoneRadius(6.0);
			c.setHeading(Utility.random(360));
			c.setAnxietyLevel(0);
			c.setAnxietyThreshold(Utility.randomNormal(100, 20));
			c.setSightRange(Utility.randomNormal(15, 5));
			c.setIndependenceLevel(Utility.random(0.05)); //Percent of the time the cow will group, b/w 5 and 0 %		
		}
		createHerders(numHerders);
		for(Herder h : herders()){
			h.setShape("person");
			h.setxy(randomPxcor(), randomPycor());
			//randomly orient cows
			h.setSize(3);
			//randomly place cows so they don't hit other objects
			while(count(h.inRadius(turtles(), 3))>1){
				h.setxy(randomPxcor(), randomPycor());
			}
			double roleNum = Utility.random(1);
			if(roleNum < 0.5){
				//set as mover
				h.setRole(Role.Mover);
				h.setColor(135);
			}else{
				//set as grouper
				h.setRole(Role.Grouper);
				//setRole(Role.Grouper)
				h.setColor(95);
			}

		}


	}




//	@Go
//	public void go(){
//		ask(herders()){ step(); }
//		ask(cows()){ step(); }
//	}

	public int remainingCows() {
		return count(cows());
	}
	public void createFenceAroundField(){
		int maxX = getMaxPxcor();
		int maxY = getMaxPycor();
		int minX  = getMinPxcor();
		int minY = getMinPycor();
		//iterate over all x values
		for(int i=minX; i<= maxX; i++){
			Fence f = new Fence();
			f.setxy(i, minY);
			f.setColor(60);
			this.getContext().add(f);
			Fence f1 = new Fence();
			f1.setxy(i, maxY);
			f1.setColor(60);
			this.getContext().add(f1);
		}
		//iterate over all y values
		for(int j=minY; j< maxY-10; j++){
			Fence f = new Fence();
			f.setxy(minX, j);
			f.setColor(60);
			this.getContext().add(f);
			Fence f1 = new Fence();
			f1.setxy(maxX, j);
			f1.setColor(60);
			this.getContext().add(f1);
		}
		for(int k=maxY-10; k <= maxY; k++){
			Fence f = new Fence();
			f.setxy(minX, k);
			f.setColor(60);
			this.getContext().add(f);
		}
	}
	public AgentSet<Herder> getGroupers(){
		AgentSet<Herder> clone = (AgentSet<Herder>) herders().clone();
		for(Herder h: clone){
			if(h.role == Role.Grouper){
				
			}else{
				clone.remove(h);
			}
		}
		return clone;
	}
	public AgentSet<Herder> getMovers(){
		AgentSet<Herder> clone = (AgentSet<Herder>)herders().clone();
		for(Herder h: clone){
			if(h.role == Role.Mover){
				
			}else{
				clone.remove(h);
			}
		}
		return clone;
	}

}