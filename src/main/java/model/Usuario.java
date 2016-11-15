package model;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="usuario")
@RequestScoped
public class Usuario implements Serializable{



	private static final long serialVersionUID = -7643404588198283069L;
	
	public int idUsuario;
	public String tipo;
	public Pessoa pessoa;

	public Usuario(){
		this.pessoa = new Pessoa();
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String salvar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if(! this.pessoa.senha.equalsIgnoreCase(this.pessoa.confirmarsenha))
		{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha confirmada incorretamente",""));
			return "usuario";
		}
		//salva o usuário
		return "mostraUsuario";
	}
	

}
