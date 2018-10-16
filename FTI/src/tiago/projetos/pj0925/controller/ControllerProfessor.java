package tiago.projetos.pj0925.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tiago.projetos.pj0925.dao.ProfessorDAO;
import tiago.projetos.pj0925.model.Funcionario;
import tiago.projetos.pj0925.model.Pessoa;
import tiago.projetos.pj0925.model.Professor;
import tiago.projetos.pj0925.view.Menu;

public class ControllerProfessor {
	
	private DefaultTableModel modelTabelaProfessor;
	private ArrayList<Professor> arrayDisplay;
	private ProfessorDAO pDAO;
	
	public ControllerProfessor(){
		pDAO = new ProfessorDAO();
		modelTabelaProfessor = new DefaultTableModel(new Object[][] {}, new String[] {"Cadastro", "CPF", "Nome", "Disciplina"});
		iniciaTabela();
	}

	public ArrayList<Professor> getArrayDisplay(){
		return arrayDisplay;
	}
	
	public void iniciaTabela() {
		arrayDisplay = pDAO.consultarListaProfessor();
		modelTabelaProfessor.setRowCount(0);
		for (Professor p : arrayDisplay){
			String cadastro = p.getCodCadastro();
			String cpf = p.getCpf();
			String nome = p.getNome();
			String disciplina = p.getDisciplina();
			Object[] linha = {cadastro, cpf, nome, disciplina};
			modelTabelaProfessor.addRow(linha);
		}
	}

	public void cadastraProfessor(Professor p){
		modelTabelaProfessor.setRowCount(0);
		pDAO.cadastrarProfessor(p);
		
		iniciaTabela();
	}

