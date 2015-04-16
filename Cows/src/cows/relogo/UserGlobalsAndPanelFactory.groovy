package cows.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory
/**
 * Object that is a user panel with controls that configure simulation parameters.
 * @author Lynnea
 *
 */
public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	/**
	 * Add controls to panel with associated global parameters
	 */
	public void addGlobalsAndPanelComponents(){

		addSliderWL("numHerders", "Number of Herders", 1, 1, 100, 1)
		addSliderWL("numCows", "Number of Cows", 5, 5, 250, 5)
		addSliderWL("obstacleDensity", "Density of Obstacles", 0, 0.01, 0.5, 0)
		addMonitorWL("remainingCows", "Cows in Field", 5)	
	}
}