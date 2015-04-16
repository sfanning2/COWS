package cows.factories;

import javax.swing.JPanel;
import repast.simphony.relogo.factories.AbstractReLogoPanelCreator;
import cows.relogo.UserGlobalsAndPanelFactory;
/**
 * Object for creating panels
 * @author Lynnea
 *
 */
public class ReLogoPanelCreator extends AbstractReLogoPanelCreator {
	/**
	 * Add globals and controls to panel
	 */
	public void addComponents(JPanel parent) {
		UserGlobalsAndPanelFactory ugpf = new UserGlobalsAndPanelFactory();
		ugpf.initialize(parent);
		ugpf.addGlobalsAndPanelComponents();
	}

}
