package cucumber.teste;

import integragenda.modelo.Belly;
import model.ControleMinhasSalasBean;
import model.ControleSalasBean;
import model.Pessoa;
import model.Sala;
import model.Usuario;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;


public class AdminEditTodasAsSalas {
	
	private Usuario usuario = new Usuario();
	private ControleMinhasSalasBean controleMinhasSalas = new ControleMinhasSalasBean();
	private ControleSalasBean controleSalas = new ControleSalasBean();
	private Pessoa pessoa = new Pessoa();
	
	@Given("^Eu entrei no sistema como adminstrador$")
	public void euEntreiNoSistemaComoAdminstrador() throws Throwable {
		
		pessoa.setNome("Administrador");
		pessoa.setSenha("123456");
		usuario.setPessoa(pessoa);
		usuario.entrar();
		controleMinhasSalas.setUsuario(usuario);
		controleSalas.setUsuario(usuario);
		
	}

	@When("^Eu clico em minhas salas$")
	public void euClicoEmMinhasSalas() throws Throwable {
		
		controleMinhasSalas.selecionaSalas();
		controleSalas.selecionaSalas();
	}

	@Then("^Eu posso editar todas as salas listadas$")
	public void euPossoEditarTodasAsSalasListadas() throws Throwable {
		
	    List<Sala> listaMihasSalas = new ArrayList<Sala>();
	    List<Sala> listaSalas =  new ArrayList<Sala>();
	    listaSalas = controleSalas.getSalas();
	    listaMihasSalas = controleMinhasSalas.getSalas();
	    
	    assertThat(listaSalas, is(listaMihasSalas));
	    
	}
	
}
