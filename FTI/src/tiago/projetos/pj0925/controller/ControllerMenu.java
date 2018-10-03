package tiago.projetos.pj0925.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tiago.projetos.pj0925.model.Aluno;
import tiago.projetos.pj0925.model.Funcionário;
import tiago.projetos.pj0925.model.Professor;

public class ControllerMenu {
	private static ArrayList<Professor> arrayProfessor;
	private static ArrayList<Funcionário> arrayFuncionário;
	private static ArrayList<Aluno> arrayAluno;
	public static SimpleDateFormat sdf; 
	
	public ControllerMenu() {
		arrayProfessor = new ArrayList<Professor>();
		arrayFuncionário = new ArrayList<Funcionário>();
		arrayAluno = new ArrayList<Aluno>();
		sdf = new SimpleDateFormat("dd/mm/yyyy");
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
		if (arrayAluno.get(i).getSexo() == 'M'){
			sexo = "Masculino";
		} else {
			sexo = "Feminino";
		}
		return "Matrícula: " + arrayAluno.get(i).getMatricula() + ";\nNome: " + arrayAluno.get(i).getNome() + ";\nCPF: " + arrayAluno.get(i).getCpf() + ";\nData de Nascimento: " + 
				ControllerMenu.sdf.format(arrayAluno.get(i).getDataNascimento()) + ";\nEndereço: " + arrayAluno.get(i).getEndereço() + ";\nSexo: " + sexo + ";\nCurso: " + arrayAluno.get(i).getCurso() + 
				";\nTelefone: " + arrayAluno.get(i).getTelefone() + ";\ne-mail: " + arrayAluno.get(i).geteMail() + ";";
	}

	public String setTextPaneFuncionario(int i) {
		String sexo = "";
		if (arrayFuncionário.get(i).getSexo() == 'M'){
			sexo = "Masculino";
		} else {
			sexo = "Feminino";
		}
		return "Código do cadastro: " + arrayFuncionário.get(i).getCodCadastro() + ";\nNome: " + arrayFuncionário.get(i).getNome() + ";\nCPF: " + 
		arrayFuncionário.get(i).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(arrayFuncionário.get(i).getDataNascimento()) + ";\nEndereço: " + arrayFuncionário.get(i).getEndereço() +
		";\nSexo: " + sexo + ";\nCargo: " + arrayFuncionário.get(i).getCargo() + ";\nSalário: R$" + arrayFuncionário.get(i).getSalario() + ";\nValor do Vale Alimentação: R$" + 
		arrayFuncionário.get(i).getValeAlimentação() + ";\nValor do Vale Refeição: R$" + arrayFuncionário.get(i).getValeRefeição() + ";\nValor do Vale Transporte: R$" + 
		arrayFuncionário.get(i).getValeTransporte() + ";\nTelefone: " + arrayFuncionário.get(i).getTelefone() + ";\ne-mail: " + arrayFuncionário.get(i).geteMail() + 
		";\nNúmero de filhos: " + arrayFuncionário.get(i).getFilhos() + ";";
	}

	public String setTextPaneProfessor(int i) {
		String sexo = "";
		if (arrayProfessor.get(i).getSexo() == 'M'){
			sexo = "Masculino";
		} else {
			sexo = "Feminino";
		}
		return "Código do cadastro: " + arrayProfessor.get(i).getCodCadastro() + ";\nNome: " + arrayProfessor.get(i).getNome() + ";\nCPF: " + 
		arrayProfessor.get(i).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(arrayProfessor.get(i).getDataNascimento()) + ";\nEndereço: " + arrayProfessor.get(i).getEndereço() +
		";\nSexo: " + sexo + ";\nCargo: " + arrayProfessor.get(i).getCargo() + ";\nDisciplina: " + arrayProfessor.get(i).getDisciplina() + ";\nSalário: R$" + arrayProfessor.get(i).getSalario() + ";\nValor do Vale Alimentação: R$" + 
		arrayProfessor.get(i).getValeAlimentação() + ";\nValor do Vale Refeição: R$" + arrayProfessor.get(i).getValeRefeição() + ";\nValor do Vale Transporte: R$" + 
		arrayProfessor.get(i).getValeTransporte() + ";\nTelefone: " + arrayProfessor.get(i).getTelefone() + ";\ne-mail: " + arrayProfessor.get(i).geteMail() + 
		";\nNúmero de filhos: " + arrayProfessor.get(i).getFilhos() + ";";
	}
}
