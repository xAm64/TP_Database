package fr.fms.entities;

public class Article {
	private int idArticle;
	private String description;
	private String brand;
	private double unitPrice;
	
	public Article(int idArticle, String description, String brand, double unitPrice) {
		super();
		this.idArticle = idArticle;
		this.description = description;
		this.brand = brand;
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getUnitPrice() {
		return unitPrice;
	}
	public String getStringPrice() {
		return Double.toString(unitPrice);
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getIdArticle() {
		return this.idArticle;
	}

}
