package model;

public class Location {

	private int id;
	private String venue;
	private String city;
	private String address;
	private String image;
	private int rows;
	private int columns;
	
	public Location() {}
	
	public Location(int id, String venue, String city, String address, String image, int rows, int columns) {
		super();
		this.id = id;
		this.venue = venue;
		this.city = city;
		this.address = address;
		this.image = image;
		this.rows = rows;
		this.columns = columns;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
	
}
