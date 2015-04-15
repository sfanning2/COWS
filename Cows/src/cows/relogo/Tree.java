package cows.relogo;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import cows.ReLogoPatch;
import cows.ReLogoTurtle;

public class Tree extends ReLogoTurtle {
	private ContinuousSpace<Object> space;
	private Grid<Object> grid; 

	public Tree(){

	}
	public Tree(ContinuousSpace<Object> space, Grid<Object> grid){
		this.space = space;
		this.grid = grid;
	}
	private void setSpace(ContinuousSpace space){
		this.space = space;
	}
	private void setGrid(Grid grid){
		this.grid = grid;
	}
}
