package persistence;


import java.sql.SQLException;

import model.Categoria;
import model.Pessoa;
import model.Usuario;

public class PessoaDAO extends DAO{

	public Pessoa consulta(int idPessoa) throws Exception {
		
		
		
		Pessoa pessoa = null;

		
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, nome, sobrenome, email, sexo, senha, confirmarsenha, teelfone FROM Pessoa where id= "+idPessoa+"");
		
		
		if(rs.next())
		{
			pessoa = new Pessoa(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("email"), rs.getString("sexo"),rs.getString("senha"),rs.getString("confirmarsenha"),rs.getString("teelfone"));
		}
		
		close();
		return pessoa;
}
	
	
	public void gravar(Usuario usuario) throws Exception{
		
		
		open();
		stmt = con.prepareStatement("INSERT INTO pessoa(\r\n" + 
				"	nome, sobrenome, email, telefone, sexo, senha, confirmarsenha)\r\n" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?);");
		stmt.setString(1, usuario.getPessoa().getNome());
		stmt.setString(2, usuario.getPessoa().getSobrenome());
		stmt.setString(3, usuario.getPessoa().getEmail());
		stmt.setString(4, usuario.getPessoa().getTelefone());
		stmt.setString(5, usuario.getPessoa().getSexo());
		stmt.setString(6, usuario.getPessoa().getSenha());
		stmt.setString(7, usuario.getPessoa().getConfirmarsenha());	
		stmt.execute();
		
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, nome, sobrenome, email, telefone, sexo, senha, confirmarsenha FROM Pessoa where email = '"+ usuario.getPessoa().getEmail() +"'");
		int idPessoa = -1;
		if (rs.next()) {
			idPessoa = rs.getInt("id");
		}
		
		stmt = con.prepareStatement("INSERT INTO usuario(\r\n" + 
				"	tipo, idPessoa)\r\n" + 
				"	VALUES (?, ?);");
		
		stmt.setString(1, "Locatario");
		stmt.setInt(2, idPessoa);
		stmt.execute();
		close();

		
	}
		
	
	public String verificaCadastro(Usuario usuario) throws Exception{
		
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, nome, sobrenome, email, telefone, sexo, senha, confirmarsenha \r\n"
				+ "FROM Pessoa where nome = '"+ usuario.getPessoa().getNome() +"' \r\n"+
				"and senha = '"+ usuario.getPessoa().getSenha()  +"'");
		if (rs.next()) {
			usuario.getPessoa().setSobrenome(rs.getString("sobrenome"));
			usuario.getPessoa().setEmail(rs.getString("email"));
			usuario.getPessoa().setTelefone(rs.getString("telefone"));
			usuario.getPessoa().setSexo(rs.getString("sexo"));
			usuario.getPessoa().setSenha(rs.getString("senha"));
			usuario.getPessoa().setConfirmarsenha(rs.getString("confirmarsenha"));
			close();
			return "selecaoSala";
		}
		close();
		
		return "Login";
	}

}
