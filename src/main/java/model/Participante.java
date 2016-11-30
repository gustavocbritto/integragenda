package model;

import java.io.Serializable;

public class Participante implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1560121457885628125L;
	int idParticipantes;
	String email;
	
	public Participante(int id, String email) {
		super();
		//this.idParticipantes = idParticipantes;
		this.email = email;
		this.idParticipantes = id;
	}

	public Participante() {
		super();
	}

	public int getIdParticipantes() {
		return idParticipantes;
	}

	public void setIdParticipantes(int idParticipantes) {
		this.idParticipantes = idParticipantes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
}
