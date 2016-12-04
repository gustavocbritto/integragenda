package cucumber.teste;

import model.ControleSalasBean;
import model.Sala;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UsuarioFiltaSalasPorData {
	
	private Date dataInicio;
	private Date dataFim;
	private ControleSalasBean controle = new ControleSalasBean();
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private List<Sala> listaSalas = new ArrayList<Sala>();
	
	@Given("^Eu desejo filtrar as salas com a data livre de \"([^\"]*)\" a \"([^\"]*)\"$")
	public void euDesejoFiltrarAsSalasComADataLivreDeA(String arg1, String arg2) throws Throwable {
		
		//provar que tem mais de 1 sala
		controle.selecionaSalas();
		listaSalas = controle.getSalas();
		assertThat(listaSalas.size(), is(8));
		//Informo o filtro para o controlador
		dataInicio = formatter.parse(arg1);
		dataFim = formatter.parse(arg2);
		controle.setDt_inicial(dataInicio);
		controle.setDt_final(dataFim);
	}

	@When("^Clico em pesquisar$")
	public void clicoEmPesquisar() throws Throwable {
		//Faco o filtro
		controle.selecionaSalas();
	}
	
	@Then("^Eu recebo uma lista com todas as salas disponiveis no intervalo desejado$")
	public void euReceboUmaListaComTodasAsSalasDisponiveisNoIntervaloDesejado() throws Throwable {

		//Pego as salas ja filtradas e verifico que elas estao disponiveis naquela dala
	    listaSalas = controle.getSalas();
	    assertThat(listaSalas.size(), is(7));
	    for(Sala sala : listaSalas)
	    {
	    	assertThat(sala.getDisponivel(dataInicio, dataFim), is(true));
	    }
	    
	}
	
}
