package model;

public class Agenda {
	
	int idAgenda;
	String horaInicio;
	String horaFim;
	int numeroSala;
	String local;
	int qtdParticipantes;
	Boolean status;
	Sala sala;

	public Agenda(int idAgenda, String horaInicio, String horaFim,
			int numeroSala, String local, int qtdParticipantes, Boolean status,
			Sala sala) {
		super();
		this.idAgenda = idAgenda;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.numeroSala = numeroSala;
		this.local = local;
		this.qtdParticipantes = qtdParticipantes;
		this.status = status;
		this.sala = sala;
	}

	public int getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getQtdParticipantes() {
		return qtdParticipantes;
	}

	public void setQtdParticipantes(int qtdParticipantes) {
		this.qtdParticipantes = qtdParticipantes;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	
	
}
