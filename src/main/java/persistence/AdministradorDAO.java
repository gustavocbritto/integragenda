package persistence;

import model.Administrador;
import model.Categoria;
import model.Pessoa;

public class AdministradorDAO extends DAO{

	public Administrador consulta(int idAdministrador) throws Exception {
		Administrador administrador = null;
		
		
		open();
		st = con.createStatement();
		rs = st.executeQuery("SELECT id, tipo, idPessoa FROM Administrador where id= "+idAdministrador+"");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		
		if(rs.next())
		{	
			Pessoa pessoa = pessoaDAO.consulta(rs.getInt("idPessoa"));
			administrador = new Administrador(rs.getString("tipo"), pessoa);
			administrador.setIdAdministrador(idAdministrador);
		}
		
		close();
		return administrador;

	}

}
