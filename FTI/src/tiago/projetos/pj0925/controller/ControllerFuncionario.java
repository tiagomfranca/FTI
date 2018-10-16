package tiago.projetos.pj0925.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import tiago.projetos.pj0925.dao.FuncionarioDAO;
import tiago.projetos.pj0925.model.Funcionario;
import tiago.projetos.pj0925.model.Pessoa;
import tiago.projetos.pj0925.model.Professor;
import tiago.projetos.pj0925.view.Menu;

public class ControllerFuncionario {
	
	private Border defaultBorder;
	private Border border;
	private DefaultTableModel modelTabelaFuncionario;
	private FuncionarioDAO fDAO;
	private ArrayList<Funcionario> arrayDisplay;
	
	public ControllerFuncionario(){
		fDAO = new FuncionarioDAO();
		border = BorderFactory.createLineBorder(Color.GRAY);
		defaultBorder = new JTextField().getBorder();
		modelTabelaFuncionario = new DefaultTableModel(new Object[][] {}, new String[] {"Cadastro", "CPF", "Nome", "Cargo"});
		iniciaTabela();
	}
	
	public ArrayList<Funcionario> getArrayDisplay(){
		return arrayDisplay;
	}
	
	public void iniciaTabela() {
		modelTabelaFuncionario.setRowCount(0);
		arrayDisplay = fDAO.consultarListaFuncinoario();
		for (Funcionario f : arrayDisplay){
			String cadastro = f.getCodCadastro();
			String cpf = f.getCpf();
			String nome = f.getNome();
			String cargo = f.getCargo();
			Object[] linha = {cadastro, cpf, nome, cargo};
			modelTabelaFuncionario.addRow(linha);
		}
	}

	public void cadastraFuncionário(Funcionario f) {
		modelTabelaFuncionario.setRowCount(0);
		fDAO.cadastrarFuncionario(f);
		
		iniciaTabela();
	}
	
	public void removeFuncionario(int i, int selectedRow){
		fDAO.inativarFuncionario(i);
		modelTabelaFuncionario.removeRow(selectedRow);
	}
	
