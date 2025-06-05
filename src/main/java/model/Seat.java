package model;

public class Seat {

	private int id;
	private int available;
	private String type;
	
	private int row;
	private int column;
	
	public Seat() {
		super();
	}

	public Seat(int id, int available, String type, int row, int column) {
		this.id = id;
		this.available = available;
		this.type = type;
		this.row = row;
		this.column = column;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getAvailable() {
		return available;
	}
	
	
}
