package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

 
@ManagedBean(name="agendaBean")
@SessionScoped
//ViewScoped
public class AgendaBean implements Serializable {
 

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7793338733478542233L;
	
	private List<Agenda> agendas;
	private Agenda agendaAtual;
    private String email = "";
    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!email.equals("")){
			agendaAtual.adicionarNovoParticipante(email);
			this.email = email;
		}
	}

	public Agenda getAgendaAtual() {
		return agendaAtual;
	}

	public void setAgendaAtual(Agenda agendaAtual) {
		this.agendaAtual = agendaAtual;
	}

	@ManagedProperty("#{agendaService}")
    private agendaService service;
 
    @PostConstruct
    public void init() {
    	agendas = service.createAgendas(10);
    }
     
    public List<Agenda> getAgendas() {
        return agendas;
    }
 
    public void setService(agendaService service) {
        this.service = service;
    }
    
	public void confirmar(Agenda agenda) {
		boolean status = !agenda.getStatus();
		agenda.setStatus(status);
		String mensagem = status ? "Não confirmado!" : "Confirmado!";
        addMessage("Status:", mensagem);
    }
	
	public void cancelar(Agenda agenda) {
		if(this.agendas.remove(agenda))
		{
			addMessage("Confirmação:", "Agenda deletada com sucesso!");
		}
		else
		{
			addMessage("Confirmação:", "Agenda não pode ser deletada!");
		}
	} 
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public Agenda getAgendaById(int id)
    {
    	return agendas.get(id);
    }
    
    public void guardaAgenda(Agenda agenda)
    {
    	this.agendaAtual = agenda;
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("participantes.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetEmail()
    {
    	RequestContext.getCurrentInstance().reset("email");
    }
}
