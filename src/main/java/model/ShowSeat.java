package model;

public class ShowSeat {

	private int showId;
    private int seatId;
    private float price;
    private int available;
    
    public ShowSeat() {}
    
	public ShowSeat(int showId, int seatId, float price, int available) {
		super();
		this.showId = showId;
		this.seatId = seatId;
		this.price = price;
		this.available = available;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
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
	public void setAvailable(int available) {
		this.available = available;
	}
    
    
    
	
}
