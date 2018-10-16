package tiago.projetos.pj0925.controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import tiago.projetos.pj0925.model.Aluno;
import tiago.projetos.pj0925.model.Funcionario;
import tiago.projetos.pj0925.model.Pessoa;
import tiago.projetos.pj0925.model.Professor;
import tiago.projetos.pj0925.view.CadastroAluno;
import tiago.projetos.pj0925.view.CadastroFuncionario;

public class ControllerMenu {
	private static ArrayList<Professor> arrayProfessor;
	private static ArrayList<Funcionario> arrayFuncionário;
	private static ArrayList<Aluno> arrayAluno;
	public static SimpleDateFormat sdf; 
	public static NumberFormat nF;
	
	public ControllerMenu() {
		arrayProfessor = new ArrayList<Professor>();
		arrayFuncionário = new ArrayList<Funcionario>();
		arrayAluno = new ArrayList<Aluno>();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		nF = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	}

	public static ArrayList<Professor> getArrayProfessor() {
		return ControllerMenu.arrayProfessor;
	}

	public static void setArrayProfessor(ArrayList<Professor> arrayProfessor) {
		ControllerMenu.arrayProfessor = arrayProfessor;
	}

	public static ArrayList<Funcionario> getArrayFuncionário() {
		return ControllerMenu.arrayFuncionário;
	}

	public static void setArrayFuncionário(ArrayList<Funcionario> arrayFuncionário) {
		ControllerMenu.arrayFuncionário = arrayFuncionário;
	}

	public static ArrayList<Aluno> getArrayAluno() {
		return ControllerMenu.arrayAluno;
	}

	public static void setArrayAluno(ArrayList<Aluno> arrayAluno) {
		ControllerMenu.arrayAluno = arrayAluno;
	}
	
	public String setTextPaneAluno(int i, CadastroAluno cA){
		String sexo = "";
		try {
			if (cA.getCA().getArrayDisplay().get(i).getSexo() == 'M'){
				sexo = "Masculino";
			} else {
				sexo = "Feminino";
			}
			sexo = "Matrícula: " + cA.getCA().getArrayDisplay().get(i).getMatricula() + ";\nNome: " +cA.getCA().getArrayDisplay().get(i).getNome() + ";\nCPF: " + cA.getCA().getArrayDisplay().get(i).getCpf() + ";\nData de Nascimento: " + 
					ControllerMenu.sdf.format(cA.getCA().getArrayDisplay().get(i).getDataNascimento()) + ";\nEndereço: " + cA.getCA().getArrayDisplay().get(i).getEndereço() + ";\nSexo: " + sexo + ";\nCurso: " + cA.getCA().getArrayDisplay().get(i).getCurso() + 
					";\nTelefone: " + cA.getCA().getArrayDisplay().get(i).getTelefone() + ";\ne-mail: " + cA.getCA().getArrayDisplay().get(i).geteMail() + ";";
		} catch (Exception xcp) {
		}
		return sexo;
	}

	public String setTextPaneFuncionario(int i, CadastroFuncionario cF) {
		String sexo = "";
		try {
		if (cF.getCF().getArrayDisplay().get(i).getSexo() == 'M'){
			sexo = "Masculino";
		} else {	
			sexo = "Feminino";
		}
		sexo = "Código do cadastro: " + cF.getCF().getArrayDisplay().get(i).getCodCadastro() + ";\nNome: " + cF.getCF().getArrayDisplay().get(i).getNome() + ";\nCPF: " + 
				cF.getCF().getArrayDisplay().get(i).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(cF.getCF().getArrayDisplay().get(i).getDataNascimento()) + ";\nEndereço: " + cF.getCF().getArrayDisplay().get(i).getEndereço() +
		";\nSexo: " + sexo + ";\nCargo: " + cF.getCF().getArrayDisplay().get(i).getCargo() + ";\nSalário: " + nF.format(cF.getCF().getArrayDisplay().get(i).getSalario()) + ";\nValor do Vale Alimentação: " + 
		nF.format(cF.getCF().getArrayDisplay().get(i).getValeAlimentação()) + ";\nValor do Vale Refeição: " + nF.format(cF.getCF().getArrayDisplay().get(i).getValeRefeição()) + ";\nValor do Vale Transporte: " + 
		nF.format(cF.getCF().getArrayDisplay().get(i).getValeTransporte()) + ";\nTelefone: " + cF.getCF().getArrayDisplay().get(i).getTelefone() + ";\ne-mail: " + cF.getCF().getArrayDisplay().get(i).geteMail() + 
		";\nNúmero de filhos: " + cF.getCF().getArrayDisplay().get(i).getFilhos() + ";";
		if (cF.getCF().getArrayDisplay().get(i).getFilhos() != 0) {
			sexo += "\nFilhos:\n";
			for (Pessoa p : cF.getCF().getArrayDisplay().get(i).getCadastroFilhos()) {
				sexo += p.getNome() + " - " + ControllerMenu.sdf.format(p.getDataNascimento()) + "\n";
			}
		}
		} catch (Exception xcp) {
		}
		return sexo;
	}

	public String setTextPaneProfessor(int i, CadastroFuncionario cF) {
		String sexo = "";
		try {
		if (cF.getCP().getArrayDisplay().get(i).getSexo() == 'M'){
			sexo = "Masculino";
		} else {
			sexo = "Feminino";
		}
		sexo = "Código do cadastro: " + cF.getCP().getArrayDisplay().get(i).getCodCadastro() + ";\nNome: " + cF.getCP().getArrayDisplay().get(i).getNome() + ";\nCPF: " + 
				cF.getCP().getArrayDisplay().get(i).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(cF.getCP().getArrayDisplay().get(i).getDataNascimento()) + ";\nEndereço: " + cF.getCP().getArrayDisplay().get(i).getEndereço() +
		";\nSexo: " + sexo + ";\nCargo: " + cF.getCP().getArrayDisplay().get(i).getCargo() + ";\nSalário: " + nF.format(cF.getCP().getArrayDisplay().get(i).getSalario()) + ";\nValor do Vale Alimentação: " + 
		nF.format(cF.getCP().getArrayDisplay().get(i).getValeAlimentação()) + ";\nValor do Vale Refeição: " + nF.format(cF.getCP().getArrayDisplay().get(i).getValeRefeição()) + ";\nValor do Vale Transporte: " + 
		nF.format(cF.getCP().getArrayDisplay().get(i).getValeTransporte()) + ";\nTelefone: " + cF.getCP().getArrayDisplay().get(i).getTelefone() + ";\ne-mail: " + cF.getCP().getArrayDisplay().get(i).geteMail() + 
		";\nNúmero de filhos: " + cF.getCP().getArrayDisplay().get(i).getFilhos() + ";";
		if (cF.getCP().getArrayDisplay().get(i).getFilhos() != 0) {
			sexo += "\nFilhos:\n";
			for (Pessoa p : cF.getCP().getArrayDisplay().get(i).getCadastroFilhos()) {
				sexo += p.getNome() + " - " + ControllerMenu.sdf.format(p.getDataNascimento()) + "\n";
			}
		}
		} catch (Exception xcp) {
		}
		return sexo;
	}
}
