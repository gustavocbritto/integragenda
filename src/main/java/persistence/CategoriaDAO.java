package persistence;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.Categoria;
import model.Sala;
import model.Utensilio;

public class CategoriaDAO extends DAO {
	
	
	public Categoria consulta(int idCategoria) throws Exception {
			
	
			
			Categoria categoria = null;
	
			
			open();
			st = con.createStatement();
			rs = st.executeQuery("SELECT id, descricao FROM Categoria where id= "+idCategoria+"");
			
			
			if(rs.next())
			{
				categoria = new Categoria(rs.getString("descricao"));
			}
			
			close();
			return categoria;
	}
	
	public ArrayList<Categoria> getLista() throws Exception
	{
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		
		open();
		
		stmt = con.prepareStatement("select * from categoria;");
		stmt.executeQuery();
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next())
		{
			int id = rs.getInt("id");
			String descricao = rs.getString("descricao");
			categorias.add(new Categoria(id, descricao));
		}
		
		close();
		
		return categorias;
	}
}
