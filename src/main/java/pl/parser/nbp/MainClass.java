package pl.parser.nbp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainClass {
	public static void main(String... args) {
		if (args.length != 3) {
			System.out.println("Incorrect number of arguments. Must be 3.");
			return;
		}
		
		String[] availableCurrencies = { "USD", "EUR", "CHF", "GBP" };
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			String currencyCode = args[0];
			
			if (Arrays.stream(availableCurrencies).noneMatch(el -> el.equals(currencyCode))) {
				System.out.println("Incorrect currency.");
				return;
			}
			
			LocalDate startDate = LocalDate.parse(args[1], formatter);
			LocalDate endDate = LocalDate.parse(args[2], formatter);
			
			if (startDate.isAfter(endDate)) {
				System.out.println("End date is after start date.");
				return;
			}
			
			List<Position> positions = new Receiver().getPositions(currencyCode, startDate, endDate);
			
			List<Double> buyingPrices = positions.stream()
					.map(Position::getConvertedBuyingPrice)
					.collect(Collectors.toList());
			
			List<Double> sellingPrices = positions.stream()
					.map(Position::getConvertedSellingPrice)
					.collect(Collectors.toList());
			
			if (positions.size() == 0) {
				System.out.println("No result in given period.");
				return;
			}
			
			System.out.format("%.4f\n", Mathematician.getAverage(buyingPrices));
			System.out.format("%.4f\n", Mathematician.getDeviation(sellingPrices));
		} catch (Exception e) {
			System.out.println("Unexpected error during execution.");
		}
	}
}