	public void botaoCadastrar(String textNome, String textCpf, boolean botaoMale, boolean botaoFemale, String textData, String textEndereço,
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
			Funcionario f;
			if (boxCargo.equals("Professor")){
				f = new Professor("", textNome, textCpf, u.transformaData(textData), textEndereço, sexo, boxCargo, boxDisciplina, Double.parseDouble(textSalario), valorVA,
						valorVT, valorVR, Integer.parseInt(textFilhos), arrayFilhos, textTelefone, textEMail);
			} else {
				f = new Funcionario("", textNome, textCpf, u.transformaData(textData), textEndereço, sexo, boxCargo,
						Double.parseDouble(textSalario), valorVA, valorVT, valorVR, Integer.parseInt(textFilhos), arrayFilhos, textTelefone, textEMail);
			}
			cadastraFuncionário(f);
			Menu.adicionando = false;
			JOptionPane.showMessageDialog(null, "Cadastro de funcionário efetuado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			iniciaTabela();
		} else {
			Menu.adicionando = true;
			JOptionPane.showMessageDialog(null, erros, numeros + " erros encontrados:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void botaoEditar(String textNome, String textCpf, boolean botaoMale, boolean botaoFemale, String textData, String textEndereço,
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
			editaFuncionario(textNome, textCpf, textData, textEndereço, sexo, boxCargo, boxDisciplina, textTelefone, textEMail, Integer.parseInt(textFilhos), 
						Double.parseDouble(textSalario), valorVA, valorVR, valorVT, arrayFilhos);
			Menu.editando = false;
			JOptionPane.showMessageDialog(null, "Informações do funcionário foram atualizadas com sucesso..", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			Menu.pessoaEditada = -1;
		} else {
			Menu.editando = true;
			JOptionPane.showMessageDialog(null, erros, numeros + " erros encontrados:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void botaoLimpar(JFrame frame, JPanel container, JTextField textNome, JTextField textCpf, JTextField textData, JTextArea textEndereço, JTextField textSalario,
			JTextField textVA, JTextField textVR, JTextField textVT, JTextField textTelefone, JTextField textEMail, JTextField textFilhos, ArrayList<JTextField> arrayTextFilhos,
			ArrayList<JTextField> arrayTextDatas, ArrayList<JLabel> arrayLabels, JComboBox<String> boxCargo, JComboBox<String> boxDisciplina, JRadioButton botaoMale, JRadioButton botaoFemale){
		textNome.setForeground(Color.GRAY);
		textCpf.setForeground(Color.GRAY);
		textData.setForeground(Color.GRAY);
		textEndereço.setForeground(Color.GRAY);
		textSalario.setForeground(Color.GRAY);
		textVA.setForeground(Color.GRAY);
		textVR.setForeground(Color.gray);
		textVT.setForeground(Color.gray);
		textTelefone.setForeground(Color.GRAY);
		textEMail.setForeground(Color.GRAY);
		textFilhos.setForeground(Color.GRAY);
		textNome.setBorder(defaultBorder);
		textCpf.setBorder(defaultBorder);
		textData.setBorder(defaultBorder);
		textEndereço.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		textSalario.setBorder(defaultBorder);
		textVA.setBorder(defaultBorder);
		textVR.setBorder(defaultBorder);
		textVT.setBorder(defaultBorder);
		textTelefone.setBorder(defaultBorder);
		textEMail.setBorder(defaultBorder);
		textFilhos.setBorder(defaultBorder);
		textNome.setText("ex: José");
		textCpf.setText("ex: 12345678901");
		textData.setText("dd/mm/aaaa");
		textEndereço.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
		textSalario.setText("ex: 400,00");
		textVA.setText("ex: 400,00");
		textVR.setText("ex: 400,00");
		textVT.setText("ex: 400,00");
		textTelefone.setText("ex: 43999565338");
		textEMail.setText("ex: nome@site.com");
		textFilhos.setText("ex: 2");
		botaoFemale.setSelected(false);
		botaoMale.setSelected(false);
		boxCargo.setSelectedItem("Selecionar...");
		boxDisciplina.setSelectedItem("Selecionar...");
		for(JTextField text : arrayTextDatas) {
			container.remove(text);
		}
		for(JTextField text : arrayTextFilhos) {
			container.remove(text);
		}
		for(JLabel label : arrayLabels) {
			container.remove(label);
		}
		container.setPreferredSize(new Dimension(750, 385));
		frame.pack();
		frame.setSize(785, 451);	
	}
	
	public void geraFilhos(int i, JPanel container, ArrayList<JTextField> arrayTextFilhos, ArrayList<JTextField> arrayTextDatas, ArrayList<JLabel> arrayLabels){
		String label = "Filho " + i + ": ";
		
		JLabel lbl = new JLabel(label);
		lbl.setBounds(45, (35*i)+345, 100, 14);
		container.add(lbl);
		
		JTextField texto = new JTextField();
		texto.setBounds(120, (35*i)+345, 250, 20);
		texto.setForeground(Color.gray);
		texto.setText("ex: José");
		arrayTextFilhos.add(texto);
		container.add(texto);
		
		JLabel lbl1 = new JLabel("Data de");
		lbl1.setBounds(415, (35*i)+337, 100, 14);
		container.add(lbl1);
		JLabel lbl2 = new JLabel("Nascimento: ");
		lbl2.setBounds(400, (35*i)+350, 100, 14);
		container.add(lbl2);
		
		JTextField texto2 = new JTextField();
		texto2.setBounds(485, (35*i)+345, 250, 20);
		texto2.setForeground(Color.gray);
		texto2.setText("dd/mm/aaaa");
		arrayTextDatas.add(texto2);
		arrayLabels.add(lbl);
		arrayLabels.add(lbl1);
		arrayLabels.add(lbl2);
		container.add(texto2);
	}
	
	public void deletaFilhos(JFrame frame, JPanel container, ArrayList<JTextField> arrayTextFilhos, ArrayList<JTextField> arrayTextDatas, ArrayList<JLabel> arrayLabels){
		for(JTextField texto : arrayTextFilhos) {
			texto.setText(null);
			container.remove(texto);
		}
		for(JTextField texto : arrayTextDatas) {
			texto.setText(null);
			container.remove(texto);
		}
		for(JLabel label : arrayLabels) {
			container.remove(label);
		}
		arrayTextFilhos.clear();
		arrayTextDatas.clear();
		arrayLabels.clear();
		frame.pack();
		frame.setSize(785, 451);
	}
	
	public DefaultTableModel modelFuncionario(){
		return this.modelTabelaFuncionario;
	}
	
	public void geraFilhosEditar(ArrayList<Pessoa> arrayFilhos, int i, JPanel container, ArrayList<JTextField> arrayTextFilhos, ArrayList<JTextField> arrayTextDatas, ArrayList<JLabel> arrayLabels){
		String label = "Filho " + i + ": ";
		
		JLabel lbl = new JLabel(label);
		lbl.setBounds(45, (35*i)+345, 100, 14);
		container.add(lbl);
		
		JTextField texto = new JTextField();
		texto.setBounds(120, (35*i)+345, 250, 20);
		texto.setText(arrayFilhos.get(i-1).getNome());
		arrayTextFilhos.add(texto);
		container.add(texto);
		texto.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		texto.setForeground(Color.BLACK);
		
		JLabel lbl1 = new JLabel("Data de");
		lbl1.setBounds(415, (35*i)+337, 100, 14);
		container.add(lbl1);
		JLabel lbl2 = new JLabel("Nascimento: ");
		lbl2.setBounds(400, (35*i)+350, 100, 14);
		container.add(lbl2);
		
		JTextField texto2 = new JTextField();
		texto2.setBounds(485, (35*i)+345, 250, 20);
		texto2.setText(ControllerMenu.sdf.format(arrayFilhos.get(i-1).getDataNascimento()));
		texto2.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		texto2.setForeground(Color.BLACK);
		arrayTextDatas.add(texto2);
		arrayLabels.add(lbl);
		arrayLabels.add(lbl1);
		arrayLabels.add(lbl2);
		container.add(texto2);
	}
	
	public void editaFuncionario(String nome, String cpf, String data, String endereço, char sexo, String boxCargo, String boxDisciplina, String telefone, String email, int filhos,
			double salario, double vA, double vR, double vT, ArrayList<Pessoa> arrayFilhos){
		ControllerUtil u = new ControllerUtil();
		Date date = new Date();
		date = u.transformaData(data);
		Funcionario f;
		
		if (boxCargo.equals("Professor")){
			f = new Professor();
		} else {
			f = new Funcionario();
		}
		f.setCodCadastro(arrayDisplay.get(Menu.pessoaEditada).getCodCadastro());
		f.setNome(nome);
		f.setCpf(cpf);
		f.setDataNascimento(date);
		f.setEndereço(endereço);
		f.setSexo(sexo);
		f.setCargo(boxCargo);
		if  (f instanceof Professor){
			((Professor) f).setDisciplina(boxDisciplina);
			((Professor)arrayDisplay.get(Menu.pessoaEditada)).setDisciplina(boxDisciplina);
		}
		f.setTelefone(telefone);
		f.seteMail(email);
		f.setFilhos(filhos);
		f.setSalario(salario);
		f.setValeAlimentação(vA);
		f.setValeRefeição(vR);
		f.setValeTransporte(vT);
		f.setCadastroFilhos(arrayFilhos);
		
		fDAO.alterarFuncionario(f);
		
		arrayDisplay.get(Menu.pessoaEditada).setNome(nome);
		arrayDisplay.get(Menu.pessoaEditada).setCpf(cpf);
		arrayDisplay.get(Menu.pessoaEditada).setDataNascimento(date);
		arrayDisplay.get(Menu.pessoaEditada).setEndereço(endereço);
		arrayDisplay.get(Menu.pessoaEditada).setSexo(sexo);
		arrayDisplay.get(Menu.pessoaEditada).setCargo(boxCargo);
		arrayDisplay.get(Menu.pessoaEditada).setTelefone(telefone);
		arrayDisplay.get(Menu.pessoaEditada).seteMail(email);
		arrayDisplay.get(Menu.pessoaEditada).setSalario(salario);
		arrayDisplay.get(Menu.pessoaEditada).setValeAlimentação(vA);
		arrayDisplay.get(Menu.pessoaEditada).setValeRefeição(vR);
		arrayDisplay.get(Menu.pessoaEditada).setValeTransporte(vT);
		arrayDisplay.get(Menu.pessoaEditada).setFilhos(filhos);
		arrayDisplay.get(Menu.pessoaEditada).setCadastroFilhos(arrayFilhos);
		
		iniciaTabela();
		Menu.setTextFuncionario();
		Menu.pessoaEditada = -1;
	}
	
}
