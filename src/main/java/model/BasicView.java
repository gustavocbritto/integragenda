package model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
//import org.primefaces.showcase.domain.Car;
//import org.primefaces.showcase.service.CarService;
 
@ManagedBean(name="dtBasicView")
@ViewScoped
public class BasicView implements Serializable {
     
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
}
