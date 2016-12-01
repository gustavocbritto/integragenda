package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
 
@ManagedBean(name = "agendaService")
@ApplicationScoped
public class agendaService implements Serializable{
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 786673173999872843L;

	private final static String[] colors;
     
    private final static String[] email;
   
     
    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";
         
        email = new String[10];
        email[0] = "BMW@gmail.com";
        email[1] = "Mercedes@gmail.com";
        email[2] = "Volvo@gmail.com";
        email[3] = "Audi@gmail.com";
        email[4] = "Renault@gmail.com";
        email[5] = "Fiat@gmail.com";
        email[6] = "Volkswagen@gmail.com";
        email[7] = "Honda@gmail.com";
        email[8] = "Jaguar@gmail.com";
        email[9] = "Ford@gmail.com";
    }
     
    public List<Agenda> createAgendas(int size) {
        List<Agenda> list = new ArrayList<Agenda>();
        for(int i = 0 ; i < size ; i++) {
        	Localizacao localizacao = new Localizacao("Rua Pereira Pinto", 115, 902, "Vitoria", "ES", "Brasil");
            list.add(new Agenda(i+1, getRandonDataInicio(), getRandonDataFim(), getRandomStatus(), new Sala(localizacao, 115)));
        }
         
        return list;
    }
    
    private String getRandonDataInicio() {
        return (int)((Math.random() * 23)+1) + ":" + (int)(Math.random() * 59);
    }
    
    private String getRandonDataFim() {
    	return (int)((Math.random() * 23)+1) + ":" + (int)(Math.random() * 59);
    }
          
    public boolean getRandomStatus() {
       if (Math.random() * 1000 > 500)
       {
    	   return false;
       }
       else
       {
    	   return true;  
       }

    }
     
    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
 
    public List<String> getColors() {
        return Arrays.asList(colors);
    }
     
    public List<String> getBrands() {
        return Arrays.asList(email);
    }
}