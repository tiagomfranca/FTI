package tiago.projetos.pj0925.controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import tiago.projetos.pj0925.model.Aluno;
import tiago.projetos.pj0925.model.Funcionário;
import tiago.projetos.pj0925.model.Pessoa;
import tiago.projetos.pj0925.model.Professor;

public class ControllerMenu {
	private static ArrayList<Professor> arrayProfessor;
	private static ArrayList<Funcionário> arrayFuncionário;
	private static ArrayList<Aluno> arrayAluno;
	public static SimpleDateFormat sdf; 
	public static NumberFormat nF;
	
	public ControllerMenu() {
		arrayProfessor = new ArrayList<Professor>();
		arrayFuncionário = new ArrayList<Funcionário>();
		arrayAluno = new ArrayList<Aluno>();
		sdf = new SimpleDateFormat("dd/mm/yyyy");
		nF = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	}

	public static ArrayList<Professor> getArrayProfessor() {
		return ControllerMenu.arrayProfessor;
	}

	public static void setArrayProfessor(ArrayList<Professor> arrayProfessor) {
		ControllerMenu.arrayProfessor = arrayProfessor;
	}

	public static ArrayList<Funcionário> getArrayFuncionário() {
		return ControllerMenu.arrayFuncionário;
	}

	public static void setArrayFuncionário(ArrayList<Funcionário> arrayFuncionário) {
		ControllerMenu.arrayFuncionário = arrayFuncionário;
	}

	public static ArrayList<Aluno> getArrayAluno() {
		return ControllerMenu.arrayAluno;
	}

	public static void setArrayAluno(ArrayList<Aluno> arrayAluno) {
		ControllerMenu.arrayAluno = arrayAluno;
	}
	
	public String setTextPaneAluno(int i){
		String sexo = "";
		try {
			if (arrayAluno.get(i).getSexo() == 'M'){
				sexo = "Masculino";
			} else {
				sexo = "Feminino";
			}
			sexo = "Matrícula: " + arrayAluno.get(i).getMatricula() + ";\nNome: " + arrayAluno.get(i).getNome() + ";\nCPF: " + arrayAluno.get(i).getCpf() + ";\nData de Nascimento: " + 
					ControllerMenu.sdf.format(arrayAluno.get(i).getDataNascimento()) + ";\nEndereço: " + arrayAluno.get(i).getEndereço() + ";\nSexo: " + sexo + ";\nCurso: " + arrayAluno.get(i).getCurso() + 
					";\nTelefone: " + arrayAluno.get(i).getTelefone() + ";\ne-mail: " + arrayAluno.get(i).geteMail() + ";";
		} catch (Exception xcp) {
		}
		return sexo;
	}

	public String setTextPaneFuncionario(int i) {
		String sexo = "";
		try {
		if (arrayFuncionário.get(i).getSexo() == 'M'){
			sexo = "Masculino";
		} else {
			sexo = "Feminino";
		}
		sexo = "Código do cadastro: " + arrayFuncionário.get(i).getCodCadastro() + ";\nNome: " + arrayFuncionário.get(i).getNome() + ";\nCPF: " + 
		arrayFuncionário.get(i).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(arrayFuncionário.get(i).getDataNascimento()) + ";\nEndereço: " + arrayFuncionário.get(i).getEndereço() +
		";\nSexo: " + sexo + ";\nCargo: " + arrayFuncionário.get(i).getCargo() + ";\nSalário: " + nF.format(arrayFuncionário.get(i).getSalario()) + ";\nValor do Vale Alimentação: " + 
		nF.format(arrayFuncionário.get(i).getValeAlimentação()) + ";\nValor do Vale Refeição: " + nF.format(arrayFuncionário.get(i).getValeRefeição()) + ";\nValor do Vale Transporte: " + 
		nF.format(arrayFuncionário.get(i).getValeTransporte()) + ";\nTelefone: " + arrayFuncionário.get(i).getTelefone() + ";\ne-mail: " + arrayFuncionário.get(i).geteMail() + 
		";\nNúmero de filhos: " + arrayFuncionário.get(i).getFilhos() + ";";
		if (arrayFuncionário.get(i).getFilhos() != 0) {
			sexo += "\nFilhos:\n";
			for (Pessoa p : arrayFuncionário.get(i).getCadastroFilhos()) {
				sexo += p.getNome() + " - " + ControllerMenu.sdf.format(p.getDataNascimento()) + "\n";
			}
		}
		} catch (Exception xcp) {
		}
		return sexo;
	}

	public String setTextPaneProfessor(int i) {
		String sexo = "";
		try {
			if (arrayProfessor.get(i).getSexo() == 'M'){
				sexo = "Masculino";
			} else {
				sexo = "Feminino";
			}
			sexo = "Código do cadastro: " + arrayProfessor.get(i).getCodCadastro() + ";\nNome: " + arrayProfessor.get(i).getNome() + ";\nCPF: " + 
			arrayProfessor.get(i).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(arrayProfessor.get(i).getDataNascimento()) + ";\nEndereço: " + arrayProfessor.get(i).getEndereço() +
			";\nSexo: " + sexo + ";\nCargo: " + arrayProfessor.get(i).getCargo() + ";\nDisciplina: " + arrayProfessor.get(i).getDisciplina() + ";\nSalário: " + nF.format(arrayProfessor.get(i).getSalario()) + ";\nValor do Vale Alimentação: " + 
			nF.format(arrayProfessor.get(i).getValeAlimentação()) + ";\nValor do Vale Refeição: " + nF.format(arrayProfessor.get(i).getValeRefeição()) + ";\nValor do Vale Transporte: " + 
			nF.format(arrayProfessor.get(i).getValeTransporte()) + ";\nTelefone: " + arrayProfessor.get(i).getTelefone() + ";\ne-mail: " + arrayProfessor.get(i).geteMail() + 
			";\nNúmero de filhos: " + arrayProfessor.get(i).getFilhos() + ";";
			if (arrayProfessor.get(i).getFilhos() != 0) {
				sexo += "\nFilhos:\n";
				for (Pessoa p : arrayProfessor.get(i).getCadastroFilhos()) {
					sexo += p.getNome() + " - " + ControllerMenu.sdf.format(p.getDataNascimento()) + "\n";
				}
			}
		} catch (Exception xcp) {
		}
		return sexo;
	}
}
