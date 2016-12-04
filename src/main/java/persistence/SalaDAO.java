package persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Categoria;
import model.Imagem;
import model.Localizacao;
import model.Pessoa;
import model.Sala;
import model.Utensilio;

public class SalaDAO extends DAO {

	public void inserir(Sala sala) throws Exception {
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		open();
		
		stmt = con.prepareStatement("INSERT INTO sala(idCategoria, tamanhoMin, tamanhoMax, preco, idlocalizacao, descricao, idAdministrador, estrela, status, numero) VALUES(?,?,?,?,?,?,?,?,?,?) RETURNING ID;");
		
		sala.getLocalizacao().salvar();
				
		stmt.setInt(1, sala.getCategoria().getId());
		stmt.setInt(2, sala.getTamanhoMin());
		stmt.setInt(3, sala.getTamanhoMax());
		stmt.setDouble(4, sala.getPreco());
		stmt.setInt(5, sala.getLocalizacao().getId());
		stmt.setString(6, sala.getDescricao());
		stmt.setInt(7, sala.getPessoa().getId());
		stmt.setInt(8, sala.getEstrela());
		
		int status = 0;
		if(sala.getStatus())
			status = 1;
		
		stmt.setInt(9, status);
		stmt.setInt(10, sala.getNumeroSala());
		
		ResultSet rs = stmt.executeQuery();

		if(rs.next())
		{
			int id = rs.getInt(1);
			sala.setIdSala(id);
		}
		
		//Adicionar utensilios adicionado a sala;
		for(Utensilio u : sala.getUtensilios())
		{
			utensilioDAO.associarUtensilio(sala.getIdSala(), u);
		}
		
		//adiciona a imagem adicionada a sala;
		for(Imagem i : sala.getImagens())
		{
			associarImagem(sala.getIdSala(), i);
		}	
		
		close();
	}

	public List<Sala> consultar() throws Exception {

		List<Sala> salas = new ArrayList<Sala>();
		PessoaDAO pessoaDAO = new PessoaDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
		ImagemDAO imagemDAO =  new ImagemDAO();
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		Sala sala = null;
		Pessoa pessoa;
		Categoria categoria;
		Localizacao localizacao;
		ArrayList<Utensilio> utensilios;

		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, idcategoria, tamanhoMin, tamanhoMax, preco, idlocalizacao, descricao, idAdministrador, estrela, status, numero FROM sala");

		while (rs.next()) {
			categoria = categoriaDAO.consulta(rs
					.getInt("idcategoria"));
			pessoa = pessoaDAO.consulta(rs
					.getInt("idAdministrador"));
			localizacao = localizacaoDAO.consulta(rs.getInt("idLocalizacao"));
			sala = new Sala(rs.getInt("id"), categoria, rs.getInt("tamanhomin"),
					rs.getInt("tamanhomax"), rs.getDouble("preco"),
					localizacao, rs.getString("descricao"),
					pessoa,rs.getInt("estrela"),
					rs.getBoolean("status"));
			sala.setImagens(imagemDAO.getImagensSala(sala.getIdSala()));
			utensilios = utensilioDAO.consultaSalaUtensilio(sala.getIdSala());
			sala.setUtensilios(utensilios);
			sala.setNumeroSala(rs.getInt("numero"));
			salas.add(sala);

		}

		close();
		return salas;
	}

	public Sala consulta(int idSala) throws Exception{

		PessoaDAO pessoaDAO = new PessoaDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		ImagemDAO imagemDAO =  new ImagemDAO();
		
		Sala sala = null;
		Pessoa pessoa;
		Categoria categoria;
		Localizacao localizacao;
		ArrayList<Utensilio> utensilios;
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT idcategoria, tamanhoMin, tamanhoMax, preco, idlocalizacao, descricao, idAdministrador, estrela, status, numero FROM sala where sala.id = "+idSala+";");

		while (rs.next()) {
			categoria = categoriaDAO.consulta(rs.getInt("idcategoria"));
			pessoa = pessoaDAO.consulta(rs.getInt("idAdministrador"));
			localizacao = localizacaoDAO.consulta(rs.getInt("idLocalizacao"));
			
			sala = new Sala(categoria, rs.getInt("tamanhomin"),
					rs.getInt("tamanhomax"), rs.getDouble("preco"),
					localizacao, rs.getString("descricao"),
					pessoa,rs.getInt("estrela"),
					rs.getBoolean("status"));
			utensilios = utensilioDAO.consultaSalaUtensilio(idSala);
			sala.setUtensilios(utensilios);
			sala.setNumeroSala(rs.getInt("numero"));
			sala.setIdSala(idSala);
			sala.setImagens(imagemDAO.getImagensSala(idSala));
		}

		close();
		return sala;
	}

	public void updateCategoria(int idSala, Categoria categoria) throws Exception {
		open();
		stmt = con.prepareStatement("update sala set idcategoria = "+ categoria.getId() +" where sala.id = "+ idSala);
		stmt.executeUpdate();
		close();
	}

	public void updateTamanhoMax(int idSala, int tamanhoMax) throws Exception {
		open();
		stmt = con.prepareStatement("update sala set tamanhomax = "+ tamanhoMax +" where sala.id = "+ idSala +";");
		stmt.execute();
		
		close();
		
	}

