package tiago.projetos.pj0925.view;

import tiago.projetos.pj0925.model.*;
import tiago.projetos.pj0925.controller.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

public class CadastroProfessor {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textData;
	private JTextField textTelefone;
	private JTextField textEMail;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProfessor window = new CadastroProfessor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroProfessor() {
		iniciaJanela();
	}

	private void iniciaJanela() {
		ControllerUtil u = new ControllerUtil();
		ControllerProfessor cP = new ControllerProfessor();
		Border naoValidou = BorderFactory.createLineBorder(Color.RED);
		Border simValidou = BorderFactory.createLineBorder(Color.GREEN);
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		textNome.setBounds(150, 28, 250, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:* ");
		lblNome.setBounds(65, 31, 46, 14);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:* ");
		lblCpf.setBounds(70, 65, 46, 14);
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
		textCpf.setBounds(150, 65, 250, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblData = new JLabel("Data de");
		lblData.setBounds(59, 105, 46, 14);
		frame.getContentPane().add(lblData);
		JLabel lblData2 = new JLabel("Nascimento:* ");
		lblData2.setBounds(45, 120, 100, 14);
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
		textData.setBounds(150, 109, 250, 20);
		frame.getContentPane().add(textData);
		textData.setColumns(10);
		
		JLabel lblEndereço = new JLabel("Endereço:* ");
		lblEndereço.setBounds(53, 162, 80, 14);
		frame.getContentPane().add(lblEndereço);
				
		JTextArea textEndereço = new JTextArea();
		textEndereço.setBounds(150, 157, 250, 40);
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
		
		JLabel lblSexo = new JLabel("Sexo:* ");
		lblSexo.setBounds(65, 228, 46, 14);
		frame.getContentPane().add(lblSexo);
		
		JLabel lblMale = new JLabel("Masculino");
		lblMale.setBounds(150, 228, 60, 14);
		frame.getContentPane().add(lblMale);
		
		JLabel lblFemale = new JLabel("Feminino");
		lblFemale.setBounds(320, 228, 60, 14);
		frame.getContentPane().add(lblFemale);
		
		JRadioButton radioButtonMale = new JRadioButton("");
		radioButtonMale.setBounds(215, 224, 20, 23);
		frame.getContentPane().add(radioButtonMale);

		JRadioButton radioButtonFemale = new JRadioButton("");
		radioButtonFemale.setBounds(380, 224, 20, 23);
		frame.getContentPane().add(radioButtonFemale);
		
		JLabel lblOccupation = new JLabel("Disciplina:* ");
		lblOccupation.setBounds(53, 270, 67, 14);
		frame.getContentPane().add(lblOccupation);
		
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
		comboBox.setBounds(150, 270, 250, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblTelefone = new JLabel("Telefone:* ");
		lblTelefone.setBounds(55, 302, 60, 14);
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
		textTelefone.setBounds(150, 302, 250, 20);
		frame.getContentPane().add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblEMail = new JLabel("e-mail:* ");
		lblEMail.setBounds(65, 334, 60, 14);
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
		textEMail.setBounds(150, 334, 250, 20);
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
