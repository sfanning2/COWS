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
import java.util.Random

import static javax.measure.unit.Unit.ONE;
/**
 * Object to represent herder. Performs functions to herd cows by grouping and/or moving a group of them to end location.
 * @author Lynnea
 *
 */
class Herder extends ReLogoTurtle {
	def double visionRadius = 50
	def double speed = 3.0
	def double width
	def double length
	def double communicationRadius = 40
	def Role role
	def Cow targetedCow
	def Cow previousTargetedCow
	def PathFinder pathFinder
	def int numInteractionsTargetedCow
	def double groupSize = 10
	def grouperTicks = 0
	private static final int GROUPER_TIMEOUT = 100
	private static final double HERD_RADIUS = 20.0
	def AgentSet herdersInCommRange = null

	enum Role {
		Grouper,
		Mover
		// Add Wanderer role for cow discovery? OR add wandering to grouper/mover roles?
	}
	/**
	 * Configures a herder's role in moving cows at a certain point in time.
	 * @return void
	 */
	def herd() {

		herdersInCommRange = this.inRadius(herders(), communicationRadius)
		/*if in grouper role and doesn't meet grouper requirements he switches to a mover*/
				if (role == Role.Grouper) {
					if (false == groupCows()) {
						/* spend the turn switching roles */
						role = "Mover" as Role
						setColor(135)
					}
				}
		/*if in a mover role and doesn't meet role requirements he switches to a grouper*/
				else {
					if (false == moveCows()) {
						/* spend the turn switching roles */
						role = "Grouper" as Role
						setColor(95)
					}
				}
	}

