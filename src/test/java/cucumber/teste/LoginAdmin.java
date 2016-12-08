package cucumber.teste;

import model.Pessoa;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import Controller.Usuario;


public class LoginAdmin {
	private Pessoa pessoa = new Pessoa();
	private Usuario usuario = new Usuario();

	@Given("^Eu entro com o nome \"([^\"]*)\" e senha \"([^\"]*)\"$")
	public void euEntroComONomeESenha(String arg1, String arg2) throws Throwable {
		usuario.setPessoa(pessoa);
		pessoa.setNome(arg1);
		pessoa.setSenha(arg2);
	}
	
	@When("^Clico em entrar$")
	public void clicoEmEntrar() throws Throwable {
		usuario.entrar();
	}
	
	@Then("^Eu entro no sistema como administrador$")
	public void euEntroNoSistemaComoAdministrador() throws Throwable {
		assertThat(usuario.getNomeAtual(), is("Gustavo"));
	}
	
}
