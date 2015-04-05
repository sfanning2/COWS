package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import cows.ReLogoTurtle;
import java.awt.Point

class Herder extends ReLogoTurtle {
	def double visionRange = 5
	def double speed
	def double orientation
	def double width
	def double length
	def String currentTask
	def Role role
	def Cow targetedCow
	
	enum Role {
		Grouper,
		Mover
	}
	
	def herd() {
		if (role == Grouper) {
			if (false == groupCows()) {
				/* spend the turn switching roles */
				role = "Mover" as Role
			}
		}
	
		else (role == Mover)
		{
			if (false == moveCows()) {
				/* spend the turn switching roles */
				role = "Grouper" as Role
			}
		}
	} 
	
	def groupCows() {
		/* get the center of the herd */
		def center = getCenter()
		/* lock onto a straggler cow if necessary */
		/* In the future, add herder communication to prevent duplicate lock-ons */
		if (!targetedCow) {
			getFurthestCow()
		}
		
	}
	
	
	
	def moveCows() {
		
	}
	
	def getCenter() {
		Point myCenter = getHerdCenter()
		List<Point> otherCenters = communicateCenters()
	}
	
	/**
	 * 
	 * @return All cows in the herder's vision
	 */
	def getCowsInVision() {
		
	}
	
	/**
	 * Grouper method
	 * @param cows
	 * @return The cow furthest from the center of the group
	 */
	def getFurthestCow() {
		
	}
	
	/**
	 * @return up to three herders that are closest
	 * to this herder
	 */
	def getNearestHerders() {
		
	}
	
	/**
	 * Find the shortest path to a cow
	 * @param cow
	 * @return a path
	 */
	def getShortestPath(Cow cow) {
		
	}
	
	/**
	 * 
	 * @return Set of coordinates representing the center
	 * of the herd as seen by the individual herders
	 */
	def communicateCenters() {
		
	}
	
	/**
	 * 
	 * @return The center of the herd as determined by this herder
	 * 
	 */
	def getHerdCenter() {
		/* average the cow locations */
		getCowsInVision()
	}
	
	/**
	 * Compute the center of a set of points
	 * @param points A list of points, must have at least one member
	 * @return the center points
	 */
	Point computeCenter(List<Point> points) {
		if (points.size() <= 0) {
			throw new IllegalArgumentException("Must supply at least one point")
		}
		double sumX = 0
		double sumY = 0
		for (Point point : points) {
			sumX += point.x
			sumY += point.y
		}
		return new Point(sumX/points.size(),sumY/points.size())
	}
	
}
