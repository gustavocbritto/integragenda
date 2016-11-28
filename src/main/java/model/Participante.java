package model;

import java.io.Serializable;

public class Participante implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7179751320118979931L;
	int idParticipantes;
	String email;
	Sala Sala;
	
	public Participante(String email, model.Sala sala) {
		super();
		//this.idParticipantes = idParticipantes;
		this.email = email;
		Sala = sala;
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

	public Sala getSala() {
		return Sala;
	}

	public void setSala(Sala sala) {
		Sala = sala;
	}

	
	
	
}
