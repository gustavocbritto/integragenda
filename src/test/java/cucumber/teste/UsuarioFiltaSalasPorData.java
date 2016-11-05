package cucumber.teste;

import integragenda.modelo.Belly;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UsuarioFiltaSalasPorData {
	
	@Given("^Eu desejo filtrar as salas com a data livre de \"([^\"]*)\" a \"([^\"]*)\"$")
	public void euDesejoFiltrarAsSalasComADataLivreDeA(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Clico em pesquisar$")
	public void clicoEmPesquisar() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^Eu recebo uma lista com todas as salas disponiveis no intervalo desejado$")
	public void euReceboUmaListaComTodasAsSalasDisponiveisNoIntervaloDesejado() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
