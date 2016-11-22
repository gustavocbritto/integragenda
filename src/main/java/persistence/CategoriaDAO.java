package persistence;

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
}
