package model;

public class Product {
<<<<<<< HEAD
	
	private int id;
	private String name;
	private String category;
	private float price;
	private String image;
	
	public Product() {
	}

	public Product(int id, String name, String category, float price, String image) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.image = image;
=======

	private int id;
	private String name;
	private float price;
	private String image;
	private String category;
	private int venueId;
	
	public Product() {
	}
	
	public Product(int id, String name, float price, String category, String image, int venueId) {
		this.id= id;
		this.name= name;
		this.price= price;
		this.category= category;
		this.image= image;
		this.venueId=venueId;
>>>>>>> main
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

<<<<<<< HEAD
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
	
=======
	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", category="
				+ category + ", venueId=" + venueId + "]";
	}

>>>>>>> main
	
}
