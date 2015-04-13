package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import org.jscience.mathematics.vector.DenseVector
import org.jscience.physics.amount.Amount;
import repast.simphony.relogo.AgentSet
import repast.simphony.relogo.Patch
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import repast.simphony.space.continuous.NdPoint
import bsh.This;
import cows.ReLogoTurtle;
import cows.dstarlite.State

import java.awt.Point
import java.util.List;
import javax.measure.VectorMeasure

import static javax.measure.unit.Unit.ONE;

class Herder extends ReLogoTurtle {
	def double visionRadius = 60
	def double speed = 3.0
	def double width
	def double length
	def Role role = "Grouper" as Role
	def Cow targetedCow
	def PathFinder pathFinder
	
	def boolean needsUpdate = true

	enum Role {
		Grouper,
		Mover
	}

	def herd() {
		if (role == Role.Grouper) {
			if (false == groupCows()) {
				/* spend the turn switching roles */
				role = "Mover" as Role
				setColor(135)
			}
		}

		else {
			if (false == moveCows()) {
				/* spend the turn switching roles */
				role = "Grouper" as Role
				setColor(95)
			}
		}
	}

	/**
	 * Gather straggling cows
	 * @return
	 */
	def boolean groupCows() {
		/* check that there are cows in vision */
		if (this.getCowsInVision().size() < 1) return false
		/* get the center of the herd */
		def center = getCenter()
		/* lock onto a straggler cow if necessary */
		/* In the future, add herder communication to prevent duplicate lock-ons */
		if (!targetedCow) {
			targetedCow = getFurthestCow(center)
			if (null == targetedCow) {
				// switch roles
				return false
			}
		}
		/* check if cow has joined group (and untarget cow if so)*/
		
		
		
		/* if it is near other cows and near the center */
		/* move toward appropriate placement around cow if necessary */
		/* for now just move towards the cow */
		NdPoint myLoc = this.getTurtleLocation();
		if (pathFinder == null) {
			pathFinder = new PathFinder(myLoc, myLoc)
		}else{
			pathFinder.getdStarLitePF().updateStart((int)myLoc.x, (int)myLoc.y)
		}

		NdPoint goal = Herder.getPositionToGroupCow(this.center, targetedCow.getTurtleLocation(), 5.0)
		//targetedCow.getTurtleLocation()
		//		targetedCow.patchHere().setPcolor(5)
		this.pathFinder.updateGoal((int)goal.x, (int)goal.y)
		this.pathFinder.setCurrentCows(this.getCowsInVision())
		this.pathFinder.replan();
		List<State> path = pathFinder.getdStarLitePF().getPath()
		/* do something with path */
		if (path.size() > 1) {
			State nextState = path.get(1)
			this.facexy(nextState.x, nextState.y)
			this.move(speed)
			//			this.patchHere().setPcolor(yellow())
		}
		return true
	}

	static def NdPoint getPositionToGroupCow(NdPoint dest, NdPoint cowLocation, double standingDist) {
		// Position like points in a line: Herder - Cow - Destination
		// Determine point 6ft from cow (-6ft along line)
		DenseVector destVector = DenseVector.valueOf( Amount.valueOf(dest.x, ONE),  Amount.valueOf(dest.y, ONE) )
		DenseVector cowVector = DenseVector.valueOf(  Amount.valueOf(cowLocation.x, ONE), Amount.valueOf(cowLocation.y, ONE))
		DenseVector<Amount> diffVector = cowVector.minus(destVector)
		
		VectorMeasure magCalculator = VectorMeasure.valueOf(diffVector.get(0).doubleValue(ONE), diffVector.get(1).doubleValue(ONE), ONE)
		double magnitude = magCalculator.doubleValue(ONE)
		
		
		DenseVector diffUnit = diffVector.times(Amount.valueOf(1.0/magnitude, ONE))		
		DenseVector standingDestVector = diffUnit.times(Amount.valueOf(standingDist, ONE))
		DenseVector locVector = cowVector.plus(standingDestVector)
		
		
		return new NdPoint(locVector.get(0).doubleValue(ONE), locVector.get(1).doubleValue(ONE))
	}


