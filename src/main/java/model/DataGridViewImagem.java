package model;
 
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

 
@ManagedBean(name="dataGridViewImagem")
@ViewScoped
public class DataGridViewImagem implements Serializable {
     


	/**
	 * 
	 */
	private static final long serialVersionUID = 8068974059886159440L;

	private List<Sala> salas;
     
    private Sala salaSelecionada;
   
    @PostConstruct
    public void init() {
        this.salas = this.getSalas();
    }
    
    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
 
    public List<Sala> getSalas() {
        return salas;
    }
 
 
    public Sala getSalaSelecionada() {
        return salaSelecionada;
    }
 
    public void setSalaSelecionada(Sala salaSelecionada) {
        this.salaSelecionada = salaSelecionada;
    }
}
