import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cows.context.SimBuilder;
import cows.relogo.UserObserver;
import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.Parameters;
import repast.simphony.parameter.ParametersParser;
import repast.simphony.scenario.ScenarioUtils;


public class ObstacleDensityTest {
	static UserObserver observer;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ScenarioUtils.setScenarioDir(new File("Cows.rs"));
		File parameters = new File(ScenarioUtils.getScenarioDir(), "parameters.xml");
		ParametersParser parser = new ParametersParser(parameters);
		Parameters pms = parser.getParameters();
		RunEnvironment.init(new Schedule(), null, pms, true);
		Context context = new DefaultContext();
		SimBuilder builder = new SimBuilder();
		context = builder.build(context);
		
		RunState.init().setMasterContext(context);
		observer = (UserObserver) context.iterator().next();
		
		
	}
	@Before 
	public void setUp() throws Exception{
		observer.clearAll();
		//set new values for configurables
		
	}
	@Test
	public void testObstacleDensity(double obstacleDensity) {
		
	}

}
