package pl.parser.nbp;

import java.util.List;

public class Mathematician {
	public static double getAverage(List<Double> prices) {
		return prices.stream()
				.mapToDouble(d -> d)
				.average()
				.getAsDouble();
	}
	
	public static double getDeviation(List<Double> prices) {
		double average = Mathematician.getAverage(prices);
		
		double part = prices.stream()
				.map(d -> Math.abs(average - d))
				.mapToDouble(d -> d * d)
				.sum();
		
		return Math.sqrt(part / prices.size());
	}
}
