package cucumber.teste;

import model.Pessoa;
import model.Usuario;
import persistence.PessoaDAO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CadastroUsuario {
	private Pessoa pessoa = new Pessoa();
	private Usuario usuario = new Usuario();
	
	@Given("^I am not registered yet and my username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void iAmNotRegisteredYetAndMyUsernameAndPassword(String arg1, String arg2) throws Throwable {
		
		pessoa.setNome(arg1);
		pessoa.setSenha(arg2);
		pessoa.setSobrenome("Legal");
		pessoa.setEmail("paulo@gmail.com");
		pessoa.setTelefone("423");
		pessoa.setSenha("M");
		pessoa.setConfirmarsenha(arg2);
		usuario.setPessoa(pessoa);
	}

	@When("^I click in register$")
	public void iClickInRegister() throws Throwable {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.gravar(usuario);
		//assertThat(usuario.cadastrar(), is(true));
	}

	@Then("^my user are regitered$")
	public void myUserAreRegitered() throws Throwable {
		usuario.entrar();
		assertThat(usuario.getNomeAtual(), is("Paulo"));
	}
	
}
