package cucumber.teste;

import integragenda.modelo.Belly;
import model.ControleMinhasSalasBean;
import model.Pessoa;
import model.Sala;
import model.Usuario;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsNot;


public class UsuarioEditaSuasSalas {
	
	private Usuario usuario = new Usuario();
	private Pessoa pessoa = new Pessoa();
	
	private ControleMinhasSalasBean controle = new ControleMinhasSalasBean();
	
	@Given("^Eu desejo editar minhas salas$")
	public void euDesejoEditarMinhasSalas() throws Throwable {
		pessoa.setNome("Gustavo");
		pessoa.setSenha("123456");
		usuario.setPessoa(pessoa);
		usuario.entrar();
		
		controle.setUsuario(usuario);
		
	    List<Sala> listaSalas = new ArrayList<Sala>();
	    controle.selecionaSalas();
	    listaSalas = controle.getSalas();
	    for(Sala sala : listaSalas)
	    {
	    	assertThat(sala.getDescricao().equals("SALA TESTE"), is(false));
	    }
	    
	}
	
	@When("^Clico em minhas salas$")
	public void clicoEmMinhasSalas() throws Throwable {
		controle.selecionaSalas();
	}

	@Then("^Eu recebo uma lista com minhas salas e edito a descricao de todas para \"([^\"]*)\"$")
	public void euReceboUmaListaComMinhasSalasEEditoADescricaoDeTodasPara(String arg1) throws Throwable {
	    
		List<Sala> listaSalas = new ArrayList<Sala>();
	    listaSalas = controle.getSalas();
	    for(Sala sala : listaSalas)
	    {
	    	sala.setDescricao(arg1);
	    	sala.salvar();
	    }
	    
		List<Sala> listaSalasEditadas = new ArrayList<Sala>();
		controle.selecionaSalas();
		listaSalasEditadas = controle.getSalas();
	    
	    for(Sala sala : listaSalasEditadas)
	    {
	    	assertThat(sala.getDescricao(), is(arg1));
	    }
	    
	}
	
}
