package model;

public class Product {

	private int id;
	private String name;
	private float price;
	private String image;
	private String category;
	
	public Product() {
	}
	
	public Product(int id, String name, float price, String category, String image) {
		this.id= id;
		this.name= name;
		this.price= price;
		this.category= category;
		this.image= image;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
	
	
}
