package model;

import java.sql.Date;

public class User {

	private int idUtente;
	private String email;
	private String nome;
	private String cognome;
	private String username;
	private boolean isAdmin;
	private Date date; 
	
	public User() {
	}
	
	public User(int idUtente, String email, String nome, String cognome, String username, boolean isAdmin) {
		this.idUtente = idUtente;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.isAdmin = isAdmin;
	}
	
	
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [idUtente=" + idUtente + ", email=" + email + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", isAdmin=" + isAdmin + "]";
	}
	
}