	public void salvar(Sala sala) throws Exception {
		
		open();
		
		stmt = con.prepareStatement("UPDATE SALA SET preco = ?, descricao = ?, estrela = ?, numero = ? where sala.id = ?");
		
		stmt.setDouble(1, sala.getPreco());
		stmt.setString(2, sala.getDescricao());
		stmt.setInt(3, sala.getEstrela());
		stmt.setInt(4, sala.getNumeroSala());
		stmt.setInt(5, sala.getIdSala());
		sala.getLocalizacao().salvar();
		stmt.executeUpdate();
		close();
		
	}

	public void associarImagem(int idSala, Imagem imagem) throws Exception {
		open();
		
		stmt = con.prepareStatement("INSERT INTO SALA_IMAGEM (IDSALA, IDIMAGEM) VALUES(?,?)");
		
		stmt.setInt(1, idSala);
		stmt.setInt(2, imagem.getIdImagem());
		
		stmt.executeUpdate();
		close();
		
	}

	public boolean isDisponivel(int idSala, Date dt_inicial, Date dt_final) throws Exception {
		
		boolean retorno = false;
		
		open();
		
		stmt = con.prepareStatement("SELECT * FROM AGENDA WHERE DATAINICIO BETWEEN ? AND ? AND DATAFIM BETWEEN ? AND ? AND IDSALA = ?");
		
		stmt.setDate(1, new java.sql.Date (dt_inicial.getTime()));
		stmt.setDate(2, new java.sql.Date (dt_final.getTime()));
		stmt.setDate(3, new java.sql.Date (dt_inicial.getTime()));
		stmt.setDate(4, new java.sql.Date (dt_final.getTime()));
		stmt.setInt(5, idSala);
		
		rs = stmt.executeQuery();
		
		if(!rs.next())
		{
			retorno = true;
		}
		
		close();
		
		return retorno;
	}

	public List<Sala> consultarPorUsuario(int idUsuario) throws Exception{
		
		List<Sala> salas = new ArrayList<Sala>();
		PessoaDAO pessoaDAO = new PessoaDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
		ImagemDAO imagemDAO =  new ImagemDAO();
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		Sala sala = null;
		Categoria categoria;
		Localizacao localizacao;
		ArrayList<Utensilio> utensilios;
		Pessoa pessoa;
		open();
		
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, idcategoria, tamanhoMin, tamanhoMax, preco, idlocalizacao, descricao, estrela, status, numero FROM SALA WHERE idAdministrador = "+idUsuario+";");
	
		while (rs.next()) {
			categoria = categoriaDAO.consulta(rs
					.getInt("idcategoria"));
			pessoa = pessoaDAO.consulta(idUsuario);
			localizacao = localizacaoDAO.consulta(rs.getInt("idLocalizacao"));
			sala = new Sala(rs.getInt("id"), categoria, rs.getInt("tamanhomin"),
					rs.getInt("tamanhomax"), rs.getDouble("preco"),
					localizacao, rs.getString("descricao"),
					pessoa,rs.getInt("estrela"),
					rs.getBoolean("status"));
			sala.setImagens(imagemDAO.getImagensSala(sala.getIdSala()));
			utensilios = utensilioDAO.consultaSalaUtensilio(sala.getIdSala());
			sala.setUtensilios(utensilios);
			sala.setNumeroSala(rs.getInt("numero"));
			salas.add(sala);

		}

		close();
		return salas;
		
	}

	public boolean isDisponivel(int idSala) throws Exception{
		boolean retorno = false;
		
		open();
		
		stmt = con.prepareStatement("SELECT * FROM AGENDA WHERE IDSALA = ?");
		
		stmt.setInt(1, idSala);
		
		rs = stmt.executeQuery();
		
		if(!rs.next())
		{
			retorno = true;
		}
		
		close();
		
		return retorno;
		
	}

	public void deltar(Sala sala)  throws Exception
	{
		
		
		for(Imagem i : sala.getImagens())
		{
			desassociarImagem(sala.getIdSala(), i);
		}
		
		for(Utensilio u : sala.getUtensilios())
		{
			desassociarUtensilio(sala.getIdSala(), u);
		}
		
		open();
		
		stmt = con.prepareStatement("DELETE FROM SALA WHERE ID = ?");
		
		stmt.setInt(1, sala.getIdSala());
		
		stmt.executeUpdate();
				
		close();
		
	}
	
	private void desassociarUtensilio(int idSala, Utensilio u) throws Exception{
		
		open();
		
		stmt = con.prepareStatement("DELETE FROM SALA_UTENSILIO WHERE IDSALA = ? AND IDUTENSILIO = ?");
		
		stmt.setInt(1, idSala);
		stmt.setInt(2, u.getIdUtensilio());
		
		stmt.executeUpdate();
		
		close();

		
	}

	public void desassociarImagem(int idSala, Imagem imagem) throws Exception {
		
		open();
		
		stmt = con.prepareStatement("DELETE FROM SALA_IMAGEM WHERE IDSALA = ? AND IDIMAGEM = ?");
		
		stmt.setInt(1, idSala);
		stmt.setInt(2, imagem.getIdImagem());
		
		stmt.executeUpdate();
		close();
		
		imagem.deletar();
	}
	
}
