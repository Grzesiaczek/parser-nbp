package pl.parser.nbp;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class ReceiverTest {
	Receiver receiver = new Receiver();
	
	@Test
	public void testGettingTable() {
		Table table = receiver.getTable("c073z070413.xml");
		
		assertEquals("73/C/NBP/2007", table.getNumber());
		assertEquals(14, table.getPositions().size());
	}

	@Test
	public void testGettingFilenamesList() {
		List<String> filenames = receiver.getFilenames(LocalDate.of(2018, 11, 26), LocalDate.of(2018, 11, 28));
		
		assertEquals(3, filenames.size());
		assertEquals("c229z181126.xml", filenames.get(0));
	}
	
	@Test
	public void testGettingFilenamesListChangeYear() {
		List<String> filenames = receiver.getFilenames(LocalDate.of(2017, 12, 30), LocalDate.of(2018, 1, 3));
		
		assertEquals(2, filenames.size());
		assertEquals("c001z180102.xml", filenames.get(0));
	}
	
	@Test
	public void testGettingPrices() {
		List<Position> positions = receiver.getPositions("EUR", LocalDate.of(2013, 1, 28), LocalDate.of(2013, 1, 31));
		
		List<Double> buyingPrices = positions.stream()
				.map(Position::getConvertedBuyingPrice)
				.collect(Collectors.toList());
		
		List<Double> sellingPrices = positions.stream()
				.map(Position::getConvertedSellingPrice)
				.collect(Collectors.toList());
		
		assertEquals(4, positions.size());
		
		assertEquals(4.1505, Mathematician.getAverage(buyingPrices), 0.0001);
		assertEquals(0.0125, Mathematician.getDeviation(sellingPrices), 0.0001);
	}
}
