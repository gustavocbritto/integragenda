package cucumber.teste;

import integragenda.modelo.Belly;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UsuarioQualificaSala {
	
	@Given("^Eu desejo avaliar uma sala que eu ja tenha alugado$")
	public void euDesejoAvaliarUmaSalaQueEuJaTenhaAlugado() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^Eu seleciono a pontuacao (\\d+) para a sala$")
	public void euSelecionoAPontuacaoParaASala(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^A pontuacao da sala e atualizada com meu voto$")
	public void aPontuacaoDaSalaEAtualizadaComMeuVoto() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^Eu desejo avaliar uma sala que eu nao tenha alugado$")
	public void euDesejoAvaliarUmaSalaQueEuNaoTenhaAlugado() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^Seleciono a pontuacao (\\d+) para a sala$")
	public void selecionoAPontuacaoParaASala(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^A pontuacao da sala nao e atualizada com meu voto$")
	public void aPontuacaoDaSalaNaoEAtualizadaComMeuVoto() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
