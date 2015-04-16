package cows.context;

import javax.swing.JPanel;
import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.relogo.Observer;
import repast.simphony.relogo.factories.LinkFactory;
import repast.simphony.relogo.factories.ObserverFactory;
import repast.simphony.relogo.factories.PatchFactory;
import repast.simphony.relogo.factories.RLWorldDimensions;
import repast.simphony.relogo.factories.ReLogoWorldFactory;
import repast.simphony.relogo.factories.TurtleFactory;
import cows.relogo.UserLink;
import cows.relogo.UserObserver;
import cows.relogo.UserPatch;
import cows.relogo.UserTurtle;
import cows.relogo.UserGlobalsAndPanelFactory;
/**
 * Object which creates and configures simulation environment. 
 * @author Lynnea
 *
 */
public class SimBuilder implements ContextBuilder {
	/**
	 * Build context
	 */
	public Context build(Context context) {
	
		if (RunEnvironment.instance.isBatch()){
            UserGlobalsAndPanelFactory ugpf = new UserGlobalsAndPanelFactory();
            ugpf.initialize(new JPanel());
            ugpf.addGlobalsAndPanelComponents();
        }
		/*get parameters from parameters.xml file*/
		Parameters p = RunEnvironment.getInstance().getParameters();

		int x = Math.floor(Math.sqrt(p.getValue("field_area"))/2);
		
		int minPxcor = -x// NOTE: minPxcor and minPycor must be <= 0
		int maxPxcor = x
		int minPycor = -x
		int maxPycor = x


		/*non-toroidal world so bouncy walls*/
		RLWorldDimensions rLWorldDimensions = new RLWorldDimensions(minPxcor, maxPxcor, minPycor, maxPycor, new repast.simphony.space.continuous.BouncyBorders());
		
		LinkFactory lf = new LinkFactory(UserLink);
		TurtleFactory tf = new TurtleFactory(UserTurtle);
		PatchFactory pf = new PatchFactory(UserPatch);		
		ReLogoWorldFactory wf = new ReLogoWorldFactory(context,"default_observer_context", rLWorldDimensions, tf, pf, lf);
		
		/*create observer who "watches" simulation*/
		ObserverFactory oF = new ObserverFactory("default_observer",UserObserver,wf);
		Observer dO = oF.createObserver();
		
		context.add(dO);
		return context;
	}
}
