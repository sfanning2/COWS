package cows.relogo

import cows.ReLogoTurtle
import cows.dstarlite.DStarLite
import java.awt.Point
import repast.simphony.relogo.Patch
import repast.simphony.space.continuous.NdPoint

class PathFinder {
	
	def cowAvoidanceRadius = 4
	
	def DStarLite dStarLitePF

	def List<Cow> currentCows
	def List<NdPoint> prevCowLocations

	def List<Herder> currentHerders
	def List<NdPoint> prevHerderLocations

	/**
	 * Object that aids in finding optimal path from start to goal
	 * @param start
	 * @param goal
	 */
	PathFinder(NdPoint start, NdPoint goal) {
		this.dStarLitePF = new DStarLite()
		dStarLitePF.init((int)start.x, (int)start.y, (int)goal.x, (int)goal.y)

	}

	/**
	 * Block cow vertices
	 * @param currentCows
	 */
	void setCurrentCows(List<Cow> currentCows) {
		/* update cells for old cows */
		if (this.prevCowLocations != null)
			for (NdPoint location : this.prevCowLocations) {
				this.updateCell((int)location.x, (int)location.y, 1)
			}
		this.prevCowLocations = null
		/* update cells for new cows */
		/* base off current location and goal */
		NdPoint goal = new NdPoint(this.dStarLitePF.getS_goal().x, this.dStarLitePF.getS_goal().y);
		NdPoint current = new NdPoint(this.dStarLitePF.getS_last().x, this.dStarLitePF.getS_last().y);
		
		this.currentCows = currentCows
		if (currentCows != null && currentCows.size() > 0) {
			this.prevCowLocations = new ArrayList()
			for (Cow cow : currentCows) {
				NdPoint location = cow.getTurtleLocation();
				double x = location.x
				double y = location.y
				boolean avoid = false
				
				/* check if the cow is potentially in the way:
				 * if x is between current location and  goal with a padding of radius 
				 * and y is between current location and goal with a padding of radius
				 * then consider the cow to be a potential obstacle to avoid. */
				double r = cowAvoidanceRadius
				if(((x <= goal.x + r && x >= current.x - r) || (x >= goal.x - r && x <= current.x + r)) &&
				   ((y <= goal.y + r && y >= current.y - r) || (y >= goal.y - r && y <= current.y + r))){
					avoid = true
				}
				
				if (avoid) {
					/* update area around cow */
					List<Patch> neighboringPatches = cow.patch(x,y).inRadius(cow.patches(), cowAvoidanceRadius)	
					for (Patch patch : neighboringPatches) {
						NdPoint neighborLocation = new NdPoint(patch.getPxcor(), patch.getPycor())
						this.prevCowLocations.add(neighborLocation)
						this.updateCell((int)neighborLocation.x, (int)neighborLocation.y, 5)	// Cost of 5 discourages walking here
					}
					
					/* update cow */
					this.prevCowLocations.add(new NdPoint(x, y))
					this.updateCell((int)x, (int)y, -1)
				}
				
			}
		}
	}

	/**
	 * Block herder vertices
	 * @param currentHerders
	 */
	void setCurrentHerders(List<Herder> currentHerders) {
		/* update cells for old herders */
		if (this.prevHerderLocations != null)
			for (NdPoint location : this.prevHerderLocations) {
				this.updateCell((int)location.x, (int)location.y, 1)
			}
		this.prevHerderLocations = null
		/* update cells for new herders */
		this.currentHerders = currentHerders
		if (currentHerders != null && currentHerders.size() > 0) {
			this.prevHerderLocations = new ArrayList();
			for (Herder herder : currentHerders) {
				NdPoint location = herder.getTurtleLocation();
				double x = location.x
				double y = location.y
				this.prevHerderLocations.add(new NdPoint(x, y))
				this.updateCell((int)x, (int)y, -1)
			}
		}
	}
	/**
	 * Set borders for cells
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 */
	public void setBorders(int minX, int maxX, int minY, int maxY){
		// TODO
		for(int i = minX; i < maxX; i++) {
			this.updateCell((int)i, (int)minY, -1)
			this.updateCell((int)i, (int)maxY, -1)
		}
		for(int i = minY; i < maxY; i++) {
			this.updateCell((int)minX, (int)i, -1)
			this.updateCell((int)maxX, (int)i, -1)
		}
	}
	/** Set borders for cells
	 * 
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 */
	public void setBorders(Integer minX, Integer maxX, Integer minY, Integer maxY){
		// TODO
		for(int i = minX; i < maxX; i++) {
			this.updateCell((int)i, (int)minY, -1)
			this.updateCell((int)i, (int)maxY, -1)
		}
		for(int i = minY; i < maxY; i++) {
			this.updateCell((int)minX, (int)i, -1)
			this.updateCell((int)maxX, (int)i, -1)
		}
	}
	/**
	 * Set turtles and positions in cells
	 * @param turtles
	 * @param value
	 */
	public void setTurtles(ArrayList<ReLogoTurtle> turtles, double value) {
		for (ReLogoTurtle turtle : turtles) {
			NdPoint location = turtle.getTurtleLocation();
			double x = location.x
			double y = location.y
			this.updateCell((int)x, (int)y, value)
		}
	}
	/**
	 * Update cell in position x, y
	 * @param x
	 * @param y
	 * @param value
	 */
	private void updateCell(int x, int y, double value) {
		this.dStarLitePF.updateCell(x, y, value)
	}
	/**
	 * Update goal to x,y
	 * @param x
	 * @param y
	 */
	public void updateGoal(int x, int y) {
		this.dStarLitePF.updateGoal(x, y)
	}
	/**
	 * Re-plan route
	 */
	public void replan() {
		this.dStarLitePF.replan()
	}
}
