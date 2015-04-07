package cows;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import groovy.lang.Closure;
import repast.simphony.relogo.*;
import repast.simphony.relogo.builder.GeneratedByReLogoBuilder;
import repast.simphony.relogo.builder.ReLogoBuilderGeneratedFor;
import repast.simphony.space.SpatialException;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoTurtle extends BaseTurtle{

	/**
	 * Makes a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> hatchUserTurtles(int number, Closure closure) {
		AgentSet<cows.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof cows.relogo.UserTurtle){
				result.add((cows.relogo.UserTurtle)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> hatchUserTurtles(int number) {
		return hatchUserTurtles(number,null);
	}

	/**
	 * Returns an agentset of userTurtles from the patch of the caller.
	 * 
	 * @return agentset of userTurtles from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> userTurtlesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<cows.relogo.UserTurtle> result = new AgentSet<cows.relogo.UserTurtle>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof cows.relogo.UserTurtle)
			result.add((cows.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of userTurtles on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of userTurtles at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> userTurtlesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<cows.relogo.UserTurtle> result = new AgentSet<cows.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof cows.relogo.UserTurtle)
			result.add((cows.relogo.UserTurtle)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<cows.relogo.UserTurtle>();
		}
	}

	/**
	 * Returns an agentset of userTurtles on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of userTurtles on patch p
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> userTurtlesOn(Patch p){
		AgentSet<cows.relogo.UserTurtle> result = new AgentSet<cows.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"userTurtle")){
			if (t instanceof cows.relogo.UserTurtle)
			result.add((cows.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of userTurtles on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> userTurtlesOn(Turtle t){
		AgentSet<cows.relogo.UserTurtle> result = new AgentSet<cows.relogo.UserTurtle>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"userTurtle")){
			if (tt instanceof cows.relogo.UserTurtle)
			result.add((cows.relogo.UserTurtle)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of userTurtles on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> userTurtlesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<cows.relogo.UserTurtle>();
		}

		Set<cows.relogo.UserTurtle> total = new HashSet<cows.relogo.UserTurtle>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(userTurtlesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(userTurtlesOn(p));
				}
			}
		}
		return new AgentSet<cows.relogo.UserTurtle>(total);
	}

	/**
	 * Queries if object is a userTurtle.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userTurtle
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public boolean isUserTurtleQ(Object o){
		return (o instanceof cows.relogo.UserTurtle);
	}

	/**
	 * Returns an agentset containing all userTurtles.
	 * 
	 * @return agentset of all userTurtles
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> userTurtles(){
		AgentSet<cows.relogo.UserTurtle> a = new AgentSet<cows.relogo.UserTurtle>();
		for (Object e : this.getMyObserver().getContext().getObjects(cows.relogo.UserTurtle.class)) {
			if (e instanceof cows.relogo.UserTurtle){
				a.add((cows.relogo.UserTurtle)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userTurtle with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public cows.relogo.UserTurtle userTurtle(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof cows.relogo.UserTurtle)
			return (cows.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Makes a number of new herders and then executes a set of commands on the
	 * created herders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created herders
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> hatchHerders(int number, Closure closure) {
		AgentSet<cows.relogo.Herder> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"Herder");
		for (Turtle t : createResult){
			if (t instanceof cows.relogo.Herder){
				result.add((cows.relogo.Herder)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new herders and then executes a set of commands on the
	 * created herders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created herders
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> hatchHerders(int number) {
		return hatchHerders(number,null);
	}

	/**
	 * Returns an agentset of herders from the patch of the caller.
	 * 
	 * @return agentset of herders from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> herdersHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<cows.relogo.Herder> result = new AgentSet<cows.relogo.Herder>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"herder")){
			if (t instanceof cows.relogo.Herder)
			result.add((cows.relogo.Herder)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of herders on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of herders at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> herdersAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<cows.relogo.Herder> result = new AgentSet<cows.relogo.Herder>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"herder")){
			if (t instanceof cows.relogo.Herder)
			result.add((cows.relogo.Herder)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<cows.relogo.Herder>();
		}
	}

	/**
	 * Returns an agentset of herders on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of herders on patch p
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> herdersOn(Patch p){
		AgentSet<cows.relogo.Herder> result = new AgentSet<cows.relogo.Herder>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"herder")){
			if (t instanceof cows.relogo.Herder)
			result.add((cows.relogo.Herder)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of herders on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of herders on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> herdersOn(Turtle t){
		AgentSet<cows.relogo.Herder> result = new AgentSet<cows.relogo.Herder>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"herder")){
			if (tt instanceof cows.relogo.Herder)
			result.add((cows.relogo.Herder)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of herders on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of herders on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> herdersOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<cows.relogo.Herder>();
		}

		Set<cows.relogo.Herder> total = new HashSet<cows.relogo.Herder>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(herdersOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(herdersOn(p));
				}
			}
		}
		return new AgentSet<cows.relogo.Herder>(total);
	}

	/**
	 * Queries if object is a herder.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a herder
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public boolean isHerderQ(Object o){
		return (o instanceof cows.relogo.Herder);
	}

	/**
	 * Returns an agentset containing all herders.
	 * 
	 * @return agentset of all herders
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> herders(){
		AgentSet<cows.relogo.Herder> a = new AgentSet<cows.relogo.Herder>();
		for (Object e : this.getMyObserver().getContext().getObjects(cows.relogo.Herder.class)) {
			if (e instanceof cows.relogo.Herder){
				a.add((cows.relogo.Herder)e);
			}
		}
		return a;
	}

	/**
	 * Returns the herder with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public cows.relogo.Herder herder(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof cows.relogo.Herder)
			return (cows.relogo.Herder) turtle;
		return null;
	}

	/**
	 * Makes a number of new fences and then executes a set of commands on the
	 * created fences.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created fences
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public AgentSet<cows.relogo.Fence> hatchFences(int number, Closure closure) {
		AgentSet<cows.relogo.Fence> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"Fence");
		for (Turtle t : createResult){
			if (t instanceof cows.relogo.Fence){
				result.add((cows.relogo.Fence)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new fences and then executes a set of commands on the
	 * created fences.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created fences
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public AgentSet<cows.relogo.Fence> hatchFences(int number) {
		return hatchFences(number,null);
	}

	/**
	 * Returns an agentset of fences from the patch of the caller.
	 * 
	 * @return agentset of fences from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public AgentSet<cows.relogo.Fence> fencesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<cows.relogo.Fence> result = new AgentSet<cows.relogo.Fence>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"fence")){
			if (t instanceof cows.relogo.Fence)
			result.add((cows.relogo.Fence)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of fences on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of fences at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public AgentSet<cows.relogo.Fence> fencesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<cows.relogo.Fence> result = new AgentSet<cows.relogo.Fence>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"fence")){
			if (t instanceof cows.relogo.Fence)
			result.add((cows.relogo.Fence)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<cows.relogo.Fence>();
		}
	}

	/**
	 * Returns an agentset of fences on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of fences on patch p
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public AgentSet<cows.relogo.Fence> fencesOn(Patch p){
		AgentSet<cows.relogo.Fence> result = new AgentSet<cows.relogo.Fence>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"fence")){
			if (t instanceof cows.relogo.Fence)
			result.add((cows.relogo.Fence)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of fences on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of fences on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public AgentSet<cows.relogo.Fence> fencesOn(Turtle t){
		AgentSet<cows.relogo.Fence> result = new AgentSet<cows.relogo.Fence>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"fence")){
			if (tt instanceof cows.relogo.Fence)
			result.add((cows.relogo.Fence)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of fences on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of fences on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public AgentSet<cows.relogo.Fence> fencesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<cows.relogo.Fence>();
		}

		Set<cows.relogo.Fence> total = new HashSet<cows.relogo.Fence>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(fencesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(fencesOn(p));
				}
			}
		}
		return new AgentSet<cows.relogo.Fence>(total);
	}

	/**
	 * Queries if object is a fence.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a fence
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public boolean isFenceQ(Object o){
		return (o instanceof cows.relogo.Fence);
	}

	/**
	 * Returns an agentset containing all fences.
	 * 
	 * @return agentset of all fences
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public AgentSet<cows.relogo.Fence> fences(){
		AgentSet<cows.relogo.Fence> a = new AgentSet<cows.relogo.Fence>();
		for (Object e : this.getMyObserver().getContext().getObjects(cows.relogo.Fence.class)) {
			if (e instanceof cows.relogo.Fence){
				a.add((cows.relogo.Fence)e);
			}
		}
		return a;
	}

	/**
	 * Returns the fence with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Fence")
	public cows.relogo.Fence fence(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof cows.relogo.Fence)
			return (cows.relogo.Fence) turtle;
		return null;
	}

	/**
	 * Makes a number of new cows and then executes a set of commands on the
	 * created cows.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created cows
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> hatchCows(int number, Closure closure) {
		AgentSet<cows.relogo.Cow> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"Cow");
		for (Turtle t : createResult){
			if (t instanceof cows.relogo.Cow){
				result.add((cows.relogo.Cow)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new cows and then executes a set of commands on the
	 * created cows.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created cows
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> hatchCows(int number) {
		return hatchCows(number,null);
	}

	/**
	 * Returns an agentset of cows from the patch of the caller.
	 * 
	 * @return agentset of cows from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> cowsHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<cows.relogo.Cow> result = new AgentSet<cows.relogo.Cow>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"cow")){
			if (t instanceof cows.relogo.Cow)
			result.add((cows.relogo.Cow)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of cows on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of cows at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> cowsAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<cows.relogo.Cow> result = new AgentSet<cows.relogo.Cow>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"cow")){
			if (t instanceof cows.relogo.Cow)
			result.add((cows.relogo.Cow)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<cows.relogo.Cow>();
		}
	}

	/**
	 * Returns an agentset of cows on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of cows on patch p
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> cowsOn(Patch p){
		AgentSet<cows.relogo.Cow> result = new AgentSet<cows.relogo.Cow>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"cow")){
			if (t instanceof cows.relogo.Cow)
			result.add((cows.relogo.Cow)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of cows on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of cows on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> cowsOn(Turtle t){
		AgentSet<cows.relogo.Cow> result = new AgentSet<cows.relogo.Cow>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"cow")){
			if (tt instanceof cows.relogo.Cow)
			result.add((cows.relogo.Cow)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of cows on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of cows on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> cowsOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<cows.relogo.Cow>();
		}

		Set<cows.relogo.Cow> total = new HashSet<cows.relogo.Cow>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(cowsOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(cowsOn(p));
				}
			}
		}
		return new AgentSet<cows.relogo.Cow>(total);
	}

	/**
	 * Queries if object is a cow.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a cow
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public boolean isCowQ(Object o){
		return (o instanceof cows.relogo.Cow);
	}

	/**
	 * Returns an agentset containing all cows.
	 * 
	 * @return agentset of all cows
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> cows(){
		AgentSet<cows.relogo.Cow> a = new AgentSet<cows.relogo.Cow>();
		for (Object e : this.getMyObserver().getContext().getObjects(cows.relogo.Cow.class)) {
			if (e instanceof cows.relogo.Cow){
				a.add((cows.relogo.Cow)e);
			}
		}
		return a;
	}

	/**
	 * Returns the cow with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public cows.relogo.Cow cow(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof cows.relogo.Cow)
			return (cows.relogo.Cow) turtle;
		return null;
	}

	/**
	 * Returns the value from the getPatch_var01() method of the underlying patch.
	 * 
	 * @return getPatch_var01() of type java.lang.Object
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserPatch")
	public java.lang.Object getPatch_var01(){
		cows.relogo.UserPatch p = (cows.relogo.UserPatch)patchHere();
		return p.getPatch_var01();
	}

	/**
	 * Calls the setPatch_var01(java.lang.Object) method of the underlying patch.
	 * 
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserPatch")
	public void setPatch_var01(java.lang.Object value){
		cows.relogo.UserPatch p = (cows.relogo.UserPatch)patchHere();
		p.setPatch_var01(value);
	}

	/**
	 * Returns the value from the getPatchvar02() method of the underlying patch.
	 * 
	 * @return getPatchvar02() of type java.lang.Object
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserPatch")
	public java.lang.Object getPatchvar02(){
		cows.relogo.UserPatch p = (cows.relogo.UserPatch)patchHere();
		return p.getPatchvar02();
	}

	/**
	 * Calls the setPatchvar02(java.lang.Object) method of the underlying patch.
	 * 
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserPatch")
	public void setPatchvar02(java.lang.Object value){
		cows.relogo.UserPatch p = (cows.relogo.UserPatch)patchHere();
		p.setPatchvar02(value);
	}

	/**
	 * Makes a directed userLink from a turtle to the caller then executes a set of
	 * commands on the created userLink.
	 * 
	 * @param t
	 *            a turtle
	 * @param closure
	 *            a set of commands
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public cows.relogo.UserLink createUserLinkFrom(Turtle t, Closure closure){
		cows.relogo.UserLink link = (cows.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(t,this);
		if (closure != null){
			this.ask(link,closure);
		}
		return link;
	}

	/**
	 * Makes a directed userLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public cows.relogo.UserLink createUserLinkFrom(Turtle t){
			return createUserLinkFrom(t,null);
	}

	/**
	 * Makes directed userLinks from a collection to the caller then executes a set
	 * of commands on the created userLinks.
	 * 
	 * @param a
	 *            a collection
	 * @param closure
	 *            a set of commands
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet<cows.relogo.UserLink> createUserLinksFrom(Collection<? extends Turtle> a, Closure closure){
		AgentSet<cows.relogo.UserLink> links = new AgentSet<cows.relogo.UserLink>();
		for(Turtle t : a){
			links.add((cows.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(t,this));
		}
		if (closure != null){
			this.ask(links,closure);
		}
		return links;
	}

	/**
	 * Makes directed userLinks from a collection to the caller.
	 * 
	 * @param a
	 *            a collection
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet<cows.relogo.UserLink> createUserLinksFrom(Collection<? extends Turtle> a){
		return createUserLinksFrom(a,null);
	}

	/**
	 * Makes a directed userLink to a turtle from the caller then executes a set of
	 * commands on the created userLink.
	 * 
	 * @param t
	 *            a turtle
	 * @param closure
	 *            a set of commands
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public cows.relogo.UserLink createUserLinkTo(Turtle t, Closure closure){
		cows.relogo.UserLink link = (cows.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(this,t);
		if (closure != null){
			this.ask(link,closure);
		}
		return link;
	}

	/**
	 * Makes a directed userLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public cows.relogo.UserLink createUserLinkTo(Turtle t){
			return createUserLinkTo(t,null);
	}

	/**
	 * Makes directed userLinks to a collection from the caller then executes a set
	 * of commands on the created userLinks.
	 * 
	 * @param a
	 *            a collection
	 * @param closure
	 *            a set of commands
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet<cows.relogo.UserLink> createUserLinksTo(Collection<? extends Turtle> a, Closure closure){
		AgentSet<cows.relogo.UserLink> links = new AgentSet<cows.relogo.UserLink>();
		for(Object t : a){
			if (t instanceof Turtle){
				links.add((cows.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(this,(Turtle)t));
			}
		}
		if (closure != null){
			this.ask(links,closure);
		}
		return links;
	}

	/**
	 * Makes directed userLinks to a collection from the caller.
	 * 
	 * @param a
	 *            a collection
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet<cows.relogo.UserLink> createUserLinksTo(Collection<? extends Turtle> a){
		return createUserLinksTo(a,null);
	}

	/**
	 * Queries if there is a directed userLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return true or false based on whether there is a directed userLink from
	 *         turtle t to the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public boolean inUserLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("UserLink").isPredecessor(t, this);
	}

	/**
	 * Returns the agentset with directed userLinks to the caller.
	 * 
	 * @return agentset with directed userLinks to the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet inUserLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getPredecessors(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns the directed userLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return directed userLink from turtle t to the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public cows.relogo.UserLink inUserLinkFrom(Turtle t){
		return (cows.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").getEdge(t,this);
	}

	/**
	 * Returns an agentset of directed userLinks from other turtles to the caller.
	 * 
	 * @return agentset of directed userLinks from other turtles to the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet<cows.relogo.UserLink> myInUserLinks(){
		AgentSet<cows.relogo.UserLink> result = new AgentSet<cows.relogo.UserLink>();
		for(Object o :  this.getMyObserver().getNetwork("UserLink").getInEdges(this)){
			if (o instanceof cows.relogo.UserLink){
				result.add((cows.relogo.UserLink) o);
			}
		}
		return result;
	}

	/**
	 * Returns an agentset of directed userLinks to other turtles from the caller.
	 * 
	 * @return agentset of directed userLinks to other turtles from the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet<cows.relogo.UserLink> myOutUserLinks(){
		AgentSet<cows.relogo.UserLink> result = new AgentSet<cows.relogo.UserLink>();
		for(Object o :  this.getMyObserver().getNetwork("UserLink").getOutEdges(this)){
			if (o instanceof cows.relogo.UserLink){
				result.add((cows.relogo.UserLink) o);
			}
		}
		return result;
	}

	/**
	 * Queries if there is a directed userLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return true or false based on whether there is a directed userLink to
	 *         turtle t from the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public boolean outUserLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("UserLink").isPredecessor(this, t);
	}

	/**
	 * Returns the agentset with directed userLinks from the caller.
	 * 
	 * @return agentset with directed userLinks from the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet outUserLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getSuccessors(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns the directed userLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return directed userLink to turtle t from the caller
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public cows.relogo.UserLink outUserLinkTo(Turtle t){
		return (cows.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").getEdge(this, t);
	}

	/**
	 * Reports true if there is a userLink connecting t and the caller.
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public boolean userLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("UserLink").isAdjacent(this, t);
	}

	/**
	 * Returns the agentset of all turtles found at the other end of
	 * userLinks connected to the calling turtle.
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet userLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getAdjacent(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns an agentset of the caller's userLinks.
	 * 
	 * @return agentset of the caller's userLinks
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet<cows.relogo.UserLink> myUserLinks(){
		AgentSet<cows.relogo.UserLink> result = new AgentSet<cows.relogo.UserLink>();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getEdges(this)){
			if (o instanceof cows.relogo.UserLink){
				result.add((cows.relogo.UserLink)o);
			}
		}
		return result;
	}

	/**
	 * Queries if object is a userLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userLink
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public boolean isUserLinkQ(Object o){
		return (o instanceof cows.relogo.UserLink);
	}

	/**
	 * Returns an agentset containing all userLinks.
	 * 
	 * @return agentset of all userLinks
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public AgentSet<cows.relogo.UserLink> userLinks(){
		AgentSet<cows.relogo.UserLink> a = new AgentSet<cows.relogo.UserLink>();
		for (Object e : this.getMyObserver().getContext().getObjects(cows.relogo.UserLink.class)) {
			if (e instanceof cows.relogo.UserLink){
				a.add((cows.relogo.UserLink)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public cows.relogo.UserLink userLink(Number oneEnd, Number otherEnd) {
		return (cows.relogo.UserLink)(this.getMyObserver().getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserLink")
	public cows.relogo.UserLink userLink(Turtle oneEnd, Turtle otherEnd) {
		return userLink(oneEnd.getWho(), otherEnd.getWho());
	}

	/**
	 * Returns the value of the global variable numHerders.
	 *
	 * @return the value of the global variable numHerders
	 */
	@ReLogoBuilderGeneratedFor("global: numHerders")
	public Object getNumHerders(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("numHerders");
	}

	/**
	 * Sets the value of the global variable numHerders.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: numHerders")
	public void setNumHerders(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("numHerders",value);
	}

	/**
	 * Returns the value of the global variable numCows.
	 *
	 * @return the value of the global variable numCows
	 */
	@ReLogoBuilderGeneratedFor("global: numCows")
	public Object getNumCows(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("numCows");
	}

	/**
	 * Sets the value of the global variable numCows.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: numCows")
	public void setNumCows(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("numCows",value);
	}

	/**
	 * Returns the value of the global variable obstacleDensity.
	 *
	 * @return the value of the global variable obstacleDensity
	 */
	@ReLogoBuilderGeneratedFor("global: obstacleDensity")
	public Object getObstacleDensity(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("obstacleDensity");
	}

	/**
	 * Sets the value of the global variable obstacleDensity.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: obstacleDensity")
	public void setObstacleDensity(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("obstacleDensity",value);
	}


}