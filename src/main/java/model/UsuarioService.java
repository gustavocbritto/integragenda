package model;

import persistence.DAO;

public class UsuarioService extends DAO {
	
	public void gravar(Pessoa pessoa) throws Exception{
		
		
		open();
		stmt = con.prepareStatement("INSERT INTO pessoa(\r\n" + 
				"	nome, sobrenome, email, teelfone, sexo, senha, confirmarsenha)\r\n" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?);");
		stmt.setString(1, pessoa.getNome());
		stmt.setString(2, pessoa.getSobrenome());
		stmt.setString(3, pessoa.getEmail());
		stmt.setString(4, pessoa.getTelefone());
		stmt.setString(5, pessoa.getSexo());
		stmt.setString(6, pessoa.getSenha());
		stmt.setString(7, pessoa.getConfirmarsenha());	
		stmt.execute();
		
		close();

		
	}
	
}
