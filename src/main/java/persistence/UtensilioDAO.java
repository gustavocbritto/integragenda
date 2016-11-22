package persistence;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.Utensilio;

public class UtensilioDAO extends DAO{


	public ArrayList<Utensilio> consultaSalaUtensilio(int idSala) throws Exception 
	{
		ArrayList<Utensilio> utensilios = new ArrayList<Utensilio>();
		
		open();
		
		stmt = con.prepareStatement("select utensilio.id, utensilio.descricao, sala_utensilio.qtd from utensilio "+
				"inner join sala_utensilio on utensilio.id = sala_utensilio.idutensilio "+
				"inner join sala on sala_utensilio.idsala = sala.id "+
				"where sala.id = "+idSala+";");
		stmt.executeQuery();
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next())
		{
			int id = rs.getInt(1);
			String descricao = rs.getString(2);
			int qtd = rs.getInt(3);
			utensilios.add(new Utensilio(id, descricao, qtd));
		}
		
		close();
		
		return utensilios;
	}
	
	public ArrayList<Utensilio> getLista() throws Exception
	{
		ArrayList<Utensilio> utensilios = new ArrayList<Utensilio>();
		
		open();
		
		stmt = con.prepareStatement("select * from utensilio;");
		stmt.executeQuery();
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next())
		{
			int id = rs.getInt("id");
			String descricao = rs.getString("descricao");
			utensilios.add(new Utensilio(id, descricao));
		}
		//stmt.execute();
		
		close();
		
		return utensilios;
	}

}
