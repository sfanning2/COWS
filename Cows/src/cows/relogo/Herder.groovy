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
			}
		}

		else {
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
		NdPoint centerLocation = getCenter()
		NdPoint cowLocation = null;
		if(targetedCow) {
			cowLocation = targetedCow.getTurtleLocation()

			/* check if cow has joined group (and untarget cow if so)*/
			double xDiff = (double)(cowLocation.x - centerLocation.x)
			double yDiff = (double)(cowLocation.y - centerLocation.y)
			if(Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff,2)) < 10.0) {
				targetedCow = null
			}
		}

		/* lock onto a straggler cow if necessary */
		/* In the future, add herder communication to prevent duplicate lock-ons */
		if (!targetedCow) {
			targetedCow = getFurthestCow(center)
			if (null == targetedCow) {
				// switch roles
				return false
			}
		}
		cowLocation = targetedCow.getTurtleLocation()
		/* if it is near other cows and near the center */
		/* move toward appropriate placement around cow if necessary */
		/* for now just move towards the cow */
		NdPoint myLoc = this.getTurtleLocation();
		if (pathFinder == null) {
			pathFinder = new PathFinder(myLoc, myLoc)
		}else{
			pathFinder.getdStarLitePF().updateStart((int)myLoc.x, (int)myLoc.y)
		}

		NdPoint goal = Herder.getPositionToGroupCow(centerLocation, cowLocation, 5.0)
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
		/* get the center of the herd */
		def center = getCenter()
		/* try to position self behind the herd */
		/* TODO */
	}

	def NdPoint getCenter() {
		//		NdPoint myCenter = getHerdCenter()
		//		List<NdPoint> centers = communicateCenters()
		//		centers.add(myCenter)
		//		NdPoint center = computeCenter(centers)
		//		return center
		return new NdPoint(0,0)
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
	 * TODO: avoid multiple lock-ons and avoid locking onto
	 * cows who are already "grouped"
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
