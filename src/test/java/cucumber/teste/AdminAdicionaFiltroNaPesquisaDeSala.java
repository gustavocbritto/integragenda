package cucumber.teste;

import integragenda.modelo.Belly;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AdminAdicionaFiltroNaPesquisaDeSala {

	@Given("^Eu adiciono o filtro de categoria \"([^\"]*)\"$")
	public void euAdicionoOFiltroDeCategoria(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Eu clico em pesquisar$")
	public void euClicoEmPesquisar() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Eu recebo a lista de salas com aquele filtro$")
	public void euReceboAListaDeSalasComAqueleFiltro() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
