package model;

import java.util.ArrayList;

public class Show {

	private int id;
    private int productId;
    private int venueId;
    private String date;
    private String time;
    private ArrayList<ShowSeat> seats;
    private ArrayList<String> categories;
    
    public Show() {}
    
	public Show(int id, int productId, int venueId, String date, String time) {
		super();
		this.id = id;
		this.productId = productId;
		this.venueId = venueId;
		this.date = date;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getVenueId() {
		return venueId;
	}
	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public ArrayList<ShowSeat> getSeats() {
		return seats;
	}
	public void setSeats(ArrayList<ShowSeat> seats2) {
		this.seats = seats2;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
    
}
