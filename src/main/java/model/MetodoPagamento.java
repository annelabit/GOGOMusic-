package model;

public class MetodoPagamento {

	private int userId;
	private int id;
	private String tipo;
	private String nomeCarta;
	private String numeroCarta;
	private String scadenza;
	private int cvv;
	private int main;
	private String last4Numbers;
	
	
	public MetodoPagamento() {}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getNomeCarta() {
		return nomeCarta;
	}


	public void setNomeCarta(String nomeCarta) {
		this.nomeCarta = nomeCarta;
	}


	

	public String getScadenza() {
		return scadenza;
	}


	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}


	


	public int getCvv() {
		return cvv;
	}


	public void setCvv(int cvv) {
		this.cvv = cvv;
	}


	public int getMain() {
		return main;
	}


	public void setMain(int main) {
		this.main = main;
	}


	public String getNumeroCarta() {
		return numeroCarta;
	}


	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}


	public String getLast4Numbers() {
		return last4Numbers;
	}


	public void setLast4Numbers(String last4Numbers) {
		this.last4Numbers = last4Numbers;
	}
	
	
}
