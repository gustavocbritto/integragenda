package cucumber.teste;

import integragenda.modelo.Belly;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class LoginUsuario {
	
	@Given("^Eu entro com meu usuario \"([^\"]*)\" e senha \"([^\"]*)\"$")
	public void euEntroComMeuUsuarioESenha(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^Eu clico em entrar$")
	public void euClicoEmEntrar() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Meu usuario e logado no sistema$")
	public void meuUsuarioELogadoNoSistema() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
