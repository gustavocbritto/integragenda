package persistence;

import model.Sala;
import model.SalaUtensilio;
import model.Utensilio;

public class SalaUtensilioDAO extends DAO{

	
	public SalaUtensilio consulta(int idSalaUtensilio) throws Exception {
		
		SalaDAO salaDAO = new SalaDAO();
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		
		SalaUtensilio salaUtensilio = null;
		Sala sala;
		Utensilio utensilio;
		
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT idSala, idUtensilio FROM salaUtensilio");
		
		
		if(rs.next())
		{
			sala = salaDAO.consulta(rs.getInt("idSala"));
			utensilio = utensilioDAO.consulta(rs.getInt("idUtensilio"));
			salaUtensilio = new SalaUtensilio(sala,utensilio);
			salaUtensilio.setSala(sala);
			salaUtensilio.setUtensilio(utensilio);
		}
		
		close();
		return salaUtensilio;

	}

}
