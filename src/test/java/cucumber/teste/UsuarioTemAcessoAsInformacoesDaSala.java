package cucumber.teste;

import model.Pessoa;
import model.Sala;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;

import Controller.ControleSalasBean;
import Controller.Usuario;


public class UsuarioTemAcessoAsInformacoesDaSala {

	private Usuario usuario = new Usuario();
	private ControleSalasBean controleSalas = new ControleSalasBean();
	private Pessoa pessoa = new Pessoa();
	
	@Given("^Eu tenho a lista de salas$")
	public void euTenhoAListaDeSalas() throws Throwable {
		
		pessoa.setNome("Gustavo");
		pessoa.setSenha("123456");
		usuario.setPessoa(pessoa);
		usuario.entrar();
		controleSalas.setUsuario(usuario);
		
	}

	@When("^Eu clico em alguma sala$")
	public void euClicoEmAlgumaSala() throws Throwable {
		
		//bean responsavel por pegar todas as salas
		controleSalas.selecionaSalas();
		
	}

	@Then("^Eu recebo as informacoes sobre a sala selecionada$")
	public void euReceboAsInformacoesSobreASalaSelecionada() throws Throwable {
		
	    List<Sala> listaSalas =  new ArrayList<Sala>();
	    listaSalas = controleSalas.getSalas();
	    
	    //Aqui eu verifico se atravez das salas eu tenho as informacoes referentes a elas
	    for(Sala sala : listaSalas)
	    {
	    	assertThat(sala.getCategoria().getDescricao(), not(equalTo("")));
	    	assertThat(sala.getDescricao(), not(equalTo("")));
	    	assertThat(sala.getPreco() >= 0, is(true));
	    	assertThat(sala.getEstrela() >= 0, is(true));
	    	assertThat(sala.getImagens().size() > 0, is(true));
	    	assertThat(sala.getTamanhoMax() > 0, is(true));
	    	assertThat(sala.getUtensilios().size() >= 0, is(true));
	    	assertThat(sala.getLocalizacao().getCidade(), not(equalTo("")));
	    	assertThat(sala.getLocalizacao().getRua(), not(equalTo("")));
	    	assertThat(sala.getLocalizacao().getEstado(), not(equalTo("")));
	    	assertThat(sala.getLocalizacao().getPais(), not(equalTo("")));
	    	assertThat(sala.getLocalizacao().getNumero() > 0, is(true));
	    }
	    
	    
	}
	
}
