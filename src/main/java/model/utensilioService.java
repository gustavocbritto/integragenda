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
	private static final long serialVersionUID = -6406069601890203800L;
	
	private ArrayList<Utensilio> utensilios;
	UtensilioDAO utensilioDAO = new UtensilioDAO();
	public ArrayList<Utensilio> getUtensilios() throws Exception
	{
		return utensilioDAO.getLista();
	}
}