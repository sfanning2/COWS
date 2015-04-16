package test.cows.relogo;

import cows.relogo.Herder;
import repast.simphony.space.continuous.NdPoint;
import junit.framework.TestCase;

/**
 * Tests for Herder class
 * @author Green-kiats
 *
 */
public class HerderTest extends TestCase {
	
	public void testVectorCalculation() {
		// Arrange
		NdPoint point0 = new NdPoint(2,1);
		NdPoint point1 = new NdPoint(4,4);
		double distance = 6.0;
		NdPoint expectedResult = new NdPoint(7.3282, 8.9923);
		NdPoint actualResult;
		double epsilon = 0.5;
		
		// Act
		actualResult = Herder.getPositionToGroupCow(point0, point1, distance);
		
		//Assert		
		assertEquals(expectedResult.getX(), actualResult.getX(), epsilon);
		assertEquals(expectedResult.getY(), actualResult.getY(), epsilon);
	}
}
