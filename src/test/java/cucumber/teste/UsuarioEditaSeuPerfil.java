package cucumber.teste;

import model.Pessoa;
import model.Usuario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UsuarioEditaSeuPerfil {
	private Usuario usuario = new Usuario();
	private Pessoa pessoa =  new Pessoa();
	private String telefoneAntigo, telefoneNovo;
	@Given("^Eu tenho cadastrado como telefone \"([^\"]*)\" e desejo alterar para \"([^\"]*)\"$")
	public void euTenhoCadastradoComoTelefoneEDesejoAlterarPara(String arg1, String arg2) throws Throwable {
		usuario.setPessoa(pessoa);
		pessoa.setNome("Gustavo");
		pessoa.setSenha("123456");
		usuario.entrar();
		telefoneAntigo = arg1;
		telefoneNovo = arg2;
	   // 332233333
	    //331133333
	}

	@When("^Eu clico em alterar$")
	public void euClicoEmAlterar() throws Throwable {
		assertThat(usuario.getPessoa().getTelefone(), is(telefoneAntigo));
		usuario.getPessoa().setTelefone(telefoneNovo);
		usuario.update();
	}

	@Then("^Meu telefone e alterado de \"([^\"]*)\" para \"([^\"]*)\"$")
	public void meuTelefoneEAlteradoDePara(String arg1, String arg2) throws Throwable {
		Usuario usuarioLocal = new Usuario();
		Pessoa pessoaLocal = new Pessoa();
		usuarioLocal.setPessoa(pessoa);
		pessoaLocal.setNome("Gustavo");
		pessoaLocal.setSenha("123456");
		usuarioLocal.entrar();
		assertThat(usuarioLocal.getPessoa().getTelefone(), is(telefoneNovo));
	}

	
}
