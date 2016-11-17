package model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;

 
@ManagedBean(name="angendaBean")
@ViewScoped
public class angendaBean implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Agenda> agendas;
    
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
    
	public void confirmar() {
        addMessage("Confirmação:", "Presença confirmada!");
    }
	
	public void cancelar() {
        addMessage("System Error", "Please try again later.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
