package tiago.projetos.pj0925.model;

import java.util.Date;

public class Aluno extends Pessoa {
	private String matricula, endereço, curso, telefone, eMail; 
	
	
	public Aluno(){
		super();
	}
	
	public Aluno(String nome, String cpf, String matricula, Date dataNascimento, String endereço, char sexo, String curso,
			String telefone, String eMail){
		super(nome, cpf, dataNascimento, sexo);
		this.matricula = matricula;
		this.endereço = endereço;
		this.curso = curso;
		this.telefone = telefone;
		this.eMail = eMail;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
