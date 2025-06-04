package model;

import java.util.ArrayList;

public class Cart extends Product{

	private int quantity;
	private int showId;
	
	private ArrayList<Integer> seatIds;
	
	public Cart(Product p) {
		super(p.getId(), p.getName(), p.getPrice(), p.getCategory(), p.getImage(), p.getVenueId());

	}

	public Cart() {
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public ArrayList<Integer> getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(ArrayList<Integer> seatIds) {
		this.seatIds = seatIds;
	}

	@Override
	public String toString() {
		return "Cart [quantity=" + quantity + ", showId=" + showId + ", seatIds=" + seatIds + "]";
	}

	

}
