package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import persistence.UtensilioDAO;


@ManagedBean(name = "utensilioService")
@ApplicationScoped
public class utensilioService implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7886771518613693599L;
	UtensilioDAO utensilioDAO = new UtensilioDAO();
	public ArrayList<Utensilio> getUtensilios() throws Exception
	{
		return utensilioDAO.getLista();
	}
}
