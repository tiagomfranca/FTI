package tiago.projetos.pj0925.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import tiago.projetos.pj0925.model.Funcionário;
import tiago.projetos.pj0925.model.Pessoa;
import tiago.projetos.pj0925.model.Professor;

public class ControllerProfessor {
	
	private Border defaultBorder;
	private Border border;
	private DefaultTableModel modelTabelaProfessor;
	
	public ControllerProfessor(){
		border = BorderFactory.createLineBorder(Color.GRAY);
		defaultBorder = new JTextField().getBorder();
		modelTabelaProfessor = new DefaultTableModel(new Object[][] {}, new String[] {"Cadastro", "CPF", "Nome", "Disciplina"});
		//geraListaProfessor(modelTabelaProfessor);
	}
	
	private void geraListaProfessor(DefaultTableModel model) {
		String nome2 = "Tiago de Morais França";
		for (int i = 0; i < 12; i++) {
			ControllerMenu.getArrayProfessor().add(new Professor("123456789", nome2 + i, "07378278904", new Date(), "Rua M",
					'M', "Professor", "Banco de Dados", 4000.00, 800.00, 900.00, 80.00, 5, new ArrayList<Pessoa>(), "43999565338", "tiagomfr@gmail.com"));
		}
		for (Professor professor : ControllerMenu.getArrayProfessor()) {
			String codCadastro = professor.getCodCadastro();
			String cpf = professor.getCpf();
			String nome = professor.getNome();
			String disciplina = professor.getDisciplina();
			Object[] linha = {codCadastro, cpf, nome, disciplina};
			model.addRow(linha);
		}
	}

	public void cadastraProfessor(Professor professor){
		
	}

	public void botaoCadastrar(String textCadastro, String textNome, String textCpf, boolean botaoMale, boolean botaoFemale, String textData, String textEndereço,
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
			Professor p = new Professor(textCadastro, textNome, textCpf, u.transformaData(textData), textEndereço, sexo, boxCargo, boxDisciplina,
					Double.parseDouble(textSalario), valorVA, valorVT, valorVR, Integer.parseInt(textFilhos), arrayFilhos, textTelefone, textEMail);
			cadastraProfessor(p);
			JOptionPane.showMessageDialog(null, "Cadastro de professor efetuado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, erros, numeros + " erros encontrados:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public DefaultTableModel modelProfessor(){
		return this.modelTabelaProfessor;
	}
	
	public void removeProfessor(int i){
		ControllerMenu.getArrayProfessor().remove(i);
		modelTabelaProfessor.removeRow(i);
	}
}
