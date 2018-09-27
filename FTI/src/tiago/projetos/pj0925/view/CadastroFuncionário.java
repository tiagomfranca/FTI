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
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class CadastroFuncionário {

	private JFrame frame;
	
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
	}

	private void iniciaJanela() {
		ControllerUtil u = new ControllerUtil();
		ControllerProfessor cP = new ControllerProfessor();
		naoValidou = BorderFactory.createLineBorder(Color.RED);
		simValidou = BorderFactory.createLineBorder(Color.GREEN);
		JPanel container = new JPanel();
		
		frame = new JFrame("Cadastro");
		
		frame.setBounds(0, 0, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNome = new JLabel("Nome:* ");
		lblNome.setBounds(30, 30, 100, 14);
		frame.getContentPane().add(lblNome);
		textNome = new JTextField();
		Border defaultBorder = textNome.getBorder();
		textNome.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textNome.getText().isEmpty()){
					textNome.setForeground(Color.GRAY);
					textNome.setText("ex: José");
				} else if(!u.validaTexto(textNome.getText())){
					textNome.setBorder(naoValidou);
				} else {
					textNome.setBorder(simValidou);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textNome.setForeground(Color.black);
				if(textNome.getText().equals("ex: José")){
					textNome.setText(null);
				}
			}
		});			
		textNome.setBounds(120, 30, 250, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:* ");
		lblSexo.setBounds(30, 100, 46, 14);
		frame.getContentPane().add(lblSexo);
		
		JLabel lblMale = new JLabel("Masculino");
		lblMale.setBounds(168, 100, 60, 14);
		frame.getContentPane().add(lblMale);
		
		JLabel lblFemale = new JLabel("Feminino");
		lblFemale.setBounds(285, 100, 60, 14);
		frame.getContentPane().add(lblFemale);
		
		JRadioButton radioButtonMale = new JRadioButton("");
		radioButtonMale.setBounds(148, 97, 20, 23);
		frame.getContentPane().add(radioButtonMale);

		JRadioButton radioButtonFemale = new JRadioButton("");
		radioButtonFemale.setBounds(265, 97, 20, 23);
		frame.getContentPane().add(radioButtonFemale);
		
		JLabel lblCpf = new JLabel("CPF:* ");
		lblCpf.setBounds(30, 65, 100, 14);
		frame.getContentPane().add(lblCpf);
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
		textCpf.setBounds(120, 65, 250, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblData = new JLabel("Data de");
		lblData.setBounds(40, 130, 100, 14);
		frame.getContentPane().add(lblData);
		JLabel lblData2 = new JLabel("Nascimento:* ");
		lblData2.setBounds(30, 145, 100, 14);
		frame.getContentPane().add(lblData2);
		textData = new JTextField();
		textData.setForeground(Color.gray);
		textData.setText("dd/mm/aaaa");
		textData.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textData.getText().isEmpty()){
					textData.setForeground(Color.GRAY);
					textData.setText("dd/mm/aaaa");
				} else if(!u.validaData(textData.getText())){
					textData.setBorder(naoValidou);
				} else {
					textData.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textData.setForeground(Color.black);
				if(textData.getText().equals("dd/mm/aaaa")){
					textData.setText(null);
				}
			}
		});
		textData.setBounds(120, 135, 250, 20);
		frame.getContentPane().add(textData);
		textData.setColumns(10);
		
		JLabel lblEndereço = new JLabel("Endereço:* ");
		lblEndereço.setBounds(405, 30, 80, 14);
		frame.getContentPane().add(lblEndereço);
				
		JTextArea textEndereço = new JTextArea();
		textEndereço.setBounds(485, 30, 250, 125);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		textEndereço.setForeground(Color.gray);
		textEndereço.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
		textEndereço.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textEndereço.getText().isEmpty()){
					textEndereço.setForeground(Color.GRAY);
					textEndereço.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
				} else if(!u.validaTexto(textEndereço.getText())){
					textEndereço.setBorder(naoValidou);
				} else {
					textEndereço.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textEndereço.setForeground(Color.black);
				if(textEndereço.getText().equals("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303")){
					textEndereço.setText(null);
				}
			}
		});
	    textEndereço.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		frame.getContentPane().add(textEndereço);	
		
		JLabel lblCargo = new JLabel("Cargo:* ");
		lblCargo.setBounds(30, 170, 67, 14);
		frame.getContentPane().add(lblCargo);
		
		JLabel lblDisciplina = new JLabel("Disciplina:* ");
		lblDisciplina.setBounds(405, 170, 67, 14);
		frame.getContentPane().add(lblDisciplina);
		lblDisciplina.setVisible(false);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Selecionar...");
		comboBox.addItem("Banco de Dados");
		comboBox.addItem("Front-end");
		comboBox.addItem("Java WEB");
		comboBox.addItem("Linguagem de Programação Java");
		comboBox.addItem("Outros");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox.setBounds(485, 170, 250, 20);
		frame.getContentPane().add(comboBox);
		comboBox.setVisible(false);
		
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
					comboBox.setVisible(true);
					lblDisciplina.setVisible(true);
				} else {
				comboBox.setVisible(false);
				lblDisciplina.setVisible(false);
				comboBox.setSelectedItem("Selecionar...");
				}
			}
		});
		boxCargo.setBounds(120, 170, 250, 20);
		frame.getContentPane().add(boxCargo);
		
		JLabel lblSalario = new JLabel("Salario:* ");
		lblSalario.setBounds(30, 205, 67, 14);
		frame.getContentPane().add(lblSalario);
		
		textSalario = new JTextField();
		textSalario.setForeground(Color.gray);
		textSalario.setText("ex: 400.00");
		textSalario.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textSalario.getText().isEmpty() || textSalario.getText().equals("ex: 400.00")){
					textSalario.setForeground(Color.GRAY);
					textSalario.setText("ex: 400,00");
				} else if(!u.validaDouble(textSalario.getText())){
					textSalario.setBorder(naoValidou);
				} else {
					textSalario.setBorder(simValidou);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textSalario.setForeground(Color.black);
				if(textSalario.getText().equals("ex: 400.00")){
					textSalario.setText(null);
				}
			}
		});			
		textSalario.setBounds(120, 205, 250, 20);
		frame.getContentPane().add(textSalario);
		textSalario.setColumns(10);
		
		JLabel lblVA = new JLabel("Vale  ");
		lblVA.setBounds(423, 196, 80, 14);
		frame.getContentPane().add(lblVA);
		JLabel lblVA2 = new JLabel("Alimentação:");
		lblVA2.setBounds(402, 212, 80, 14);
		frame.getContentPane().add(lblVA2);
		textVA = new JTextField();
		textVA.setForeground(Color.gray);
		textVA.setText("ex: 400.00");
		textVA.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textVA.getText().isEmpty() || textVA.getText().equals("ex: 400.00")){
					textVA.setForeground(Color.GRAY);
					textVA.setText("ex: 400,00");
				} else if(!u.validaDouble(textVA.getText())){
					textVA.setBorder(naoValidou);
				} else {
					textVA.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textVA.setForeground(Color.black);
				if(textVA.getText().equals("ex: 400.00")){
					textVA.setText(null);
				}
			}
		});			
		textVA.setBounds(485, 205, 250, 20);
		frame.getContentPane().add(textVA);
		textVA.setColumns(10);
		
		JLabel lblVR = new JLabel("Vale ");
		lblVR.setBounds(51, 231, 58, 14);
		frame.getContentPane().add(lblVR);
		JLabel lblVR2 = new JLabel("Refeição: ");
		lblVR2.setBounds(30, 247, 58, 14);
		frame.getContentPane().add(lblVR2);
		
		textVR = new JTextField();
		textVR.setForeground(Color.gray);
		textVR.setText("ex: 400.00");
		textVR.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textVR.getText().isEmpty() || textVR.getText().equals("ex: 400.00")){
					textVR.setForeground(Color.GRAY);
					textVR.setText("ex: 400,00");
				} else if(!u.validaDouble(textVR.getText())){
					textVR.setBorder(naoValidou);
				} else {
					textVR.setBorder(simValidou);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textVR.setForeground(Color.black);
				if(textVR.getText().equals("ex: 400.00")){
					textVR.setText(null);
				}
			}
		});			
		textVR.setBounds(120, 240, 250, 20);
		frame.getContentPane().add(textVR);
		textVR.setColumns(10);
		
		JLabel lblVT = new JLabel("Vale  ");
		lblVT.setBounds(423, 234, 80, 14);
		frame.getContentPane().add(lblVT);
		JLabel lblVT2 = new JLabel("Transporte:");
		lblVT2.setBounds(402, 248, 80, 14);
		frame.getContentPane().add(lblVT2);
		textVT = new JTextField();
		textVT.setForeground(Color.gray);
		textVT.setText("ex: 400.00");
		textVT.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textVT.getText().isEmpty() || textVT.getText().equals("ex: 400.00")){
					textVT.setForeground(Color.GRAY);
					textVT.setText("ex: 400,00");
				} else if(!u.validaDouble(textVT.getText())){
					textVT.setBorder(naoValidou);
				} else {
					textVT.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textVA.setForeground(Color.black);
				if(textVT.getText().equals("ex: 400.00")){
					textVT.setText(null);
				}
			}
		});			
		textVT.setBounds(485, 240, 250, 20);
		frame.getContentPane().add(textVT);
		textVT.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:* ");
		lblTelefone.setBounds(30, 275, 60, 14);
		frame.getContentPane().add(lblTelefone);
		textTelefone = new JTextField();
		textTelefone.setForeground(Color.GRAY);
		textTelefone.setText("ex: 43999565338");
		textTelefone.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textTelefone.getText().isEmpty()){
					textTelefone.setForeground(Color.GRAY);
					textTelefone.setText("ex: 43999565338");
				} else if(!u.validaApenasNumeros(textTelefone.getText())){
					textTelefone.setBorder(naoValidou);
				} else {
					textTelefone.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textTelefone.setForeground(Color.black);
				if(textTelefone.getText().equals("ex: 43999565338")){
					textTelefone.setText(null);
				}
				textTelefone.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						u.apenasNumeros(e);
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						u.apenasNumeros(e);
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						u.apenasNumeros(e);
					}
				});
			}
		});
		textTelefone.setBounds(120, 275, 250, 20);
		frame.getContentPane().add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblEMail = new JLabel("e-mail:* ");
		lblEMail.setBounds(405, 275, 60, 14);
		frame.getContentPane().add(lblEMail);
		textEMail = new JTextField();
		textEMail.setForeground(Color.GRAY);
		textEMail.setText("ex: nome@site.com");
		textEMail.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textEMail.getText().isEmpty()){
					textEMail.setForeground(Color.GRAY);
					textEMail.setText("ex: nome@site.com");
				} else if(!u.validaTexto(textEMail.getText())){
					textEMail.setBorder(naoValidou);
				} else {
					textEMail.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textEMail.setForeground(Color.black);
				if(textEMail.getText().equals("ex: nome@site.com")){
					textEMail.setText(null);
				}
			}
		});
		textEMail.setBounds(485, 275, 250, 20);
		frame.getContentPane().add(textEMail);
		textEMail.setColumns(10);
		
		JLabel lblObrigatorio = new JLabel("*: campo obrigatório");
		lblObrigatorio.setBounds(180, 420, 200, 14);
		frame.getContentPane().add(lblObrigatorio);
		
		JButton btnClear = new JButton("Limpar");
		
		btnClear.setBounds(312, 387, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnSubmit = new JButton("Cadastrar");
		btnSubmit.setBounds(65, 387, 100, 23);
		frame.getContentPane().add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				if(!radioButtonMale.isSelected() && !radioButtonFemale.isSelected()){
					erros = erros + "É necessário informar seu gênero;\n";
					numeros++;
				} else {
					if(radioButtonMale.isSelected()) {
						sexo = 'M';
					} else {
						sexo = 'F';
					}
				}
				if(comboBox.getSelectedItem().equals("Selecione...")){
					erros = erros + "É necessário informar a disciplina;\n";
					numeros++;
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
				if (numeros == 0){
					data = u.transformaData(textData.getText());
//					Professor p = new Professor(textNome.getText(), textCpf.getText(), data, textEndereço.getText(), sexo, comboBox.getSelectedItem().toString(), textTelefone.getText(), textEMail.getText());
//					cP.cadastraProfessor(p);
					JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, erros, numeros + " erros encontrados:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNome.setForeground(Color.GRAY);
				textEMail.setForeground(Color.GRAY);
				textData.setForeground(Color.GRAY);
				textEndereço.setForeground(Color.GRAY);
				textCpf.setForeground(Color.GRAY);
				textTelefone.setForeground(Color.GRAY);
				textNome.setBorder(defaultBorder);
				textEMail.setBorder(defaultBorder);
				textTelefone.setBorder(defaultBorder);
				textCpf.setBorder(defaultBorder);
				textEndereço.setBorder(border);
				textData.setBorder(defaultBorder);
				textNome.setText("ex: José");
				textEndereço.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
				textData.setText("dd/mm/aaaa");
				textEMail.setText("ex: nome@site.com");
				textTelefone.setText("ex: 43999565338");
				textCpf.setText("ex: 12345678901");
				radioButtonFemale.setSelected(false);
				radioButtonMale.setSelected(false);
				comboBox.setSelectedItem("Selecionar...");
			}
		});
		
		radioButtonFemale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				radioButtonMale.setSelected(false);
			}
		});
		
		radioButtonMale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				radioButtonFemale.setSelected(false);
			}
		});
    }
}
