package pl.parser.nbp;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tabela_kursow")
public class Table {
	private String number;
	private String qoutesDate;
	private String publicationDate;
	private List<Position> positions;
	
	@XmlElement(name = "numer_tabeli")
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	@XmlElement(name = "data_notowania")
	public String getQoutesDate() {
		return qoutesDate;
	}
	
	public void setQoutesDate(String qoutesDate) {
		this.qoutesDate = qoutesDate;
	}
	
	@XmlElement(name = "data_publikacji")
	public String getPublicationDate() {
		return publicationDate;
	}
	
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	@XmlElement(name = "pozycja")
	public List<Position> getPositions() {
		return positions;
	}
	
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
	public Position getPosition(String currencyCode) {
		return positions.stream()
				.filter(p -> p.getCode().equals(currencyCode))
				.findFirst()
				.orElse(null);
	}
}