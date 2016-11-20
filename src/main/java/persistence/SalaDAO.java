package persistence;

import java.util.ArrayList;
import java.util.List;

import model.Administrador;
import model.Categoria;
import model.Localizacao;
import model.Pessoa;
import model.Sala;
import model.SalaImagem;
import model.SalaUtensilio;

public class SalaDAO extends DAO {

	public void inserir(Sala sala) throws Exception {
		open();
		stmt = con
				.prepareStatement("INSERT INTO sala(idCategoria, idSalaUtensilio, tamanhoMin, tamanhoMax, pre�o, localizacao, descricao, idAdministrador, idPessoa, estrela, status, idSalaImagem) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		stmt.setInt(1, sala.getCategoria().getId());
		stmt.setInt(2, sala.getSalaUtensilio().getIdSalaUtensilio());
		stmt.setInt(3, sala.getTamanhoMin());
		stmt.setInt(4, sala.getTamanhoMax());
		stmt.setDouble(5, sala.getPreco());
		stmt.setInt(6, sala.getLocalizacao().getId());
		stmt.setString(7, sala.getDescricao());
		stmt.setInt(8, sala.getAdministrador().getIdAdministrador());
		stmt.setInt(9, sala.getPessoa().getId());
		stmt.setInt(10, sala.getEstrela());
		stmt.setBoolean(11, sala.getStatus());
		stmt.setInt(12, sala.getSalaImagem().getIdSalaImagem());
		stmt.execute();

		close();
	}

	public List<Sala> consultar() throws Exception {

		List<Sala> salas = new ArrayList<Sala>();
		SalaImagemDAO salaImagemDAO = new SalaImagemDAO();
		AdministradorDAO administradorDAO = new AdministradorDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
		
		Sala sala = null;
		Administrador administrador;
		Categoria categoria;
		Localizacao localizacao;

		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT idcategoria, tamanhoMin, tamanhoMax, preco, idlocalizacao, descricao, idAdministrador, estrela, status FROM sala");

		while (rs.next()) {
			categoria = categoriaDAO.consulta(rs
					.getInt("idcategoria"));
			administrador = administradorDAO.consulta(rs
					.getInt("idAdministrador"));
			localizacao = localizacaoDAO.consulta(rs.getInt("idLocalizacao"));
			sala = new Sala(categoria, rs.getInt("tamanhomin"),
					rs.getInt("tamanhomax"), rs.getDouble("preco"),
					localizacao, rs.getString("descricao"),
					administrador,rs.getInt("estrela"),
					rs.getBoolean("status"));
			salas.add(sala);

		}

		close();
		return salas;
	}

	public Sala consulta(int int1) {
		// TODO Auto-generated method stub
		return null;
	}
}
