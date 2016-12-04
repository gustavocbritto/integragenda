package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="pessoa")
@RequestScoped
public class Pessoa implements Serializable{
	

	private static final long serialVersionUID = -462253599634007607L;
	private int id;
	private String nome, sobrenome, email, telefone, sexo, senha, confirmarsenha, tipo;
	
	public Pessoa(){
		
	}
	
	public Pessoa(String nome, String sobrenome, String email, String telefone, String sexo, String senha,
			String confirmarsenha) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.telefone = telefone;
		this.sexo = sexo;
		this.senha = senha;
		this.confirmarsenha = confirmarsenha;
	}
	
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getConfirmarsenha() {
		return confirmarsenha;
	}
	public void setConfirmarsenha(String confirmarsenha) {
		this.confirmarsenha = confirmarsenha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
