package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import persistence.AgendaDAO;
import persistence.ParticipanteDAO;

public class Agenda implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 379232833692722473L;
	
	private int id;
	private String horaInicio;
	private String horaFim;
	private int qtdParticipantes; //Feito somente por causa do jsf pra ele pegar o valor pela variavel. Pode ser revisto
	private Boolean status;
	private Sala sala;
	private List<Participante> participantes = new ArrayList<Participante>();
	private Date dataInicio;
	private Date dataFim;
	private AgendaDAO agendaDAO = new AgendaDAO();
	private ParticipanteDAO participanteDAO = new ParticipanteDAO();
	
	public Agenda()
	{
		super();
	}
	public Agenda(int idAgenda, String horaInicio, String horaFim, Boolean status,
			Sala sala) {
		super();
		this.id = idAgenda;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.status = status;
		this.sala = sala;
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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
	
	public void adicionarNovoParticipante(String email) throws Exception
	{
		if(!temEmailRepetido(email))
		{
			Participante participante = new Participante();
			participante.setEmail(email);
			participanteDAO.inserir(id, participante);
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
	public void removerParticipante(Participante participante) throws Exception
	{
		participanteDAO.desassociarAgendaParticipante(id, participante);
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
	public void salvarStatus() throws Exception {
		agendaDAO.salvarStatus(this);
		
	}
	public void delete() throws Exception {
		agendaDAO.remover(this);
		
	}

	
	
}
