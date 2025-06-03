package model;

import java.util.ArrayList;

public class Cart extends Product{

	private int quantity;
	
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

	
	
	public ArrayList<Integer> getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(ArrayList<Integer> seatIds) {
		this.seatIds = seatIds;
	}

	@Override
	public String toString() {
		return "Cart [quantity=" + quantity + ", getQuantity()=" + getQuantity() + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getCategory()=" + getCategory() + ", getPrice()=" + getPrice()
				+ ", getImage()=" + getImage() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