	/**
	 * Gather straggling cows together
	 * @return boolean if successful in grouping cows
	 */
	def boolean groupCows() {
		/* update ticks */
		grouperTicks++
		/* check that there are cows in vision */
		List<Cow> cowsInVision = this.getCowsInVision()
		if (cowsInVision.size() < 1) return false
		/* get the center of the herd */
		NdPoint centerLocation = getCenter(cowsInVision)
		NdPoint cowLocation = null;
		if(targetedCow) {
			/* check for timeout */
			cowLocation = targetedCow.getTurtleLocation()

			/* check if cow has joined group (and untarget cow if so)*/
			double xDiff = (double)(cowLocation.x - centerLocation.x)
			double yDiff = (double)(cowLocation.y - centerLocation.y)

			if(Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff,2)) < HERD_RADIUS) {
				targetedCow = null
			}
			if (!cows().contains(targetedCow)) {
				targetedCow = null
			}
			//timeout after interact with same cow frequently
			if (grouperTicks > GROUPER_TIMEOUT) {
				targetedCow = null
				return false 	// switch roles
			}
		}

		/* lock onto a straggler cow if necessary */
		if (!targetedCow) {

			targetedCow = getStragglingCow(centerLocation, centerLocation, cowsInVision )
			grouperTicks = 0

			if (null == targetedCow) {
				// switch roles
				return false
			}
						cowLocation = targetedCow.getTurtleLocation()
						double xDiff = (double)(cowLocation.x - centerLocation.x)
						double yDiff = (double)(cowLocation.y - centerLocation.y)
						/*check if targetedCow is part of a group*/
						if(Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff,2)) < groupSize) {
							targetedCow = null
							//switch roles
							return false
						}

		}
		cowLocation = targetedCow.getTurtleLocation()
		/* if it is near other cows and near the center */
		/* move toward appropriate placement around cow if necessary */
		/* for now just move towards the cow */
		NdPoint myLoc = this.getTurtleLocation();

		/* get goal*/

		NdPoint goal = Herder.getPositionToGroupCow(centerLocation, cowLocation, 5.0)
		goal = this.makeBoundedPoint(goal)
		/*utilize a pathFinder to find best route to a cow to avoid hitting other agents or obstacles*/
		if (pathFinder == null) {
			pathFinder = new PathFinder(myLoc, goal)
			ArrayList<ReLogoTurtle> fences = fences()
			int blocked = -1
			if (fences != null) {
				pathFinder.setTurtles(fences, blocked)
			}
			ArrayList<ReLogoTurtle> trees = trees()
			if (trees != null) {
				pathFinder.setTurtles(trees, blocked)
			}
			pathFinder.setBorders(getMinPxcor(), getMaxPxcor(), getMinPycor(), getMaxPycor())
		}else{
			pathFinder.getdStarLitePF().updateStart((int)myLoc.x, (int)myLoc.y)
			this.pathFinder.updateGoal((int)goal.x, (int)goal.y)
		}	
		
		this.pathFinder.setCurrentCows(this.getCowsInVision())	// best to call set current cows last
		this.pathFinder.replan();
		List<State> path = pathFinder.getdStarLitePF().getPath()
		/*move between states on the path avoiding other agents and obstacles */
		int i = 1;
		while(path.size() > i && i <= speed) {
			State nextState = path.get(i)
			Patch movePatch = this.patch(nextState.x, nextState.y)
			if(movePatch != null && this.turtlesOn(movePatch).size() == 0) {
				this.moveTo(movePatch)
			} else {
				break
			}
			i++
		}

		return true
	}
	/**
	 * Creates and return a point bounded by field size
	 * @param point
	 * @return NdPoint that is bounded
	 */
	def NdPoint makeBoundedPoint(NdPoint point) {
		int minX = getMinPxcor()
		int maxX = getMaxPxcor()
		int minY = getMinPycor()
		int maxY = getMaxPycor()

		int newX = point.x
		int newY = point.y

		if (Math.floor(newX) <= minX) {
			newX = minX + 1
		}
		if (Math.ceil(newX) >= maxX) {
			newX = maxX - 1
		}
		if (Math.floor(newY) <= minY) {
			newY = minY + 1
		}
		if (Math.ceil(newY) >= maxY) {
			newY = maxY - 1
		}

		return new NdPoint(newX, newY)
	}
	/**
	 * Get optimal position for herder to go in order to group a cow at a certain location
	 * @param dest
	 * @param cowLocation
	 * @param standingDist
	 * @return NdPoint with optimal position
	 */
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
	 * @return boolean as to success in moving cow
	 */
	def boolean moveCows() {
		/* get center of group */
		def center = getCenter(this.getCowsInVision())
		/*check for straggler cows in vision and switch roles if necessary*/
		Cow straggler = getStragglingCow(center, center, this.getCowsInVision()) 
		if(straggler != null){
			//swtich to grouping role
			return false
		}

		/* want cows to move up and right towards goal location*/
		/* cow in furthest position on x-axis not straggler in vision*/
		List<Cow> groupOfCows = new ArrayList<Cow>()
		for(Cow c : this.getCowsInVision()){
			groupOfCows.add(c)
		}

		targetedCow = getPressurePointOfGroup(groupOfCows)
		if(numInteractionsTargetedCow > groupSize && targetedCow==previousTargetedCow){
			/*this is a cow we haven't interacted with in previous step*/
			groupOfCows.remove(targetedCow)
			targetedCow = getPressurePointOfGroup(groupOfCows)
		}
		if(targetedCow == null){
			/* no targeted cow so move to a random spot on field*/
			Patch randomPatch = patchAtHeadingAndDistance(Utility.random(360), 5)
			while((count(turtlesOn(randomPatch))> 0 )&&  (count(randomPatch.inRadius(this.getHerdersInVision(), 20)) > 0)){
				/*try find better patch on which no other obstacles or agents are*/
				randomPatch = patchAtHeadingAndDistance(Utility.random(360), 5)
			}
			targetedCow = null
			numInteractionsTargetedCow = 0
			NdPoint goal = new NdPoint(new Integer(randomPatch.getPxcor()), new Integer(randomPatch.getPycor()))
			NdPoint current = new NdPoint(new Integer(this.getPxcor()), new Integer(this.getPycor()))
			/*use path finding to walk to goal point*/
			walkToPoint(goal, current)
			
		}else{
		/*increase interactions with cow if recently visited them*/
		/*herder doesn't want to interact with a cow too much for fear of making them very anxious */
			if(targetedCow==previousTargetedCow){

				numInteractionsTargetedCow++
			}else{

				numInteractionsTargetedCow = 1
			}
			previousTargetedCow = targetedCow
			/*posiiton of cow*/
			double cowYPosition = targetedCow.getYcor()
			double cowXPosition = targetedCow.getXcor()
			int x = 0
			int y = 0

			int distFromCow = 8
			double minusDist = Math.sqrt(Math.pow(distFromCow, 2)/2)//best place to move to bottom left of cow
			
			x = (int)cowXPosition - minusDist
			y = (int)cowYPosition - minusDist


			if(x < getMinPxcor()){
				x = getMinPxcor()
			}
			if(y < getMinPycor()){
				y = getMinPycor()
			}
			/*get patch closest to optimal position for moving cow*/
			Integer intx = new Integer(x)
			Integer inty = new Integer(y)
			Patch initialP = patch(intx, inty)
			Patch p = null
			/*if optimal patch is occupied move elsewhere in neighborhood*/
			if(count(turtlesOn(initialP))>0){
				//look at patches surrounding cow to see if open
				for( Patch sp : targetedCow.patchHere().neighbors()){
					if(count(turtlesOn(sp)) == 0){
						p = sp
						break
					}
				}
			}else{
				p = initialP
			}
			if(p != null){
				/*move to goal and face exit point*/
				NdPoint goal = new NdPoint(new Integer(p.getPxcor()), new Integer(p.getPycor()))
				NdPoint current = new NdPoint(new Integer(this.getPxcor()), new Integer(this.getPycor()))
				walkToPoint(goal, current)
				
				//goal patch
				Integer xx = new Integer(getMaxPxcor())
				Integer yy = new Integer(getMaxPycor()-2)

				double heading = this.towardsxy((Number)xx, (Number)yy)
				//move inside flight zone heading toward goal
				this.setHeading(heading)
				this.facexy(xx, yy)
				/*move distance of 4 towards the cow in the direction of exit point*/
				Patch finalpatch = patchAtHeadingAndDistance(heading, 4)
				NdPoint finalp = new NdPoint(new Integer(p.getPxcor()), new Integer(p.getPycor()))
				NdPoint current2 = new NdPoint(new Integer(this.getPxcor()), new Integer(this.getPycor()))
				walkToPoint(finalp, current2)
			}else{
				/*if can't find a better patch close to cow move to new spot*/
				p = patchAtHeadingAndDistance(Utility.random(360), 5)
				while(count(turtlesOn(p))> 0){
					//try find better patch
					p = patchAtHeadingAndDistance(Utility.random(360), 5)
				}
				
				NdPoint goal = new NdPoint(new Integer(p.getPxcor()), new Integer(p.getPycor()))
				NdPoint current = new NdPoint(new Integer(this.getPxcor()), new Integer(this.getPycor()))
				walkToPoint(goal, current)
				
			}



		}
		return true
	}
	/**
	 * Find center point within a set of cows
	 * @param cows for which we want a center
	 * @return NdPoint representing center
	 */
	def NdPoint getCenter(List<Cow> cows) {
		NdPoint myCenter = getHerdCenter(cows)
		List<NdPoint> centers = communicateCenters()
		NdPoint center;
		if (myCenter != null)
			centers.add(myCenter)
		if (centers.size() > 0) {
			center = computeCenter(centers)
		} else {
			center = new NdPoint(0,0)//using 0,0 as center
		}
		return center
	}

	/**
	 * Get the cows in the herder's field of vision
	 * @return All cows in the herder's vision
	 */
	def List<Cow> getCowsInVision() {
		AgentSet cows = this.inRadius(cows(), visionRadius)
		return cows
	}

	/**
	 * Grouper method
	 * TODO: avoid multiple lock-ons and avoid locking onto
	 * cows who are already "grouped"
	 * @param cows
	 * @return The cow furthest from the center of the group
	 */
	def Cow getFurthestCow(NdPoint point, List<Cow> cowsInVision) {
		Patch myLoc = this.patch(point.x, point.y)

		Cow farCow = maxOneOf ( cowsInVision ){ p -> distance (myLoc) }

		return farCow
	}
	/**
	 * Get cow farthest from group center that hasn't been picked up by another herder
	 * @param cows within vision of herder
	 * @return Cow best furthest from group center
	 */
	def Cow getBestStraggler(NdPoint point, List<Cow> cowsInVision){
		Patch myLoc = this.patch(point.x, point.y)
		Cow straggler = null
		List<Cow> targetedCowsNeighbors = new ArrayList<Cow>()
		/* get cows who are targets of neighboring herders */
		for(Herder h : this.getHerdersInVision()){
			if(h.targetedCow != null){
				targetedCowsNeighbors.add(h.targetedCow)
			}
		}

		/*best position is cow with smallest combination of x and y coordinates*/
		double heuristic = 0
		for(Cow c : cowsInVision){
			if(c.distance(myLoc) > heuristic && c.distance(myLoc) > groupSize){
				boolean hasHerder = false
				for(Cow tc : targetedCowsNeighbors){
					if(tc == c){
						hasHerder = true
					}
				}
				if(!hasHerder){
					heuristic = c.distance(myLoc)
					straggler = c
				}
			}

		}
		return straggler
	}
	
	/**
	 * Get a list of cows targeted by other herders
	 * Duplicates are possible
	 * The list may be empty
	 * @param cows non-null list of cows
	 * @return list of targeted cows
	 */
	def List<Cow> getCowsTargetedByNearbyHerders() {
		ArrayList<Cow> targets = new ArrayList<Cow>();
		if (herdersInCommRange != null) {
			for (Herder herder : herdersInCommRange) {
				Cow target = herder.getTargetedCow()
				if (target != null) {
					targets.add(target)//add target to list
				}
			}
		}
		return targets
	}
	
	/**
	 * Get cows not currently targeted by another herder and not close enough
	 * to the center of the group
	 * @param point
	 * @param groupCenter
	 * @param cowsInVision
	 * @return
	 */
	def Cow getStragglingCow(NdPoint point, NdPoint groupCenter, List<Cow> cowsInVision) {
		Patch centerPatch = this.patch(groupCenter.x, groupCenter.y)
		/* gets cows in vision and removes those already grouped and those with herders targeting them*/
		List<Cow> cowsInGroup = centerPatch.inRadius(cowsInVision, HERD_RADIUS)
		List<Cow> targetedCows = this.getCowsTargetedByNearbyHerders()
		List<Cow> stragglingCows = new ArrayList(cowsInVision)
		stragglingCows.removeAll(cowsInGroup)
		stragglingCows.removeAll(targetedCows)
		return this.getFurthestCow(point, stragglingCows)
	}

	/**
	 * Get best pressure point Cow for group of cows 
	 * @return Cow at best position
	 */
	def Cow getPressurePointOfGroup(List<Cow> group){
		Cow pressureCow = null
		List<Cow> targetedCowsNeighbors = new ArrayList<Cow>()
		/* for herders in vision add cows who have a herder targeting them */
		for(Herder h : this.getHerdersInVision()){
			if(h.targetedCow != null){
				targetedCowsNeighbors.add(h.targetedCow)
			}
		}

		/*best position is cow with smallest combination of x and y coordinates*/
		double heuristic = 9999
		for(Cow c : group){
			if(c.getXcor() + c.getYcor() < heuristic){
				boolean hasHerder = false
				/*only add if doesn't have a herder within communication distance*/
				for(Cow tc : targetedCowsNeighbors){
					if(tc ==c){
						hasHerder = true
					}
				}
				if(!hasHerder){
					heuristic = c.getXcor() + c.getYcor()
					pressureCow = c
				}
			}

		}

		return pressureCow
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
	 * Get herders in communication distance of this herder
	 * @return All herders within communication distance
	 */
	def List<Herder> getHerdersInCommunicationArea(){
		AgentSet herders = this.inRadius(herders(), communicationRadius)
		ArrayList<Herder> h = (ArrayList<Herder>) herders.clone()
		h.remove(this)
		return h
	}
	/**
	 * Have herder walk from current position to goal via path 
	 * @param goalPosition
	 * @param currentPosition
	 */
	def walkToPoint(NdPoint goalPosition, NdPoint currentPosition){
		/*make sure goal position is within bounds of field*/
		goalPosition = this.makeBoundedPoint(goalPosition)
		/* create path avoiding obstacles and other agents*/
		if (pathFinder == null) {
			pathFinder = new PathFinder(currentPosition, goalPosition)
			ArrayList<ReLogoTurtle> fences = fences()
			int blocked = -1
			if (fences != null) {
				pathFinder.setTurtles(fences, blocked)
			}
			ArrayList<ReLogoTurtle> trees = trees()
			if (trees != null) {
				pathFinder.setTurtles(trees, blocked)
			}
			pathFinder.setBorders(getMinPxcor(), getMaxPxcor(), getMinPycor(), getMaxPycor())
		}else{
			pathFinder.getdStarLitePF().updateStart((int)currentPosition.x, (int)currentPosition.y)
			this.pathFinder.updateGoal((int)goalPosition.x, (int)goalPosition.y)

		}

		this.pathFinder.setCurrentCows(this.getCowsInVision())
		this.pathFinder.replan();
		List<State> path = pathFinder.getdStarLitePF().getPath()
		/* move along states in path if possible */
		int i = 1;
		while(path.size() > i && i <= speed) {
			State nextState = path.get(i)
			Patch movePatch = this.patch(nextState.x, nextState.y)
			if(movePatch != null && this.turtlesOn(movePatch).size() == 0) {
				this.moveTo(movePatch)
			} else {
				break
			}
			i++
		}
	}
	
	

	/**
	 * TODO
	 * @return Set of coordinates representing the center
	 * of the herd as seen by the individual herders
	 */
	def List<NdPoint> communicateCenters() {
		ArrayList centers = new  ArrayList<NdPoint>()
		if (herdersInCommRange != null) {
			for (Herder herder : herdersInCommRange) {
				NdPoint center = herder.getHerdCenter()
				if (center != null) {
					centers.add(herder.getHerdCenter())
				}
			}
		}
		return centers
	}

	/**
	 * 
	 * @return The center of the herd as determined by this herder
	 * 
	 */
	def NdPoint getHerdCenter(List<Cow> cows) {
		/* average the cow locations */
		List<NdPoint> locs = new ArrayList();
		double sX = 0;
		double sY = 0;
		for (Cow cow : cows) {
			locs.add(cow.getTurtleLocation());
		}
		if (locs.size() > 0)
			return computeCenter(locs)
		else
			return null
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
		/* average points to compute center*/
		double x = sumX/(double)points.size()
		double y = sumY/(double)points.size()
		Patch center = (this.patch(x,y))
		//		center.setPcolor(15)
		return new NdPoint(x,y)
	}
}
