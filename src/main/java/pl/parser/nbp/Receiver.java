package pl.parser.nbp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Receiver {
	private final static String PATH = "http://www.nbp.pl/kursy/xml";
	
	public List<Position> getPositions(String currencyCode, LocalDate startDate, LocalDate endDate) {		
		return getFilenames(startDate, endDate).stream()
			.map(name -> getTable(name).getPosition(currencyCode))
			.collect(Collectors.toList());		
	}
	
	public Table getTable(String fileName) {
		Client client = Client.create();		
        WebResource webResource = client.resource(PATH).path(fileName);
        return webResource.get(Table.class);
	}
	
	public List<String> getFilenames(LocalDate start, LocalDate end) {
		int startInt = (start.getYear() % 100) * 10000 + start.getMonthValue() * 100 + start.getDayOfMonth();
		int endInt = (end.getYear() % 100) * 10000 + end.getMonthValue() * 100 + end.getDayOfMonth();
		
		Predicate<String> isNameCorrect = name -> {
			int date = Integer.valueOf(name.substring(5));
			return date >= startInt && date <= endInt && name.charAt(0) == 'c';
		};
		
		return IntStream.range(start.getYear(), end.getYear() + 1)
			.boxed()
			.map(year -> getFilenames(year))
			.flatMap(el -> el)
			.filter(isNameCorrect)
			.map(pos -> pos += ".xml")
			.collect(Collectors.toList());
	}
	
	private Stream<String> getFilenames(Integer year) {
		int currentYear = LocalDate.now().getYear();
		String fileName = "dir";
		
		if (currentYear > year) {
			fileName += year;
		} else if (currentYear < year) {
			return Stream.empty();
		}
		
		fileName += ".txt";
		
		Client client = Client.create();		
        WebResource webResource = client.resource(PATH).path(fileName);
        String content = webResource.get(String.class);
        
        return Arrays.stream(content.substring(1).split("\\r\\n"));
	}
}