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

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoLink<T> extends BaseLink<T>	{

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