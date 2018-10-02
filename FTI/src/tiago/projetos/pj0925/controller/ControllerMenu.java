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
	
	public ControllerMenu() {
		arrayProfessor = new ArrayList<Professor>();
		arrayFuncionário = new ArrayList<Funcionário>();
		arrayAluno = new ArrayList<Aluno>();
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
	
	public String setTextTabela(int i){
		String sexo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		if (arrayAluno.get(i).getSexo() == 'M'){
			sexo = "Masculino";
		} else {
			sexo = "Feminino";
		}
		return "Matrícula: " + arrayAluno.get(i).getMatricula() + "\nNome: " + arrayAluno.get(i).getNome() + ";\nCPF: " + arrayAluno.get(i).getCpf() + ";\nData de Nascimento: " + 
				sdf.format(arrayAluno.get(i).getDataNascimento()) + ";\nEndereço: " + arrayAluno.get(i).getEndereço() + ";\nSexo: " + sexo + ";\nCurso: " + arrayAluno.get(i).getCurso() + 
				";\nTelefone: " + arrayAluno.get(i).getTelefone() + ";\ne-mail: " + arrayAluno.get(i).geteMail() + ";";
	}
}
