package tiago.projetos.pj0925.model;

import java.util.ArrayList;
import java.util.Date;

public class Professor extends Funcionario {
	private String disciplina;
	
	public Professor(){}
	
	public Professor(String codCadastro, String nome, String cpf, Date dataNascimento, String endereço, char sexo, String cargo, String disciplina,
			double salario, double valeAlimentação, double valeTransporte, double valeRefeição, int filhos, 
			ArrayList<Pessoa> cadastroFilhos, String telefone, String eMail){
		super(codCadastro, nome, cpf, dataNascimento, endereço, sexo, cargo, salario, valeAlimentação, valeTransporte, valeRefeição, filhos,
				cadastroFilhos, telefone, eMail);
		this.disciplina = disciplina;
	}

	public String getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
}
