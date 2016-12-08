package cucumber.teste;

import model.Pessoa;
import model.Sala;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;

import Controller.ControleSalasBean;
import Controller.Usuario;


public class LocatarioTemAcessoAInfoLocador {

	private Usuario usuario = new Usuario();
	private ControleSalasBean controleSalas = new ControleSalasBean();
	private Pessoa pessoa = new Pessoa();
	
	@Given("^Eu estou olhando a sala de uma outra pessoa$")
	public void euEstouOlhandoASalaDeUmaOutraPessoa() throws Throwable {
		pessoa.setNome("Gustavo");
		pessoa.setSenha("123456");
		usuario.setPessoa(pessoa);
		usuario.entrar();
		controleSalas.setUsuario(usuario);
	}

	@When("^Eu clico em informacoes de locador$")
	public void euClicoEmInformacoesDeLocador() throws Throwable {
		//bean responsavel por pegar todas as salas
		controleSalas.selecionaSalas();
	}

	@Then("^Eu recebo as informacoes sobre o locador$")
	public void euReceboAsInformacoesSobreOLocador() throws Throwable {
		
	    List<Sala> listaSalas =  new ArrayList<Sala>();
	    listaSalas = controleSalas.getSalas();
	    
	    //Aqui eu verifico se atravez das salas eu tenho as informacoes referentes ao locador
	    for(Sala sala : listaSalas)
	    {
	    	assertThat(sala.getPessoa().getNome(), not(equalTo("")));
	    	assertThat(sala.getPessoa().getEmail(), not(equalTo("")));
	    	assertThat(sala.getPessoa().getTelefone(), not(equalTo("")));
	    }
	    
	}
	
}
