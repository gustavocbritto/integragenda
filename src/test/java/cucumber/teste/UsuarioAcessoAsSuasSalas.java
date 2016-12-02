package cucumber.teste;

import integragenda.modelo.Belly;
import model.ControleMinhasSalasBean;
import model.Pessoa;
import model.Usuario;
import model.Sala;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;


public class UsuarioAcessoAsSuasSalas {
	private Usuario usuario = new Usuario();
	private ControleMinhasSalasBean controle = new ControleMinhasSalasBean();
	private Pessoa pessoa = new Pessoa();
	@Given("^Eu estou logado no sistema com algumas salas cadastradas$")
	public void euEstouLogadoNoSistemaComAlgumasSalasCadastradas() throws Throwable {
		pessoa.setNome("Gustavo");
		pessoa.setSenha("123456");
		usuario.setPessoa(pessoa);
		usuario.entrar();
		controle.setUsuario(usuario);
	}

	@When("^Eu clico em minhas Salas$")
	public void euClicoEmMinhasSalas() throws Throwable {
		controle.selecionaSalas();
	}

	@Then("^Eu recebo uma lista com minhas salas$")
	public void euReceboUmaListaComMinhasSalas() throws Throwable {
	    List<Sala> listaSalas = new ArrayList<Sala>();
	    listaSalas = controle.getSalas();
	    for(Sala sala : listaSalas)
	    {
	    	assertThat(sala.getAdministrador().getPessoa().getNome(), is(usuario.getPessoa().getNome()));
	    }
	}
	
}
