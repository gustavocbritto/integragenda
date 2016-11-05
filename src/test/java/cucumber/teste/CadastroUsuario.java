package cucumber.teste;

import integragenda.modelo.Belly;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CadastroUsuario {

	@Given("^I am not registered yet and my username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void iAmNotRegisteredYetAndMyUsernameAndPassword(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I click in register$")
	public void iClickInRegister() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^my user are regitered$")
	public void myUserAreRegitered() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
