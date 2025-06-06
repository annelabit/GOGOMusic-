package model;

public class Show {

	private int id;
    private int productId;
    private int venueId;
    private String date;
    private String time;
    
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
    
    
	
}
