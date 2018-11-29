package pl.parser.nbp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MathematicianTest {
	@Test
	public void simpleTest() {
		List<Double> prices = new ArrayList<>();
		prices.add(2.0);
		prices.add(3.0);
		prices.add(4.0);
		
		assertEquals(3.0, Mathematician.getAverage(prices), 0.0001);
		assertEquals(0.8164, Mathematician.getDeviation(prices), 0.0001);
	}
}
