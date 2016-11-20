package persistence;

import model.Categoria;
import model.Localizacao;

public class LocalizacaoDAO extends DAO{
	
	public Localizacao consulta(int idLocalizacao) throws Exception {
		
		
		
		Localizacao localizacao = null;

		
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, rua, numero, complemento,cidade,estado, pais FROM Localizacao where id= "+idLocalizacao+"");
		
		
		if(rs.next())
		{
			localizacao = new Localizacao(rs.getString("rua"),rs.getInt("numero"), rs.getInt("complemento"), rs.getString("cidade"), rs.getString("estado"), rs.getString("pais"));
		}
		
		close();
		return localizacao;
}
	
	
}
