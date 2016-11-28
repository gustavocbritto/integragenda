package persistence;

import java.util.ArrayList;

import model.Imagem;
import model.Sala;

public class ImagemDAO extends DAO{

	public Imagem consulta(int idSalaImagem) {
		// TODO Auto-generated method stub
		return null;
	}
	
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


}
