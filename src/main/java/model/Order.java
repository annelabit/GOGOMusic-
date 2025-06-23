package model;

import java.sql.Date;
import java.util.ArrayList;

public class Order extends Product{

	private int orderId;
	private int uid;
	private int showId;
	private int quantity;
	private Date date;
	private String time;
	private String nome;
	private String email;
	private String città;
	private String paese;
	private String indirizzo;
	private int cap;
	private String nomeTitolare;
	private String numeroCarta;
	private int meseScadenza;
	private int annoScadenza;
	private int cvv;
	private String showDate;
	private String showTime;
	private ArrayList<Integer> showSeatIds;
	
	
	public Order() {}

	public Order(int orderId, int uid, int showId, int quantity, Date date, String time) {
		super();
		this.orderId = orderId;
		this.uid = uid;
		this.showId = showId;
		this.quantity = quantity;
		this.date = date;
		this.time=time;
	}

	public Order(int uid, int quantity, Date date, String time) {
		super();
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
		this.time=time;
	}
	
	public Order(int orderId, int uid, int showId, int quantity, Date date, String time, String nome, String email,
			String città, String paese, String indirizzo, int cap, String nomeTitolare, String numeroCarta,
			int meseScadenza, int annoScadenza, int cvv) {
		super();
		this.orderId = orderId;
		this.uid = uid;
		this.showId = showId;
		this.quantity = quantity;
		this.date = date;
		this.time = time;
		this.nome = nome;
		this.email = email;
		this.città = città;
		this.paese = paese;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.nomeTitolare = nomeTitolare;
		this.numeroCarta = numeroCarta;
		this.meseScadenza = meseScadenza;
		this.annoScadenza = annoScadenza;
		this.cvv = cvv;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getNomeTitolare() {
		return nomeTitolare;
	}

	public void setNomeTitolare(String nomeTitolare) {
		this.nomeTitolare = nomeTitolare;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public int getMeseScadenza() {
		return meseScadenza;
	}

	public void setMeseScadenza(int meseScadenza) {
		this.meseScadenza = meseScadenza;
	}

	public int getAnnoScadenza() {
		return annoScadenza;
	}

	public void setAnnoScadenza(int annoScadenza) {
		this.annoScadenza = annoScadenza;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public void setShowDate(String showDate) {
		this.showDate=showDate;
	}
	
	public void setShowTime(String showTime) {
		this.showTime=showTime;
	}

	public String getShowDate() {
		return showDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public ArrayList<Integer> getShowSeatIds() {
		return showSeatIds;
	}

	public void setShowSeatIds(ArrayList<Integer> showSeatIds) {
		this.showSeatIds = showSeatIds;
	}


}
