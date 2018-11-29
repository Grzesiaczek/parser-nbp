package pl.parser.nbp;

import javax.xml.bind.annotation.XmlElement;

public class Position {
	private String description;
	private int ratio;
	private String code;
	private String buyPrice;
	private String sellPrice;
	
	@XmlElement(name = "nazwa_waluty")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name = "przelicznik")
	public int getRatio() {
		return ratio;
	}
	
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	
	@XmlElement(name = "kod_waluty")
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@XmlElement(name = "kurs_kupna")
	public String getBuyPrice() {
		return buyPrice;
	}
	
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	@XmlElement(name = "kurs_sprzedazy")
	public String getSellPrice() {
		return sellPrice;
	}
	
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public double getConvertedBuyingPrice() {
		String[] parts = buyPrice.split(",");
		double num = Double.valueOf(parts[0]);
		double dec = Double.valueOf(parts[1]) / Math.pow(10, parts[1].length());
		return num + dec;
	}
	
	public double getConvertedSellingPrice() {
		String[] parts = sellPrice.split(",");
		double num = Double.valueOf(parts[0]);
		double dec = Double.valueOf(parts[1]) / Math.pow(10, parts[1].length());
		return num + dec;
	}
}