package tiago.projetos.pj0925.view;

import tiago.projetos.pj0925.model.*;
import tiago.projetos.pj0925.controller.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class CadastroFuncionário {

	private JFrame frame;
	private ArrayList<JTextField> arrayTextFilhos;
	private ArrayList<JTextField> arrayTextDatas;
	private ArrayList<JLabel> arrayLabels;
	private ArrayList<Pessoa> arrayFilhos;
	
	private Border naoValidou;
	private Border simValidou;
	
	private JLabel lblNome;
	
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textData;
	private JTextField textTelefone;
	private JTextField textEMail;
	private JTextField textFilhos;
	private JTextField textSalario;
	private JTextField textVT;
	private JTextField textVR;
	private JTextField textVA;
	String filhosAntes;;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionário window = new CadastroFuncionário();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroFuncionário() {
		iniciaJanela();
		arrayTextFilhos = new ArrayList<JTextField>();
		arrayTextDatas = new ArrayList<JTextField>();
		arrayLabels = new ArrayList<JLabel>();
		arrayFilhos = new ArrayList<Pessoa>();
		filhosAntes = "";
	}

	private void iniciaJanela() {
		ControllerUtil u = new ControllerUtil();
		ControllerFuncionário cF = new ControllerFuncionário();
		naoValidou = BorderFactory.createLineBorder(Color.RED);
		simValidou = BorderFactory.createLineBorder(Color.GREEN);
		
		JPanel container = new JPanel();
		container.setLayout(null);
		container.setLocation(0,0);
		container.setPreferredSize(new Dimension(750, 385));
		
		JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setSize(800,385);
		scroll.setLocation(0, 100);
		scroll.getViewport().add(container);
		
		frame = new JFrame("Cadastro");
		frame.setResizable(false);
		frame.setSize(785, 451);
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(scroll);
		
		JLabel lblCadastro = new JLabel("Código do");
		JLabel lblCadastro2 = new JLabel("cadastro:* ");
		lblCadastro.setBounds(30, 16, 100, 14);
		lblCadastro2.setBounds(35, 30, 100, 14);
		container.add(lblCadastro);
		container.add(lblCadastro2);
		
		JTextField textCadastro = new JTextField();
		textCadastro.addFocusListener(u.focusListenLimiteNumeros(textCadastro, "ex: 123456789", 9));
		textCadastro.setBounds(120, 30, 250, 20);
		container.add(textCadastro);
		textCadastro.setColumns(10);
		
		lblNome = new JLabel("Nome:* ");
		lblNome.setBounds(45, 65, 100, 14);
		container.add(lblNome);
		textNome = new JTextField();
		Border defaultBorder = textNome.getBorder();
		textNome.addFocusListener(u.focusListenNome(textNome, "ex: José"));			
		textNome.setBounds(120, 65, 250, 20);
		container.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:* ");
		lblCpf.setBounds(52, 100, 100, 14);
		container.add(lblCpf);
		textCpf = new JTextField();
		textCpf.setForeground(Color.gray);
		textCpf.setText("ex: 12345678901");
		textCpf.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textCpf.getText().isEmpty()){
					textCpf.setForeground(Color.GRAY);
					textCpf.setBorder(defaultBorder);
					textCpf.setText("ex: 12345678901");
				} else if(!u.validaCpf(textCpf.getText())){
					textCpf.setBorder(naoValidou);
				} else {
					textCpf.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textCpf.setForeground(Color.black);
				if(textCpf.getText().equals("ex: 12345678901")){
					textCpf.setText(null);
				}
				textCpf.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						u.apenasNumerosLimite(e, textCpf.getText(), 11);
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						u.apenasNumerosLimite(e, textCpf.getText(), 11);
						if (textCpf.getText().length() == 11){
							if (u.validaCpf(textCpf.getText())){
								textCpf.setBorder(simValidou);
							} else {
								textCpf.setBorder(naoValidou);
							}
						} else {
							textCpf.setBorder(naoValidou);
						}
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						u.apenasNumerosLimite(e, textCpf.getText(), 11);
					}
				});
			}
		});
		textCpf.setBounds(120, 100, 250, 20);
		container.add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:* ");
		lblSexo.setBounds(47, 135, 46, 14);
		container.add(lblSexo);
		
		JLabel lblMale = new JLabel("Masculino");
		lblMale.setBounds(168, 135, 60, 14);
		container.add(lblMale);
		
		JRadioButton botaoMale = new JRadioButton("");
		botaoMale.setBounds(148, 132, 20, 23);
		container.add(botaoMale);
		
		JLabel lblFemale = new JLabel("Feminino");
		lblFemale.setBounds(285, 135, 60, 14);
		container.add(lblFemale);
		
		JRadioButton botaoFemale = new JRadioButton("");
		botaoFemale.setBounds(265, 132, 20, 23);
		container.add(botaoFemale);
		
		
		JLabel lblData = new JLabel("Data de");
		lblData.setBounds(40, 165, 100, 14);
		container.add(lblData);
		JLabel lblData2 = new JLabel("Nascimento:* ");
		lblData2.setBounds(30, 180, 100, 14);
		container.add(lblData2);
		textData = new JTextField();
		textData.setForeground(Color.gray);
		textData.setText("dd/mm/aaaa");
		textData.addFocusListener(u.focusListenData(textData, "dd/mm/aaaa"));
		textData.setBounds(120, 170, 250, 20);
		container.add(textData);
		textData.setColumns(10);
		
		JLabel lblEndereço = new JLabel("Endereço:* ");
		lblEndereço.setBounds(405, 65, 80, 14);
		container.add(lblEndereço);
				
		JTextArea textEndereço = new JTextArea();
		textEndereço.setBounds(485, 65, 250, 125);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		textEndereço.setForeground(Color.gray);
		textEndereço.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
		textEndereço.addFocusListener(u.focusListenEndereço(textEndereço, "ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303"));
	    textEndereço.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 2, 2)));
	    container.add(textEndereço);	
		
		JLabel lblDisciplina = new JLabel("Disciplina:* ");
		lblDisciplina.setBounds(405, 205, 67, 14);
		container.add(lblDisciplina);
		lblDisciplina.setVisible(false);
		
		JComboBox<String> boxDisciplina = new JComboBox<String>();
		boxDisciplina.addItem("Selecionar...");
		boxDisciplina.addItem("Banco de Dados");
		boxDisciplina.addItem("Front-end");
		boxDisciplina.addItem("Java WEB");
		boxDisciplina.addItem("Linguagem de Programação Java");
		boxDisciplina.addItem("Outros");
		boxDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		boxDisciplina.setBounds(485, 205, 250, 20);
		container.add(boxDisciplina);
		boxDisciplina.setVisible(false);
		
		JLabel lblCargo = new JLabel("Cargo:* ");
		lblCargo.setBounds(45, 205, 67, 14);
		container.add(lblCargo);
		
		JComboBox<String> boxCargo = new JComboBox<String>();
		boxCargo.addItem("Selecionar...");
		boxCargo.addItem("Professor");
		boxCargo.addItem("Analista Mainframe");
		boxCargo.addItem("Analista Baixa Plataforma");
		boxCargo.addItem("Programador Mainframe");
		boxCargo.addItem("Programador Baixa Plataforma");
		boxCargo.addItem("Líder de Projetos");
		boxCargo.addItem("Gerente");
		boxCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(boxCargo.getSelectedItem().equals("Professor")){
					boxDisciplina.setVisible(true);
					lblDisciplina.setVisible(true);
				} else {
				boxDisciplina.setVisible(false);
				lblDisciplina.setVisible(false);
				boxDisciplina.setSelectedItem("Selecionar...");
				}
			}
		});
		boxCargo.setBounds(120, 205, 250, 20);
		container.add(boxCargo);
		
		JLabel lblSalario = new JLabel("Salario:* ");
		lblSalario.setBounds(41, 240, 67, 14);
		container.add(lblSalario);
		
		textSalario = new JTextField();
		textSalario.setForeground(Color.gray);
		textSalario.setText("ex: 400.00");
		textSalario.addFocusListener(u.focusListenDouble(textSalario, "ex: 400.00"));			
		textSalario.setBounds(120, 240, 250, 20);
		container.add(textSalario);
		textSalario.setColumns(10);
		
		JLabel lblVA = new JLabel("Vale  ");
		lblVA.setBounds(423, 231, 80, 14);
		container.add(lblVA);
		JLabel lblVA2 = new JLabel("Alimentação:");
		lblVA2.setBounds(402, 247, 80, 14);
		container.add(lblVA2);
		textVA = new JTextField();
		textVA.setForeground(Color.gray);
		textVA.setText("ex: 400.00");
		textVA.addFocusListener(u.focusListenDouble(textVA, "ex: 400.00"));
		textVA.setBounds(485, 240, 250, 20);
		container.add(textVA);
		textVA.setColumns(10);
		
		JLabel lblVR = new JLabel("Vale ");
		lblVR.setBounds(51, 266, 58, 14);
		container.add(lblVR);
		JLabel lblVR2 = new JLabel("Refeição: ");
		lblVR2.setBounds(39, 282, 58, 14);
		container.add(lblVR2);
		
		textVR = new JTextField();
		textVR.setForeground(Color.gray);
		textVR.setText("ex: 400.00");
		textVR.addFocusListener(u.focusListenDouble(textVR, "ex: 400.00"));	
		textVR.setBounds(120, 275, 250, 20);
		container.add(textVR);
		textVR.setColumns(10);
		
		JLabel lblVT = new JLabel("Vale  ");
		lblVT.setBounds(423, 266, 80, 14);
		container.add(lblVT);
		JLabel lblVT2 = new JLabel("Transporte:");
		lblVT2.setBounds(402, 282, 80, 14);
		container.add(lblVT2);
		textVT = new JTextField();
		textVT.setForeground(Color.gray);
		textVT.setText("ex: 400.00");
		textVT.addFocusListener(u.focusListenDouble(textVT, "ex: 400.00"));
		textVT.setBounds(485, 275, 250, 20);
		container.add(textVT);
		textVT.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:* ");
		lblTelefone.setBounds(38, 310, 60, 14);
		container.add(lblTelefone);
		textTelefone = new JTextField();
		textTelefone.setForeground(Color.GRAY);
		textTelefone.setText("ex: 43999565338");
		textTelefone.addFocusListener(u.focusListenInt(textTelefone, "ex: 43999565338"));
		textTelefone.setBounds(120, 310, 250, 20);
		container.add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblEMail = new JLabel("e-mail:* ");
		lblEMail.setBounds(405, 310, 60, 14);
		container.add(lblEMail);
		textEMail = new JTextField();
		textEMail.setForeground(Color.GRAY);
		textEMail.setText("ex: nome@site.com");
		textEMail.addFocusListener(u.focusListenEmail(textEMail, "ex: nome@site.com"));
		textEMail.setBounds(485, 310, 250, 20);
		container.add(textEMail);
		textEMail.setColumns(10);
		
		JLabel lblFilhos = new JLabel("Número de ");
		lblFilhos.setBounds(30, 337, 100, 14);
		container.add(lblFilhos);
		JLabel lblFilhos2 = new JLabel("filhos:* ");
		lblFilhos2.setBounds(45, 350, 100, 14);
		container.add(lblFilhos2);
		textFilhos = new JTextField();
		textFilhos.setForeground(Color.GRAY);
		textFilhos.setText("ex: 2");
		textFilhos.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textFilhos.getText().isEmpty() || textFilhos.getText().equals("ex: 2")){
					textFilhos.setBorder(defaultBorder);
					textFilhos.setForeground(Color.GRAY);
					textFilhos.setText("ex: 2");
				} else {
					if (!filhosAntes.equals(textFilhos.getText())) {
						//AJEITAR AQUI
						textFilhos.setBorder(simValidou);
						container.setPreferredSize(new Dimension(750, ((35*Integer.parseInt(textFilhos.getText()))+385)));
						scroll.setPreferredSize(new Dimension(750, ((35*Integer.parseInt(textFilhos.getText()))+385)));
						frame.pack();
						frame.setSize(785, 451);
						for (int i = 1; i <= Integer.parseInt(textFilhos.getText()); i++) {
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
						for(JTextField text : arrayTextFilhos) {
							text.addFocusListener(u.focusListenNome(text, "ex: José"));
						}
						for(JTextField text : arrayTextDatas) {
							text.addFocusListener(u.focusListenData(text, "dd/mm/aaaa"));
						}
						if (textFilhos.getText().equals("0")) {
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
					}
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textFilhos.setForeground(Color.black);
				if(textFilhos.getText().equals("ex: 2")){
					textFilhos.setText(null);
				} else {
					filhosAntes = textFilhos.getText();
				}
				textFilhos.addKeyListener(u.keyListenInt());
			}
		});
		textFilhos.setBounds(120, 345, 250, 20);
		container.add(textFilhos);
		textFilhos.setColumns(10);
		
		JLabel lblObrigatorio = new JLabel("*: campo obrigatório");
		lblObrigatorio.setBounds(450, 30, 200, 14);
		container.add(lblObrigatorio);
		
		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setBounds(440, 342, 100, 23);
		container.add(botaoCadastrar);
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(620, 342, 100, 23);
		container.add(botaoLimpar);
		
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double valorVA = 1;
				double valorVR = 1;
				double valorVT = 1;
				Date data = new Date();
				char sexo = '0';
				String erros = "";
				int numeros = 0;
				
				if(textNome.getText().isEmpty() || textNome.getText().equals("ex: José")) {
					erros = erros + "Campo Nome precisa estar preenchido;\n";
					numeros++;
				}
				if(textCpf.getText().isEmpty() || textCpf.getText().equals("ex: 12345678901")){
					erros = erros + "Campo CPF deve ser preenchido (apenas números);\n";
					numeros++;
				} else if(!u.validaCpf(textCpf.getText())){
					erros = erros + "CPF inválido\n";
				}
				if(!u.validaData(textData.getText())){
					erros = erros + "Campo Data de Nascimento deve ser preenchido corretamente (dd/mm/aaaa);\n";
					numeros++;
				} 
				if(textEndereço.getText().isEmpty() || textEndereço.getText().equals("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303")){
					erros = erros + "Campo Endereço deve ser preenchido;\n";
					numeros++;
				}
				if(!botaoMale.isSelected() && !botaoFemale.isSelected()){
					erros = erros + "É necessário informar seu gênero;\n";
					numeros++;
				} else {
					if(botaoMale.isSelected()) {
						sexo = 'M';
					} else {
						sexo = 'F';
					}
				}
				if (boxCargo.getSelectedItem().equals("Selecione...")) {
					erros = erros + "É necessário informar o cargo;\n";
					numeros++;
				} else if (boxCargo.getSelectedItem().equals("Professor")) {
					erros = erros + "É necessário informar a disciplina;\n";
					numeros++;
				}
				if(textSalario.getText().equals("ex: 400.00")) {
					erros = erros + "É necessário informar o salário;\n";
					numeros++;
				} else if(!u.validaDouble(textSalario.getText())) {
					erros = erros + "Salário inválido (exemplo: 400.00);\n";
				}
				if(textVA.getText().equals("ex: 400.00")) {
					valorVA = 0;
				} else if(!u.validaDouble(textVA.getText())) {
					erros = erros + "Valor do vale alimentação inválido (exemplo: 400.00);\n";
				}
				if(textVR.getText().equals("ex: 400.00")) {
					valorVR = 0;
				} else if(!u.validaDouble(textVR.getText())) {
					erros = erros + "Valor do vale refeição inválido (exemplo: 400.00);\n";
				}
				if(textVT.getText().equals("ex: 400.00")) {
					valorVT = 0;
				} else if(!u.validaDouble(textVT.getText())) {
					erros = erros + "Valor do vale transporte inválido (exemplo: 400.00);\n";
				}
				if(textTelefone.getText().isEmpty() || textTelefone.getText().equals("ex: 43999565338")){
					erros = erros + "Campo Telefone deve ser preenchido;\n";
					numeros++;
				} else {
					if (!u.validaApenasNumeros(textTelefone.getText())){
						erros = erros + "Campo Telefone deve ser preenchido corretamente (apenas números);\n";
						numeros++;
					}
				}
				if(textEMail.getText().isEmpty() || textEMail.getText().equals("ex: nome@site.com")){
					erros = erros + "Campo e-Mail deve ser preenchido;\n";
					numeros++;
				} else if (!u.validaEmail(textEMail.getText())){
						erros = erros + "Campo e-mail deve ser preenchido corretamente (nome@site.com);\n";
						numeros++;
				}
				if(textFilhos.getText().equals("ex: 2")) {
					erros = erros + "Campo Número de filhos deve ser preenchido;\n";
					numeros++;
				} else if (!textFilhos.getText().equals("0")) {
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
								erros = erros + "Campo data de nascimento do Filho " + (arrayTextDatas.indexOf(text)+1) + " deve ser preenchido;\n";
								numeros++;
							}
						}
					}
				}
				if (numeros == 0){
					data = u.transformaData(textData.getText());
					if(valorVA != 0) {
						valorVA = Double.parseDouble(textVA.getText());
					}
					if(valorVR != 0) {
						valorVR = Double.parseDouble(textVR.getText());
					}
					if(valorVT != 0) {
						valorVT = Double.parseDouble(textVT.getText());
					}
					if(!textFilhos.getText().equals("0")) {
						for(JTextField text : arrayTextFilhos) {
							Pessoa filho = new Pessoa(text.getText(), u.transformaData(arrayTextDatas.get(arrayTextFilhos.indexOf(text)).getText()));
							arrayFilhos.add(filho);
						}
					}
					if(boxCargo.getSelectedItem().equals("Professor")) {
						Professor p = new Professor(textNome.getText(), textCpf.getText(), u.transformaData(textData.getText()), textEndereço.getText(), sexo, boxCargo.getSelectedItem().toString(), boxDisciplina.getSelectedItem().toString(),
								Double.parseDouble(textSalario.getText()), valorVA, valorVT, valorVR, Integer.parseInt(textFilhos.getText()), arrayFilhos, textTelefone.getText(), textEMail.getText());
						cF.cadastraProfessor(p);
					} else {
						Funcionário f = new Funcionário(textNome.getText(), textCpf.getText(), u.transformaData(textData.getText()), textEndereço.getText(), sexo, boxCargo.getSelectedItem().toString(),
								Double.parseDouble(textSalario.getText()), valorVA, valorVT, valorVR, Integer.parseInt(textFilhos.getText()), arrayFilhos, textTelefone.getText(), textEMail.getText());
						cF.cadastraFuncionário(f);
					}
					JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, erros, numeros + " erros encontrados:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				textEndereço.setBorder(border);
				textSalario.setBorder(defaultBorder);
				textVA.setBorder(defaultBorder);
				textVR.setBorder(defaultBorder);
				textVT.setBorder(defaultBorder);
				textTelefone.setBorder(defaultBorder);
				textEMail.setBorder(defaultBorder);
				textNome.setText("ex: José");
				textCpf.setText("ex: 12345678901");
				textData.setText("dd/mm/aaaa");
				textEndereço.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
				textSalario.setText("ex: 400.00");
				textVA.setText("ex: 400.00");
				textVR.setText("ex: 400.00");
				textVT.setText("ex: 400.00");
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
		});
		
		botaoFemale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				botaoMale.setSelected(false);
			}
		});
		
		botaoMale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				botaoFemale.setSelected(false);
			}
		});
    }
}
