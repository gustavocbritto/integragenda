package cucumber.teste;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Pessoa;
import model.Sala;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;

import Controller.ControleSalasBean;
import Controller.Usuario;


public class UsuarioQualificaSala {
	
	private Usuario usuario = new Usuario();
	private ControleSalasBean controleSalas = new ControleSalasBean();
	private Pessoa pessoa = new Pessoa();
	
	@Given("^Eu desejo avaliar uma sala que eu ja tenha alugado$")
	public void euDesejoAvaliarUmaSalaQueEuJaTenhaAlugado() throws Throwable {
		pessoa.setNome("Administrador");
		pessoa.setSenha("123456");
		usuario.setPessoa(pessoa);
		usuario.entrar();
		controleSalas.setUsuario(usuario);
	}
	
	@When("^Eu seleciono a pontuacao (\\d+) para a sala$")
	public void euSelecionoAPontuacaoParaASala(int arg1) throws Throwable {
		//bean responsavel por pegar todas as salas
		controleSalas.selecionaSalas();
	}

	@Then("^A pontuacao da sala e atualizada com meu voto$")
	public void aPontuacaoDaSalaEAtualizadaComMeuVoto() throws Throwable {
		
	    List<Sala> listaSalas =  new ArrayList<Sala>();
	    listaSalas = controleSalas.getSalas();
	    for(Sala sala: listaSalas)
	    {
	    	sala.setEstrela(5);
	    }
	    
		//bean responsavel por pegar todas as salas
		controleSalas.selecionaSalas();
		List<Sala> listaSalasAtualizada =  new ArrayList<Sala>();
		listaSalasAtualizada = controleSalas.getSalas();
		
		//Verifico se a quantidade de estrela que esta salvo eh a mesma que tinha coloado
	    for(Sala sala: listaSalasAtualizada)
	    {
	    	assertThat(sala.getEstrela(), is(5));
	    }
	    
	    
	    
	}
	
}
