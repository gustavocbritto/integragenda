package persistence;

import java.sql.ResultSet;

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
			localizacao.setId(idLocalizacao);
		}
		
		close();
		return localizacao;
}

	public void salvar(Localizacao localizacao)throws Exception {
		open();
		
		stmt = con.prepareStatement("UPDATE LOCALIZACAO SET rua = ?, numero = ?, complemento = ?,cidade = ?,estado = ?, pais = ? where LOCALIZACAO.ID = ?");
		
		stmt.setString(1, localizacao.getRua());
		stmt.setInt(2, localizacao.getNumero());
		stmt.setInt(3, localizacao.getComplemento());
		stmt.setString(4, localizacao.getCidade());
		stmt.setString(5, localizacao.getEstado());
		stmt.setString(6, localizacao.getPais());
		stmt.setInt(7, localizacao.getId());
		
		stmt.executeUpdate();
		close();		
	}
	
	public void inserir(Localizacao localizacao)throws Exception {
		
		open();
		
		stmt = con.prepareStatement("INSERT INTO LOCALIZACAO(RUA, NUMERO, COMPLEMENTO, CIDADE, ESTADO, PAIS)"+
				" VALUES (?,?,?,?,?,?) RETURNING ID;");
		
		stmt.setString(1, localizacao.getRua());
		stmt.setInt(2, localizacao.getNumero());
		stmt.setInt(3, localizacao.getComplemento());
		stmt.setString(4, localizacao.getCidade());
		stmt.setString(5, localizacao.getEstado());
		stmt.setString(6, localizacao.getPais());
	
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next())
		{
			int id = rs.getInt(1);
			localizacao.setId(id);
		}
		
		close();
		
	}
	
	
}
