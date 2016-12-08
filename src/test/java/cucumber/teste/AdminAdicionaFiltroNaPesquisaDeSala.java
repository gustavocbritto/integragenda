package cucumber.teste;

import model.Categoria;
import model.ControleSalasBean;
import model.Sala;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;


public class AdminAdicionaFiltroNaPesquisaDeSala {

	private ControleSalasBean controle = new ControleSalasBean();
	private List<Sala> listaSalas = new ArrayList<Sala>();
	private String nomeCategoria = "";
	
	@Given("^Eu adiciono o filtro de categoria \"([^\"]*)\"$")
	public void euAdicionoOFiltroDeCategoria(String arg1) throws Throwable {
		//Atribuo o nome da categoria a uma variavel auxliar para comparar no ultimo teste
		nomeCategoria = arg1;
		
		//Inicializo o controle
		controle.init();
		
		for(Categoria categoria : controle.getCategorias())
		{
			if(categoria.getDescricao().equals(nomeCategoria))
				controle.setCategoriaFiltro(categoria);
		}
		
		
	}

	@When("^Eu clico em pesquisar$")
	public void euClicoEmPesquisar() throws Throwable {
		//Faco o filtro
		controle.selecionaSalas();
	}

	@Then("^Eu recebo a lista de salas com aquele filtro$")
	public void euReceboAListaDeSalasComAqueleFiltro() throws Throwable {
		
		//Pego as salas ja filtradas e verifico se a categoria delas batem com o filtro
	    listaSalas = controle.getSalas();
	    for(Sala sala : listaSalas)
	    {
	    	assertThat(sala.getCategoria().getDescricao(), is(nomeCategoria));
	    }
	    
	}
	
}
