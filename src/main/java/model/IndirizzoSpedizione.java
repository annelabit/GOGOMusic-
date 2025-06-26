package model;

public class IndirizzoSpedizione {

	private int id;
	private int userId;
	private String nomeDestinatario;
	private String cognomeDestinatario;
	private String indirizzo;
	private int CAP;
	private String città;
	private String paese;
	private int main;
	
	
	public IndirizzoSpedizione() {
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getNomeDestinatario() {
		return nomeDestinatario;
	}


	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}


	public String getCognomeDestinatario() {
		return cognomeDestinatario;
	}


	public void setCognomeDestinatario(String cognomeDestinatario) {
		this.cognomeDestinatario = cognomeDestinatario;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public int getCAP() {
		return CAP;
	}


	public void setCAP(int cAP) {
		CAP = cAP;
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


	public int getMain() {
		return main;
	}


	public void setMain(int main) {
		this.main = main;
	}
	
	
	
}
