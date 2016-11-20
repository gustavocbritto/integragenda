package backingbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {
	private String nome;
	private String email; //comentario pro git
	private String senha;
	private String confirmaSenha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	public String novo()
	{
		return "usuario";
	}
	public String salvar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if(! this.senha.equalsIgnoreCase(this.confirmaSenha))
		{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha confirmada incorretamente",""));
			return "usuario";
		}
		//salva o usu�rio
		return "mostraUsuario";
	}

	
}