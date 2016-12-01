package persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.Imagem;


public class ImagemDAO extends DAO{
	
	public ArrayList<Imagem> getImagensSala(int idSala) throws Exception {
				
		ArrayList<Imagem> imagens = new ArrayList<Imagem>();
		
		open();
		st = con.createStatement();
		rs = st.executeQuery("select imagem.id, imagem.caminho"+
							" from imagem" +
							" inner join sala_imagem on imagem.id = sala_imagem.idimagem"+
							" inner join sala on sala_imagem.idsala = sala.id"+
							" where sala.id = "+ idSala +";");
		
		
		if(rs.next())
		{
			imagens.add(new Imagem(rs.getInt(1), rs.getString(2)));
		}
		
		close();
		
		return imagens;

	}

	public void inserir(Imagem imagem) throws Exception {	
		open();
		
		stmt = con.prepareStatement("INSERT INTO IMAGEM (CAMINHO) VALUES (?) RETURNING ID;");
		
		stmt.setString(1, imagem.getUrl());

		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next())
		{
			int idImagem = rs.getInt(1);
			imagem.setIdImagem(idImagem);
		}
		
		close();
	}


}
