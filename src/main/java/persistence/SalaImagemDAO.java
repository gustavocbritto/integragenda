package persistence;


import model.Imagem;
import model.Sala;
import model.SalaImagem;

public class SalaImagemDAO extends DAO{

	public SalaImagem consulta(int idSalaImagem) throws Exception {
		
		SalaDAO salaDAO = new SalaDAO();
		ImagemDAO imagemDAO = new ImagemDAO();
		
		SalaImagem salaImagem = null;
		Sala sala;
		Imagem imagem;
		
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT idSala, idImagem FROM salaImagem");
		
		
		if(rs.next())
		{
			sala = salaDAO.consulta(rs.getInt("idSala"));
			imagem = imagemDAO.consulta(rs.getInt("idImagem"));
			salaImagem = new SalaImagem(sala,imagem);
			salaImagem.setSala(sala);
			salaImagem.setImagem(imagem);
		}
		
		close();
		return salaImagem;

	}

}
