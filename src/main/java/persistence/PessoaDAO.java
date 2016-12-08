package persistence;


import Controller.Usuario;
import model.Pessoa;

public class PessoaDAO extends DAO{

	public Pessoa consulta(int idPessoa) throws Exception {
		
		
		
		Pessoa pessoa = null;
		TipoDAO tipoDAO = new TipoDAO();
		
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, nome, sobrenome, email, sexo, senha, confirmarsenha, telefone, tipo FROM Pessoa where id= "+idPessoa+"");
		
		
		if(rs.next())
		{
			pessoa = new Pessoa();
			
			pessoa.setNome(rs.getString("nome"));
			pessoa.setSobrenome(rs.getString("sobrenome"));
			pessoa.setEmail(rs.getString("email"));
			pessoa.setSexo(rs.getString("sexo"));
			pessoa.setSenha(rs.getString("senha"));
			pessoa.setConfirmarsenha(rs.getString("confirmarsenha"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setTipo(tipoDAO.consulta(rs.getInt("tipo")));
			pessoa.setId(idPessoa);
			
		}
		
		close();
		return pessoa;
}
	
	
	public void gravar(Usuario usuario) throws Exception{
		
		
		open();
		stmt = con.prepareStatement("INSERT INTO pessoa(\r\n" + 
				"	nome, sobrenome, email, telefone, sexo, senha, confirmarsenha, tipo)\r\n" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
		
		stmt.setString(1, usuario.getPessoa().getNome());
		stmt.setString(2, usuario.getPessoa().getSobrenome());
		stmt.setString(3, usuario.getPessoa().getEmail());
		stmt.setString(4, usuario.getPessoa().getTelefone());
		stmt.setString(5, usuario.getPessoa().getSexo());
		stmt.setString(6, usuario.getPessoa().getSenha());
		stmt.setString(7, usuario.getPessoa().getConfirmarsenha());	
		stmt.setInt(8, 2);	
		stmt.execute();
		
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, nome, sobrenome, email, telefone, sexo, senha, confirmarsenha FROM Pessoa where email = '"+ usuario.getPessoa().getEmail() +"'");
		int idPessoa = -1;
		if (rs.next()) {
			idPessoa = rs.getInt("id");
		}
		usuario.getPessoa().setId(idPessoa);
		
		close();

		
	}
		
	
	public boolean verificaCadastro(Usuario usuario) throws Exception{
		boolean retorno = false;
		TipoDAO tipoDAO = new TipoDAO();
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, nome, sobrenome, email, telefone, sexo, senha, confirmarsenha, tipo \r\n"
				+ "FROM Pessoa where nome = '"+ usuario.getPessoa().getNome() +"' \r\n"+
				"and senha = '"+ usuario.getPessoa().getSenha()  +"'");
		if (rs.next()) {
			usuario.getPessoa().setSobrenome(rs.getString("sobrenome"));
			usuario.getPessoa().setEmail(rs.getString("email"));
			usuario.getPessoa().setTelefone(rs.getString("telefone"));
			usuario.getPessoa().setSexo(rs.getString("sexo"));
			usuario.getPessoa().setSenha(rs.getString("senha"));
			usuario.getPessoa().setConfirmarsenha(rs.getString("confirmarsenha"));
			usuario.getPessoa().setTipo(tipoDAO.consulta(rs.getInt("tipo")));
			usuario.getPessoa().setId(rs.getInt("id"));
			close();
			retorno = true;
		}
		close();
		
		return retorno;
	}
	
	public void update(Pessoa pessoa) throws Exception {


		
		open();
		stmt = con.prepareStatement("UPDATE pessoa SET sobrenome= ?, email=?, sexo=?,telefone=? WHERE id = ?;");
		stmt.setString(1, pessoa.getSobrenome());
		stmt.setString(2, pessoa.getEmail());
		stmt.setString(3, pessoa.getSexo());
		stmt.setString(4, pessoa.getTelefone());
		stmt.setInt(5, pessoa.getId());
		stmt.executeUpdate();
		close();
		
	}

}
