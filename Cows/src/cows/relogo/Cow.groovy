package cows.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import cows.ReLogoTurtle;

class Cow extends ReLogoTurtle {
	public double speed;
	public double orientation;
	public double flightZoneRadius;
	public double width;
	public double length;
	public double independenceLevel;
	public double anxietyLevel;
	public double anxietyThreshold;
	public Position position;
}
