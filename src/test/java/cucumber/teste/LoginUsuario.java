package cucumber.teste;

import model.Pessoa;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import Controller.Usuario;


public class LoginUsuario {
	private Pessoa pessoa = new Pessoa();
	private Usuario usuario = new Usuario();
	@Given("^Eu entro com meu usuario \"([^\"]*)\" e senha \"([^\"]*)\"$")
	public void euEntroComMeuUsuarioESenha(String arg1, String arg2) throws Throwable {
		usuario.setPessoa(pessoa);
		pessoa.setNome(arg1);
		pessoa.setSenha(arg2);
	}
	
	@When("^Eu clico em entrar$")
	public void euClicoEmEntrar() throws Throwable {
			usuario.entrar();
	}

	@Then("^Meu usuario e logado no sistema$")
	public void meuUsuarioELogadoNoSistema() throws Throwable {
			assertThat(usuario.getNomeAtual(), is("Gustavo"));
	}
	
}
