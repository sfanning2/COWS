package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
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

class Herder extends ReLogoTurtle {
	def double visionRadius = 50
	def double speed = 1.0
	//	def double orientation
	def double width
	def double length
	//	def String currentTask
	def Role role = "Grouper" as Role
	def Cow targetedCow
	def PathFinder pathFinder

	enum Role {
		Grouper,
		Mover
	}

	def herd() {
		if (role == Role.Grouper) {
			if (false == groupCows()) {
				/* spend the turn switching roles */
				role = "Mover" as Role
			}
		}

		else
		{
			if (false == moveCows()) {
				/* spend the turn switching roles */
				role = "Grouper" as Role
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
		NdPoint goal = targetedCow.getTurtleLocation()
		targetedCow.patchHere().setPcolor(5)
		this.pathFinder.updateGoal((int)goal.x, (int)goal.y)
		this.pathFinder.setCurrentCows(this.getCowsInVision())
		this.pathFinder.replan();
		List<State> path = pathFinder.getdStarLitePF().getPath()
		/* do something with path */
		if (path.size() > 1) {
			State nextState = path.get(1)
			this.facexy(nextState.x, nextState.y)
			this.move(speed)
			this.patchHere().setPcolor(yellow())
		}
		return true
	}

	/**
	 * Try to move the group
	 * @return
	 */
	def moveCows() {
		/* get the center of the herd */
		def center = getCenter()
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

	/**
	 * @return up to three herders that are closest
	 * to this herder
	 */
	def getNearestHerders() {

	}

	/**
	 * 
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
		center.setPcolor(15)
		return new NdPoint(x,y)
	}

}
