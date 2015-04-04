package cows;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import groovy.lang.Closure;
import repast.simphony.relogo.*;
import repast.simphony.relogo.ast.Diffusible;
import repast.simphony.relogo.builder.GeneratedByReLogoBuilder;
import repast.simphony.relogo.builder.ReLogoBuilderGeneratedFor;
import repast.simphony.space.SpatialException;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoPatch extends BasePatch{

	/**
	 * Sprouts (makes) a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> sproutUserTurtles(int number, Closure closure) {
		AgentSet<cows.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof cows.relogo.UserTurtle){
				result.add((cows.relogo.UserTurtle)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.UserTurtle")
	public AgentSet<cows.relogo.UserTurtle> sproutUserTurtles(int number) {
		return sproutUserTurtles(number,null);
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
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
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
	 * Sprouts (makes) a number of new herders and then executes a set of commands on the
	 * created herders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created herders
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> sproutHerders(int number, Closure closure) {
		AgentSet<cows.relogo.Herder> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Herder");
		for (Turtle t : createResult){
			if (t instanceof cows.relogo.Herder){
				result.add((cows.relogo.Herder)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new herders and then executes a set of commands on the
	 * created herders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created herders
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Herder")
	public AgentSet<cows.relogo.Herder> sproutHerders(int number) {
		return sproutHerders(number,null);
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
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
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
	 * Sprouts (makes) a number of new cows and then executes a set of commands on the
	 * created cows.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created cows
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> sproutCows(int number, Closure closure) {
		AgentSet<cows.relogo.Cow> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Cow");
		for (Turtle t : createResult){
			if (t instanceof cows.relogo.Cow){
				result.add((cows.relogo.Cow)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new cows and then executes a set of commands on the
	 * created cows.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created cows
	 */
	@ReLogoBuilderGeneratedFor("cows.relogo.Cow")
	public AgentSet<cows.relogo.Cow> sproutCows(int number) {
		return sproutCows(number,null);
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
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
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


}