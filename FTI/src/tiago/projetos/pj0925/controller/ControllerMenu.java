package tiago.projetos.pj0925.controller;

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
	
	public void geraListaAluno(DefaultTableModel model) {
		String nome2 = "Tiago de Morais França";
		for (int i = 0; i < 40; i++) {
			nome2 = nome2 + i;
			arrayAluno.add(new Aluno(nome2, "07378278904", "123456789", new Date(), "Rua", 'M', "Java WEB", "43999565338", "tiagomfr@gmail.com"));
		}
		
		for (Aluno aluno : arrayAluno) {
			String matrícula = aluno.getMatricula().toString();
			String cpf = aluno.getCpf();
			String nome = aluno.getNome();
			String curso = aluno.getCurso();
			String email = aluno.geteMail();
			Object[] linha = {matrícula, cpf, nome, curso, email};
			model.addRow(linha);
		}
	}
	
	public String setTextTabela(int i){
		return "Matrícula: " + arrayAluno.get(i).getMatricula() + "\nNome: " + arrayAluno.get(i).getNome() + ";";
	}
}
