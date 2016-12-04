package persistence;

public class TipoDAO extends DAO{
	
	public String consulta(int idTipo) throws Exception {	
		open();
		
		stmt = con.prepareStatement("SELECT TIPO FROM TIPO WHERE ID = ?");
		
		stmt.setInt(1, idTipo);

		
		rs = stmt.executeQuery();
		String tipo = "";
		if(rs.next())
		{
			tipo = rs.getString(1);
		}
		
		close();
		
		return tipo;
	}


}
