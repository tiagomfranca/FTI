package tiago.projetos.pj0925.model;

import java.util.Date;

public class Professor extends Pessoa {
	private String endereço, disciplina, telefone, eMail; 
	
	public Professor(){
		super("Tiago", "0", new Date(), 'M');
		this.endereço = "Rua";
	}
	
	public Professor(String nome, String cpf, Date dataNascimento, char sexo, String endereço, String disciplina,
			String telefone, String eMail){
		super(nome, cpf, dataNascimento, sexo);
		this.endereço = endereço;
		this.disciplina = disciplina;
		this.telefone = telefone;
		this.eMail = eMail;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
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
