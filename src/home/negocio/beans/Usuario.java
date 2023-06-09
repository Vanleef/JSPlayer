package home.negocio.beans;

import java.io.Serializable;

import home.dados.IRepositorioPlaylist;
import home.dados.RepositorioPlaylistArray;

public class Usuario implements Serializable{

	
	private String nome;
	private String email;
	private String cidade;
	private Object estado;
	private Object sexo;
	private Object idade;
	private IRepositorioPlaylist repoP;

	public Usuario(String nome, String email,String cidade, Object estado, Object sexo, Object idade) {

		this.nome = nome;
		this.email = email;
		this.cidade = cidade;
		this.estado = estado;
		this.sexo = sexo;
		this.idade = idade;
		this.repoP = new RepositorioPlaylistArray(100);

	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Object getSexo() {
		return sexo;
	}

	public Object getIdade() {
		return idade;
	}
	
	public String getCidade() {
		return cidade;
	}

	public Object getEstado() {
		return estado;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(Object estado) {
		this.estado = estado;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSexo(Object sexo) {
		this.sexo = sexo;
	}

	public void setIdade(Object idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + "\nEmail: " + email + "\nLocalizacao: " + cidade + "," + estado + "\nSexo: " + sexo
				+ "\nIdade: " + idade;
	}

	public IRepositorioPlaylist getRepoP() {
		return repoP;
	}
}
