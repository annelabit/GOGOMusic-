package model;

import java.util.ArrayList;

public class Product {
	
	private int id;
	private String name;
	private String image;
	private String category;
	private int venueId;
	private double minPrice;
	private double maxPrice;
	private String location;
	private ArrayList<Show> shows;
	private String descrizione;
	private float price;
	
	public Product() {
		id=0;
		name="";
		image="";
		category="";
		venueId=0;
		minPrice=0;
		maxPrice=0;
		location="";
		shows=null;
		descrizione="";
	}
	
	public Product(int id, String name, float price, String category, String image, int venueId) {
		this.id= id;
		this.name= name;
		this.category= category;
		this.image= image;
		this.venueId=venueId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	
	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<Show> getShows() {
		return shows;
	}

	public void setShows(ArrayList<Show> shows) {
		this.shows = shows;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", category="
				+ category + ", venueId=" + venueId + "]";
	}
	
}
