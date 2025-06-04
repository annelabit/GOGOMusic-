package model;

public class Order extends Product{

	private int orderId;
	private int uid;
	private int showId;
	private int quantity;
	private String date;
	private String time;
	
	public Order() {}

	public Order(int orderId, int uid, int showId, int quantity, String date, String time) {
		super();
		this.orderId = orderId;
		this.uid = uid;
		this.showId = showId;
		this.quantity = quantity;
		this.date = date;
		this.time=time;
	}

	public Order(int uid, int quantity, String date, String time) {
		super();
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
		this.time=time;
	}


	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", uid=" + uid + ", showId=" + showId + ", quantity=" + quantity
				+ ", date=" + date + ", time=" + time + "]";
	}

}