	/**
	 * Try to move the group
	 * @return
	 */
	def moveCows() {
		/* get center of group */
		def center = getCenter()
		/* cows in vision excluding straggler cow*/
		List<Cow> cowsInVisionNotTargetCow = this.getCowsInVision()
		/*check for straggler cows in vision and switch roles if necessary*/
		Cow furthestCow = getFurthestCow(center)
		if(furthestCow != null){
			//get herders in vision
			List<Herder> herders = getHerdersInVision()
			//this herders distance to straggler cow
			double distanceMeToCow = this.distance(furthestCow)
			double minDistanceHToCow = distanceMeToCow
			
			for(Herder h : herders){
				double distanceHToCow = h.distance(furthestCow)
				/* if another herder is not closer to cow  switch roles*/
				if(distanceHToCow < distanceMeToCow){
					//break since this herder will not switch roles
					minDistanceHToCow = distanceHToCow
					break
				}
			}
			/* if this herder is closest to straggler cow */
			if(minDistanceHToCow == distanceMeToCow){
				//switch roles
				return false
			}
			//remove target cow from array
			cowsInVisionNotTargetCow.remove(furthestCow)
		}
		/* want cows to move up and right towards goal location*/
		/* cow in furthest position on x-axis not straggler in vision*/
		Cow c = getCowToMove(this.getTurtleLocation())

		if(c==null){
			//couldn't find a good cow
			//set random position
			Integer x = new Integer(randomPxcor())
			Integer y = new Integer(randomPycor())
			while(count(patch(x,y).inRadius(herders(), 20))> 0 ){
				x = new Integer(randomPxcor())
				y = new Integer(randomPycor())
			}
			this.moveTo(patch(x, y))
		}else{
				double cowYPosition = c.getYcor()
				double cowXPosition = c.getXcor()
				int x = 0
				int y = 0
				
					//move cow up and right until it moves past next furthest x-cor cow
					//move cow right
					int distFromCow = 3
					double minusDist = Math.sqrt(Math.pow(distFromCow, 2)/2)
					//best place to move
					x = (int)cowXPosition - minusDist
					y = (int)cowYPosition - minusDist
					
		
					if(x < getMinPxcor()){
						x = getMinPxcor()
					}
					if(y < getMinPycor()){
						y = getMinPycor()
					}
					//get patch closest 
					Integer intx = new Integer(x)
					Integer inty = new Integer(y)
					Patch p = patch(intx, inty)
					System.out.println(p)
					
		
		NdPoint myLoc = this.getTurtleLocation();
		if (pathFinder == null) {
			pathFinder = new PathFinder(myLoc, myLoc)
		}else{
			pathFinder.getdStarLitePF().updateStart((int)myLoc.x, (int)myLoc.y)
		}
		NdPoint point = new NdPoint(getMaxPxcor(), getMaxPycor()-5)
		
		NdPoint goal = Herder.getPositionToGroupCow(point, c.getTurtleLocation(), 5.0)
		System.out.println(goal)
//		targetedCow.getTurtleLocation()
//		targetedCow.patchHere().setPcolor(5)
//		this.pathFinder.updateGoal((int)goal.x, (int)goal.y)
//		this.pathFinder.setCurrentCows(this.getCowsInVision())
//		this.pathFinder.replan();
//		List<State> path = pathFinder.getdStarLitePF().getPath()
//		/* do something with path */
//		if (path.size() > 1) {
//			State nextState = path.get(1)
//			this.facexy(nextState.x, nextState.y)
//			this.move(speed)
//		//			this.patchHere().setPcolor(yellow())
//		}
	this.moveTo(p)
	}
		return true
		/* try to position self behind the herd */
		/* TODO */
	}

	def NdPoint getCenter() {
		NdPoint myCenter = getHerdCenter()
		List<NdPoint> centers = communicateCenters()
		centers.add(myCenter)
		NdPoint center = computeCenter(centers)
		return center
	}

	/**
	 * Get the cows in the herder's field of vision
	 * @return All cows in the herder's vision
	 */
	def List<Cow> getCowsInVision() {
		AgentSet cows = this.inRadius(cows(), visionRadius)
		return (ArrayList<Cow>) cows.clone()
	}

	/**
	 * Grouper method
	 * @param cows
	 * @return The cow furthest from the center of the group
	 */
	def Cow getFurthestCow(NdPoint point) {
		Patch myLoc = this.patch(point.x, point.y)
		List<Cow> cowsInVision = this.getCowsInVision()

		Cow farCow = maxOneOf ( cowsInVision ){ p -> distance (myLoc) }

		return farCow
	}
	def Cow getCowToMove(NdPoint point){
		Patch myLoc = this.patch(point.x, point.y)
		List<Cow> cowsInVision = this.getCowsInVision()
		double x = 10000
		Cow bestCow = null // best cow has no herders near it

		for(Cow c : cowsInVision){
			//first cow which doesn't have closer herder and herder at least 20 ft away
			// get herders within 20 ft of cow
			AgentSet herdersNearCow  = c.inRadius(herders(), 20)
			if(count(herdersNearCow) <=  1){
				bestCow = c
				return bestCow
			}
		}
		//herders within 20 ft of all the cows so move elsewhere
		
		//find cow with different position
		
		return null
	}
	/**
	 * Get herders in this herder's field of vision
	 * @return All herders in the herder's vision
	 */
	def List<Herder> getHerdersInVision() {
	AgentSet herders = this.inRadius(herders(), visionRadius)
	ArrayList<Herder> h = (ArrayList<Herder>) herders.clone()
	h.remove(this)
	return h
	}
	/**
	 * TODO
	 * @return up to three herders that are closest
	 * to this herder
	 */
	def getNearestHerders() {

	}

	/**
	 * TODO
	 * @return Set of coordinates representing the center
	 * of the herd as seen by the individual herders
	 */
	def List<NdPoint> communicateCenters() {
		return new ArrayList<NdPoint>();
	}

	/**
	 * 
	 * @return The center of the herd as determined by this herder
	 * 
	 */
	def NdPoint getHerdCenter() {
		/* average the cow locations */
		List<Cow> cows = this.getCowsInVision()
		List<NdPoint> locs = new ArrayList();
		double sX = 0;
		double sY = 0;
		for (Cow cow : cows) {
			locs.add(cow.getTurtleLocation());
		}
		return computeCenter(locs)
	}

	/**
	 * Compute the center of a set of points
	 * @param points A list of points, must have at least one member
	 * @return the center points
	 */
	NdPoint computeCenter(List<NdPoint> points) {
		if (points.size() <= 0) {
			throw new IllegalArgumentException("Must supply at least one point")
		}
		double sumX = 0.0
		double sumY = 0.0
		for (NdPoint point : points) {
			sumX += point.x
			sumY += point.y
		}
		double x = sumX/(double)points.size()
		double y = sumY/(double)points.size()
		Patch center = (this.patch(x,y))
		//		center.setPcolor(15)
		return new NdPoint(x,y)
	}

}
