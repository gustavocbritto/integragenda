package cucumber.teste;

import integragenda.modelo.Belly;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class LoginAdmin {

	@Given("^Eu entro com o nome \"([^\"]*)\" e senha \"([^\"]*)\"$")
	public void euEntroComONomeESenha(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^Clico em entrar$")
	public void clicoEmEntrar() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^Eu entro no sistema como administrador$")
	public void euEntroNoSistemaComoAdministrador() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
