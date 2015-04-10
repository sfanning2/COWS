package cows.relogo

import cows.ReLogoTurtle
import cows.dstarlite.DStarLite
import java.awt.Point
import repast.simphony.space.continuous.NdPoint

class PathFinder {
	def DStarLite dStarLitePF

	def List<Object> edges
	def List<Object> fences
	def List<Object> trees

	def List<Cow> currentCows
	def List<NdPoint> prevCowLocations

	def List<Herder> currentHerders
	def List<NdPoint> prevHerderLocations


	PathFinder(NdPoint start, NdPoint goal) {
		this.dStarLitePF = new DStarLite()
		dStarLitePF.init((int)start.x, (int)start.y, (int)goal.x, (int)goal.y)
		/* static cell updates */
		// update edge cells
		// update fence cells
		// update tree cells

		/* dynamic cell updates must be passed*/
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
		this.currentCows = currentCows
		if (currentCows != null && currentCows.size() > 0) {
			this.prevCowLocations = new ArrayList();
			for (Cow cow : currentCows) {
				NdPoint location = cow.getTurtleLocation();
				double x = location.x
				double y = location.y
				this.prevCowLocations.add(new NdPoint(x, y))
				this.updateCell((int)x, (int)y, -1)
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

	private void setTurtles(ReLogoTurtle turtle, double value) {
		/* get location and dimensions */
		NdPoint location = turtle.getTurtleLocation();
		/* create cell barriers around the object */

		/* for now this is a single patch */
	}

	private void updateCowBarriers(Cow cow) {
		/* call updateCell for each relevant location */
	}

	private void updateHerderBarriers(Herder herder) {
	}

	private void updateCell(int x, int y, double value) {
		this.dStarLitePF.updateCell(x, y, value)
	}

	public void updateGoal(int x, int y) {
		this.dStarLitePF.updateGoal(x, y)
	}

	public void replan() {
		this.dStarLitePF.replan()
	}
}
