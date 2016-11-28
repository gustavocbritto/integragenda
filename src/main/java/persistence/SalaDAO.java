package persistence;

import java.util.ArrayList;
import java.util.List;

import model.Administrador;
import model.Categoria;
import model.Imagem;
import model.Localizacao;
import model.Pessoa;
import model.Sala;
import model.Utensilio;

public class SalaDAO extends DAO {

	public void inserir(Sala sala) throws Exception {
		open();
		stmt = con.prepareStatement("INSERT INTO sala(idCategoria, tamanhoMin, tamanhoMax, preco, localizacao, descricao, idAdministrador, idPessoa, estrela, status) VALUES(?,?,?,?,?,?,?,?,?,?)");
		
		stmt.setInt(1, sala.getCategoria().getId());
		stmt.setInt(2, sala.getTamanhoMin());
		stmt.setInt(3, sala.getTamanhoMax());
		stmt.setDouble(4, sala.getPreco());
		stmt.setInt(5, sala.getLocalizacao().getId());
		stmt.setString(6, sala.getDescricao());
		stmt.setInt(7, sala.getAdministrador().getIdAdministrador());
		stmt.setInt(8, sala.getPessoa().getId());
		stmt.setInt(9, sala.getEstrela());
		stmt.setBoolean(10, sala.getStatus());
		stmt.execute();

		close();
	}

	public List<Sala> consultar() throws Exception {

		List<Sala> salas = new ArrayList<Sala>();
		AdministradorDAO administradorDAO = new AdministradorDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
		ImagemDAO imagemDAO =  new ImagemDAO();
		Sala sala = null;
		Administrador administrador;
		Categoria categoria;
		Localizacao localizacao;

		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, idcategoria, tamanhoMin, tamanhoMax, preco, idlocalizacao, descricao, idAdministrador, estrela, status FROM sala");

		while (rs.next()) {
			categoria = categoriaDAO.consulta(rs
					.getInt("idcategoria"));
			administrador = administradorDAO.consulta(rs
					.getInt("idAdministrador"));
			localizacao = localizacaoDAO.consulta(rs.getInt("idLocalizacao"));
			sala = new Sala(rs.getInt("id"), categoria, rs.getInt("tamanhomin"),
					rs.getInt("tamanhomax"), rs.getDouble("preco"),
					localizacao, rs.getString("descricao"),
					administrador,rs.getInt("estrela"),
					rs.getBoolean("status"));
			sala.setImagens(imagemDAO.getImagensSala(sala.getIdSala()));
			salas.add(sala);

		}

		close();
		return salas;
	}

	public Sala consulta(int idSala) throws Exception{

		AdministradorDAO administradorDAO = new AdministradorDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		ImagemDAO imagemDAO =  new ImagemDAO();
		
		Sala sala = null;
		Administrador administrador;
		Categoria categoria;
		Localizacao localizacao;
		ArrayList<Utensilio> utensilios;
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT idcategoria, tamanhoMin, tamanhoMax, preco, idlocalizacao, descricao, idAdministrador, estrela, status FROM sala where sala.id = "+idSala+";");

		while (rs.next()) {
			categoria = categoriaDAO.consulta(rs.getInt("idcategoria"));
			administrador = administradorDAO.consulta(rs.getInt("idAdministrador"));
			localizacao = localizacaoDAO.consulta(rs.getInt("idLocalizacao"));
			
			sala = new Sala(categoria, rs.getInt("tamanhomin"),
					rs.getInt("tamanhomax"), rs.getDouble("preco"),
					localizacao, rs.getString("descricao"),
					administrador,rs.getInt("estrela"),
					rs.getBoolean("status"));
			utensilios = utensilioDAO.consultaSalaUtensilio(idSala);
			sala.setUtensilios(utensilios);
			sala.setNumeroSala(115);
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
		
		stmt = con.prepareStatement("UPDATE SALA SET preco = ?, descricao = ?, estrela = ? where sala.id = ?");
		
		stmt.setDouble(1, sala.getPreco());
		stmt.setString(2, sala.getDescricao());
		stmt.setInt(3, sala.getEstrela());
		stmt.setInt(4, sala.getIdSala());
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
}
