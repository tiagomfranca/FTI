package tiago.projetos.pj0925.model;

import java.util.Date;

public class Pessoa {
	private String nome, cpf;
	private Date dataNascimento;
	private char sexo;
	
	public Pessoa(){
	}
	
	public Pessoa(String nome, Date data) {
		this.nome = nome;
		this.dataNascimento = data;
	}
	
	public Pessoa(String nome, String cpf, Date data, char sexo){
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = data;
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
}
