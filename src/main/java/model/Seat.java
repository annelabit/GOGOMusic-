package model;

public class Seat {

	private int id;
	private float price;
	private int available;
	private String type;
	
	public Seat() {
		super();
	}

	public Seat(int id, float price, int available, String type) {
		this.id = id;
		this.price = price;
		this.available = available;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int isAvailable() {
		return available;
	}
	public void setAvailable(int i) {
		this.available = i;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
