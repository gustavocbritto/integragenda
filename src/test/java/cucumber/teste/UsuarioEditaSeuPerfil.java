package cucumber.teste;

import integragenda.modelo.Belly;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UsuarioEditaSeuPerfil {
	
	@Given("^Eu tenho cadastrado como nome \"([^\"]*)\" e desejo alterar para \"([^\"]*)\"$")
	public void euTenhoCadastradoComoNomeEDesejoAlterarPara(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Eu clico em salvar$")
	public void euClicoEmSalvar() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Meu nome e alterado para \"([^\"]*)\"$")
	public void meuNomeEAlteradoPara(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
