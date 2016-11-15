package model;

public class SalaUtensilio {
	
	int idSalaUtensilio;
	Sala sala;
	Utensilio utensilio;


	public SalaUtensilio(Sala sala, Utensilio utensilio) {
		super();
		this.sala = sala;
		this.utensilio = utensilio;
	}

	public int getIdSalaUtensilio() {
		return idSalaUtensilio;
	}

	public void setIdSalaUtensilio(int idSalaUtensilio) {
		this.idSalaUtensilio = idSalaUtensilio;
	}

	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Utensilio getUtensilio() {
		return utensilio;
	}
	public void setUtensilio(Utensilio utensilio) {
		this.utensilio = utensilio;
	}
	
	
	
}
