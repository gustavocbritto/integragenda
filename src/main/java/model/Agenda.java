package model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
	
	int id;
	String horaInicio;
	String horaFim;
	int numeroSala;
	String local;
	int qtdParticipantes;
	Boolean status;
	Sala sala;
	List<Participante> participantes = new ArrayList<Participante>();

	public Agenda(int idAgenda, String horaInicio, String horaFim, Boolean status,
			Sala sala) {
		super();
		this.id = idAgenda;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.status = status;
		this.sala = sala;
	}

	public int getId() {
		return id;
	}

	public void setId(int idAgenda) {
		this.id = idAgenda;
	}

	public String getHoraInicio() {
		return this.horaInicio;
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

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public void adicionarParticipante(Participante participante)
	{
		this.participantes.add(participante);
	}
	
	public void adicionarNovoParticipante(String email)
	{
		if(!temEmailRepetido(email))
		{
			Participante participante = new Participante(email, this.sala);
			this.participantes.add(participante);
		}
	}
	
	public boolean temEmailRepetido(String email)
	{
		boolean achou = false;
		for(Participante p: participantes)
		{
			if(p.getEmail().equals(email))
			{
				achou = true;
				break;
			}
		}
		return achou;		
	}
	public void removerParticipante(Participante participante)
	{
		this.participantes.remove(participante);
	}
	
	public int getNumeroParticipantes()
	{
		if(participantes == null){
			return 0;
		}else{
			return this.participantes.size();
		}
	}

	
	
}
