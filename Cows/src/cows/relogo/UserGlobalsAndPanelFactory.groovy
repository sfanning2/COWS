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

		addMonitorWL("remainingCows", "Cows in Field", 1)	
		addMonitorWL("currentMovers", "Movers", 1)	
		addMonitorWL("currentGroupers", "Groupers", 1)	
	}
}