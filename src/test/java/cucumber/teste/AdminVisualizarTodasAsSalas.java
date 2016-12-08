package cucumber.teste;

import model.ControleSalasBean;
import model.Pessoa;
import model.Sala;
import model.Usuario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;


public class AdminVisualizarTodasAsSalas {

	private Usuario usuario = new Usuario();
	private ControleSalasBean controleSalas = new ControleSalasBean();
	private Pessoa pessoa = new Pessoa();
	
	@Given("^Eu entro no sistema como Administrador$")
	public void euEntroNoSistemaComoAdministrador() throws Throwable {
		pessoa.setNome("Administrador");
		pessoa.setSenha("123456");
		usuario.setPessoa(pessoa);
		usuario.entrar();
		controleSalas.setUsuario(usuario);
	}

	@When("^Eu clico em todas as salas$")
	public void euClicoEmTodasAsSalas() throws Throwable {

		//bean responsavel por pegar todas as salas
		controleSalas.selecionaSalas();
		
	}

	@Then("^Eu recebo uma lista com todas as salas$")
	public void euReceboUmaListaComTodasAsSalas() throws Throwable {
		
	    List<Sala> listaSalas =  new ArrayList<Sala>();
	    listaSalas = controleSalas.getSalas();
	    
	    //Verifico se a quantidade de salas eh a mesma quantidade de salas que posso editar
	    assertThat(listaSalas.size(), is(8));
	}
	
}
