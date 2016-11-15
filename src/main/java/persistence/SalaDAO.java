package persistence;

import java.util.ArrayList;
import java.util.List;

import model.Administrador;
import model.Pessoa;
import model.Sala;
import model.SalaImagem;
import model.SalaUtensilio;

public class SalaDAO extends DAO {

	public void inserir(Sala sala) throws Exception {
		open();
		stmt = con
				.prepareStatement("INSERT INTO sala(categoria, idSalaUtensilio, tamanhoMin, tamanhoMax, preço, localização, descricao, idAdministrador, idPessoa, estrela, status, idSalaImagem) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		stmt.setString(1, sala.getCategoria());
		stmt.setInt(2, sala.getSalaUtensilio().getIdSalaUtensilio());
		stmt.setInt(3, sala.getTamanhoMin());
		stmt.setInt(4, sala.getTamanhoMax());
		stmt.setDouble(5, sala.getPreço());
		stmt.setString(6, sala.getLocalização());
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
		SalaUtensilioDAO salaUtensilioDAO = new SalaUtensilioDAO();
		SalaImagemDAO salaImagemDAO = new SalaImagemDAO();
		AdministradorDAO administradorDAO = new AdministradorDAO();
		PessoaDAO pessoaDAO = new PessoaDAO();

		Sala sala;
		SalaUtensilio salaUtensilio;
		SalaImagem salaImagem;
		Administrador administrador;
		Pessoa pessoa;

		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT categoria, idSalaUtensilio, tamanhoMin, tamanhoMax, preço, localização, descricao, idAdministrador, idPessoa, estrela, status, idSalaImagem FROM sala");

		while (rs.next()) {
			salaUtensilio = salaUtensilioDAO.consulta(rs
					.getInt("idSalaUtensilio"));
			salaImagem = salaImagemDAO.consulta(rs.getInt("idSalaImagem"));
			administrador = administradorDAO.consulta(rs
					.getInt("idAdministrador"));
			pessoa = pessoaDAO.consulta(rs.getInt("idPessoa"));
			sala = new Sala(rs.getString("categoria"), salaUtensilio,
					rs.getInt("tamanhoMin"), rs.getInt("tamanhoMax"),
					salaImagem, rs.getDouble("preço"),
					rs.getString("localizacao"), rs.getString("descricao"),
					administrador, pessoa, rs.getInt("estrela"),
					rs.getBoolean("status"));
			sala.setSalaUtensilio(salaUtensilio);
			sala.setSalaImagem(salaImagem);
			sala.setAdministrador(administrador);
			sala.setPessoa(pessoa);
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