	public void botaoCadastrar(String textNome, String textCpf, boolean botaoMale, boolean botaoFemale, String textData, String textEndereço,
			String boxCargo, String boxDisciplina, String textSalario, String textVA, String textVR, String textVT, String textTelefone, String textEMail,
			String textFilhos, ArrayList<JTextField> arrayTextFilhos, ArrayList<JTextField> arrayTextDatas) {
		ControllerUtil u = new ControllerUtil();
		ArrayList<Pessoa> arrayFilhos = new ArrayList<Pessoa>();
		double valorVA = 1;
		double valorVR = 1;
		double valorVT = 1;
		char sexo = '0';
		String erros = "";
		int numeros = 0;
					
		if(textNome.equals("") || textNome.equals("ex: José")) {
			erros = erros + "Campo Nome precisa estar preenchido;\n";
			numeros++;
		}
		if(textCpf.equals("") || textCpf.equals("ex: 12345678901")){
			erros = erros + "Campo CPF deve ser preenchido (apenas números);\n";
			numeros++;
		} else if(!u.validaCpf(textCpf)){
			erros = erros + "CPF inválido\n";
		}
		if(!botaoMale && !botaoFemale){
			erros = erros + "É necessário informar seu gênero;\n";
			numeros++;
		} else {
			if(botaoMale) {
				sexo = 'M';
			} else {
				sexo = 'F';
			}
		}
		if(!u.validaData(textData)){
			erros = erros + "Campo Data de Nascimento deve ser preenchido corretamente (dd/mm/aaaa);\n";
			numeros++;
		} 
		if(textEndereço.equals("") || textEndereço.equals("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303")){
			erros = erros + "Campo Endereço deve ser preenchido;\n";
			numeros++;
		}
		if (boxCargo.equals("Selecionar...")) {
			erros = erros + "É necessário informar o cargo;\n";
			numeros++;
		} else if (boxCargo.equals("Professor")) {
			if (boxDisciplina.equals("Selecionar...")){
				erros = erros + "É necessário informar a disciplina;\n";
				numeros++;
			}
		}
		if(textSalario.equals("ex: 400,00")) {
			erros = erros + "É necessário informar o salário;\n";
			numeros++;
		} else if(!u.validaDouble(textSalario)) {
			erros = erros + "Salário inválido (exemplo: 400,00);\n";
		}
		if(textVA.equals("ex: 400,00")) {
			valorVA = 0;
		} else if(!u.validaDouble(textVA)) {
			erros = erros + "Valor do vale alimentação inválido (exemplo: 400,00);\n";
		}
		if(textVR.equals("ex: 400,00")) {
			valorVR = 0;
		} else if(!u.validaDouble(textVR)) {
			erros = erros + "Valor do vale refeição inválido (exemplo: 400,00);\n";
		}
		if(textVT.equals("ex: 400,00")) {
			valorVT = 0;
		} else if(!u.validaDouble(textVT)) {
			erros = erros + "Valor do vale transporte inválido (exemplo: 400,00);\n";
		}
		if(textTelefone.equals("") || textTelefone.equals("ex: 43999565338")){
			erros = erros + "Campo Telefone deve ser preenchido;\n";
			numeros++;
		} else {
			if (!u.validaApenasNumeros(textTelefone)){
				erros = erros + "Campo Telefone deve ser preenchido corretamente (apenas números);\n";
				numeros++;
			}
		}
		if(textEMail.equals("") || textEMail.equals("ex: nome@site.com")){
			erros = erros + "Campo e-Mail deve ser preenchido;\n";
			numeros++;
		} else if (!u.validaEmail(textEMail)){
				erros = erros + "Campo e-mail deve ser preenchido corretamente (nome@site.com);\n";
				numeros++;
		}
		if(textFilhos.equals("ex: 2")) {
			erros = erros + "Campo Número de filhos deve ser preenchido;\n";
			numeros++;
		} else if (!textFilhos.equals("0")) {
			for (JTextField text : arrayTextFilhos) {
				if(text.getText().equals("ex: José")) {
					erros = erros + "Campo Filho " + (arrayTextFilhos.indexOf(text)+1) + " deve ser preenchido;\n";
					numeros++;
				}
			}
			for (JTextField text : arrayTextDatas) {
				if(text.getText().equals("dd/mm/aaaa")) {
					erros = erros + "Campo Data de nascimento do Filho " + (arrayTextDatas.indexOf(text)+1) + " deve ser preenchido;\n";
					numeros++;
				} else {
					if (!u.validaData(text.getText())) {
						erros = erros + "Campo data de nascimento do Filho " + (arrayTextDatas.indexOf(text)+1) + " deve ser preenchido corretamente (dd/mm/aaaa);\n";
						numeros++;
					}
				}
			}
		}
		if (numeros == 0){
			if(valorVA != 0) {
				valorVA = Double.parseDouble(textVA);
			}
			if(valorVR != 0) {
				valorVR = Double.parseDouble(textVR);
			}
			if(valorVT != 0) {
				valorVT = Double.parseDouble(textVT);
			}
			if(!textFilhos.equals("0")) {
				for(JTextField text : arrayTextFilhos) {
					Pessoa filho = new Pessoa(text.getText(), u.transformaData(arrayTextDatas.get(arrayTextFilhos.indexOf(text)).getText()));
					arrayFilhos.add(filho);
				}
			}
			Professor p = new Professor("", textNome, textCpf, u.transformaData(textData), textEndereço, sexo, boxCargo, boxDisciplina,
					Double.parseDouble(textSalario), valorVA, valorVT, valorVR, Integer.parseInt(textFilhos), arrayFilhos, textTelefone, textEMail);
			cadastraProfessor(p);
			Menu.adicionando = false;
			JOptionPane.showMessageDialog(null, "Cadastro de professor efetuado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			iniciaTabela();
		} else {
			Menu.adicionando = true;
			JOptionPane.showMessageDialog(null, erros, numeros + " erros encontrados:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public DefaultTableModel modelProfessor(){
		return this.modelTabelaProfessor;
	}
	
	public void removeProfessor(int i){
		pDAO.inativarProfessor(i);
		iniciaTabela();
	}
	
	public void botaoEditar(String textCadastro, String textNome, String textCpf, boolean botaoMale, boolean botaoFemale, String textData, String textEndereço,
			String boxCargo, String boxDisciplina, String textSalario, String textVA, String textVR, String textVT, String textTelefone, String textEMail,
			String textFilhos, ArrayList<JTextField> arrayTextFilhos, ArrayList<JTextField> arrayTextDatas){
		ControllerUtil u = new ControllerUtil();
		ArrayList<Pessoa> arrayFilhos = new ArrayList<Pessoa>();
		double valorVA = 1;
		double valorVR = 1;
		double valorVT = 1;
		char sexo = '0';
		String erros = "";
		int numeros = 0;
		
		if(textCadastro.equals("") || textCadastro.equals("ex: 123456789")){
			erros = erros + "Campo Código do cadastro deve ser preenchido;\n";
			numeros++;
		}			
		if(textNome.equals("") || textNome.equals("ex: José")) {
			erros = erros + "Campo Nome precisa estar preenchido;\n";
			numeros++;
		}
		if(textCpf.equals("") || textCpf.equals("ex: 12345678901")){
			erros = erros + "Campo CPF deve ser preenchido (apenas números);\n";
			numeros++;
		} else if(!u.validaCpf(textCpf)){
			erros = erros + "CPF inválido\n";
		}
		if(!botaoMale && !botaoFemale){
			erros = erros + "É necessário informar seu gênero;\n";
			numeros++;
		} else {
			if(botaoMale) {
				sexo = 'M';
			} else {
				sexo = 'F';
			}
		}
		if(!u.validaData(textData)){
			erros = erros + "Campo Data de Nascimento deve ser preenchido corretamente (dd/mm/aaaa);\n";
			numeros++;
		} 
		if(textEndereço.equals("") || textEndereço.equals("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303")){
			erros = erros + "Campo Endereço deve ser preenchido;\n";
			numeros++;
		}
		if (boxCargo.equals("Selecione...")) {
			erros = erros + "É necessário informar o cargo;\n";
			numeros++;
		} else if (boxCargo.equals("Professor")) {
			if (boxDisciplina.equals("Selecionar...")){
				erros = erros + "É necessário informar a disciplina;\n";
				numeros++;
			}
		}
		if(textSalario.equals("ex: 400,00")) {
			erros = erros + "É necessário informar o salário;\n";
			numeros++;
		} else if(!u.validaDouble(textSalario)) {
			erros = erros + "Salário inválido (exemplo: 400,00);\n";
		}
		if(textVA.equals("ex: 400,00")) {
			valorVA = 0;
		} else if(!u.validaDouble(textVA)) {
			erros = erros + "Valor do vale alimentação inválido (exemplo: 400,00);\n";
		}
		if(textVR.equals("ex: 400,00")) {
			valorVR = 0;
		} else if(!u.validaDouble(textVR)) {
			erros = erros + "Valor do vale refeição inválido (exemplo: 400,00);\n";
		}
		if(textVT.equals("ex: 400,00")) {
			valorVT = 0;
		} else if(!u.validaDouble(textVT)) {
			erros = erros + "Valor do vale transporte inválido (exemplo: 400,00);\n";
		}
		if(textTelefone.equals("") || textTelefone.equals("ex: 43999565338")){
			erros = erros + "Campo Telefone deve ser preenchido;\n";
			numeros++;
		} else {
			if (!u.validaApenasNumeros(textTelefone)){
				erros = erros + "Campo Telefone deve ser preenchido corretamente (apenas números);\n";
				numeros++;
			}
		}
		if(textEMail.equals("") || textEMail.equals("ex: nome@site.com")){
			erros = erros + "Campo e-Mail deve ser preenchido;\n";
			numeros++;
		} else if (!u.validaEmail(textEMail)){
				erros = erros + "Campo e-mail deve ser preenchido corretamente (nome@site.com);\n";
				numeros++;
		}
		if(textFilhos.equals("ex: 2")) {
			erros = erros + "Campo Número de filhos deve ser preenchido;\n";
			numeros++;
		} else if (!textFilhos.equals("0")) {
			for (JTextField text : arrayTextFilhos) {
				if(text.getText().equals("ex: José")) {
					erros = erros + "Campo Filho " + (arrayTextFilhos.indexOf(text)+1) + " deve ser preenchido;\n";
					numeros++;
				}
			}
			for (JTextField text : arrayTextDatas) {
				if(text.getText().equals("dd/mm/aaaa")) {
					erros = erros + "Campo Data de nascimento do Filho " + (arrayTextDatas.indexOf(text)+1) + " deve ser preenchido;\n";
					numeros++;
				} else {
					if (!u.validaData(text.getText())) {
						erros = erros + "Campo data de nascimento do Filho " + (arrayTextDatas.indexOf(text)+1) + " deve ser preenchido corretamente (dd/mm/aaaa);\n";
						numeros++;
					}
				}
			}
		}
		if (numeros == 0){
			if(valorVA != 0) {
				valorVA = Double.parseDouble(textVA);
			}
			if(valorVR != 0) {
				valorVR = Double.parseDouble(textVR);
			}
			if(valorVT != 0) {
				valorVT = Double.parseDouble(textVT);
			}
			if(!textFilhos.equals("0")) {
				for(JTextField text : arrayTextFilhos) {
					Pessoa filho = new Pessoa(text.getText(), u.transformaData(arrayTextDatas.get(arrayTextFilhos.indexOf(text)).getText()));
					arrayFilhos.add(filho);
				}
			}
			editaProfessor(textCadastro, textNome, textCpf, textData, textEndereço, sexo, boxCargo, boxDisciplina, textTelefone, textEMail, Integer.parseInt(textFilhos), 
						Double.parseDouble(textSalario), valorVA, valorVR, valorVT, arrayFilhos);
			Menu.editando = false;
			JOptionPane.showMessageDialog(null, "Informações do professor foram atualizadas com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			Menu.pessoaEditada = -1;
			iniciaTabela();
		} else {
			Menu.editando = true;
			JOptionPane.showMessageDialog(null, erros, numeros + " erros encontrados:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editaProfessor(String cadastro, String nome, String cpf, String data, String endereço, char sexo, String boxCargo, String boxDisciplina, String telefone, String email, int filhos,
			double salario, double vA, double vR, double vT, ArrayList<Pessoa> arrayFilhos){
		ControllerUtil u = new ControllerUtil();
		Date date = new Date();
		date = u.transformaData(data);
		
		Professor p = new Professor();
		p.setCodCadastro(arrayDisplay.get(Menu.pessoaEditada).getCodCadastro());
		p.setNome(nome);
		p.setCpf(cpf);
		p.setDataNascimento(date);
		p.setEndereço(endereço);
		p.setSexo(sexo);
		p.setCargo(boxCargo);
		p.setDisciplina(boxDisciplina);
		arrayDisplay.get(Menu.pessoaEditada).setDisciplina(boxDisciplina);

		p.setTelefone(telefone);
		p.seteMail(email);
		p.setFilhos(filhos);
		p.setSalario(salario);
		p.setValeAlimentação(vA);
		p.setValeRefeição(vR);
		p.setValeTransporte(vT);
		p.setCadastroFilhos(arrayFilhos);
		
		pDAO.alterarProfessor(p);
		
		arrayDisplay.get(Menu.pessoaEditada).setCodCadastro(cadastro);
		arrayDisplay.get(Menu.pessoaEditada).setNome(nome);
		arrayDisplay.get(Menu.pessoaEditada).setCpf(cpf);
		arrayDisplay.get(Menu.pessoaEditada).setDataNascimento(date);
		arrayDisplay.get(Menu.pessoaEditada).setEndereço(endereço);
		arrayDisplay.get(Menu.pessoaEditada).setSexo(sexo);
		arrayDisplay.get(Menu.pessoaEditada).setCargo(boxCargo);
		arrayDisplay.get(Menu.pessoaEditada).setDisciplina(boxDisciplina);
		arrayDisplay.get(Menu.pessoaEditada).setTelefone(telefone);
		arrayDisplay.get(Menu.pessoaEditada).seteMail(email);
		arrayDisplay.get(Menu.pessoaEditada).setFilhos(filhos);
		arrayDisplay.get(Menu.pessoaEditada).setSalario(salario);
		arrayDisplay.get(Menu.pessoaEditada).setValeAlimentação(vA);
		arrayDisplay.get(Menu.pessoaEditada).setValeRefeição(vR);
		arrayDisplay.get(Menu.pessoaEditada).setValeTransporte(vT);
		arrayDisplay.get(Menu.pessoaEditada).setCadastroFilhos(arrayFilhos);
		Menu.setTextProfessor();
		iniciaTabela();
		Menu.pessoaEditada = -1;
	}
}
