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

public class SimBuilder implements ContextBuilder {
	
	public Context build(Context context) {
	
		if (RunEnvironment.getInstance().isBatch()){
            UserGlobalsAndPanelFactory ugpf = new UserGlobalsAndPanelFactory();
            ugpf.initialize(new JPanel());
            ugpf.addGlobalsAndPanelComponents();
        }
		 
		Parameters p = RunEnvironment.getInstance().getParameters();
		
		//field_width and field_height 
		int x = (int)Math.floor(Double.valueOf(p.getValue("field_width").toString())/2);
		int y = (int)Math.floor(Double.valueOf(p.getValue("field_height").toString())/2);
		// NOTE: minPxcor and minPycor must be <= 0
		int minPxcor = -x;
		int maxPxcor = x;
		int minPycor = -y;
		int maxPycor = y;
		//non-toroidal world
		RLWorldDimensions rLWorldDimensions = new RLWorldDimensions(minPxcor, maxPxcor, minPycor, maxPycor);
		
		LinkFactory lf = new LinkFactory(UserLink.class);
		TurtleFactory tf = new TurtleFactory(UserTurtle.class);
		PatchFactory pf = new PatchFactory(UserPatch.class);		
		ReLogoWorldFactory wf = new ReLogoWorldFactory(context,"default_observer_context", rLWorldDimensions, tf, pf, lf);
		
		ObserverFactory oF = new ObserverFactory("default_observer",UserObserver.class,wf);
		Observer dO = oF.createObserver();
		
		context.add(dO);
		return context;
	}
}
