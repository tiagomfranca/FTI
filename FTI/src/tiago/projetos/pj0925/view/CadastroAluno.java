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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.util.Date;

public class CadastroAluno {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textMatricula;
	private JTextField textData;
	private JTextField textTelefone;
	private JTextField textEMail;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAluno window = new CadastroAluno();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroAluno() {
		iniciaJanela();
	}

	private void iniciaJanela() {
		ControllerUtil u = new ControllerUtil();
		ControllerAluno cA = new ControllerAluno();
		Border naoValidou = BorderFactory.createLineBorder(Color.RED);
		Border simValidou = BorderFactory.createLineBorder(Color.GREEN);
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textNome = new JTextField();
		Border defaultBorder = textNome.getBorder();
		textNome.setForeground(Color.gray);
		textNome.setText("ex: Jos�");
		textNome.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textNome.getText().isEmpty()){
					textNome.setForeground(Color.GRAY);
					textNome.setText("ex: Jos�");
				} else if(!u.validaTexto(textNome.getText())){
					textNome.setBorder(naoValidou);
				} else {
					textNome.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textNome.setForeground(Color.black);
				if(textNome.getText().equals("ex: Jos�")){
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
		textCpf.setBounds(150, 62, 250, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matr�cula*: ");
		lblMatricula.setBounds(55, 99, 80, 14);
		frame.getContentPane().add(lblMatricula);
		textMatricula = new JTextField();
		textMatricula.setForeground(Color.gray);
		textMatricula.setText("ex: 123456789");
		textMatricula.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textMatricula.getText().isEmpty()){
					textMatricula.setForeground(Color.GRAY);
					textMatricula.setText("ex: 123456789");
				} else if(!u.validaApenasNumeros(textMatricula.getText())){
					textMatricula.setBorder(naoValidou);
				} else {
					textMatricula.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textMatricula.setForeground(Color.black);
				if(textMatricula.getText().equals("ex: 123456789")){
					textMatricula.setText(null);
				}
				textMatricula.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						u.apenasNumerosLimite(e, textMatricula.getText(), 9);
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						u.apenasNumerosLimite(e, textMatricula.getText(), 9);
						if (textMatricula.getText().length() == 9){
							textMatricula.setBorder(simValidou);
						}
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						u.apenasNumerosLimite(e, textMatricula.getText(), 9);
					}
				});
			}
		});
		textMatricula.setBounds(150, 96, 250, 20);
		frame.getContentPane().add(textMatricula);
		textMatricula.setColumns(10);
		
		JLabel lblData = new JLabel("Data de");
		lblData.setBounds(59, 130, 46, 14);
		frame.getContentPane().add(lblData);
		JLabel lblData2 = new JLabel("Nascimento:* ");
		lblData2.setBounds(45, 145, 100, 14);
		frame.getContentPane().add(lblData2);
		textData = new JTextField();
		textData.setForeground(Color.GRAY);
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
		textData.setBounds(150, 132, 250, 20);
		frame.getContentPane().add(textData);
		textData.setColumns(10);
		
		JLabel lblEndere�o = new JLabel("Endere�o:* ");
		lblEndere�o.setBounds(53, 179, 80, 14);
		frame.getContentPane().add(lblEndere�o);
				
		JTextArea textEndere�o = new JTextArea();
		textEndere�o.setBounds(150, 176, 250, 40);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
	    textEndere�o.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		textEndere�o.setForeground(Color.gray);
		textEndere�o.setText("ex: R. Ayrton Senna da Silva, 500\nEdif�cio Torre di Pietra - 3� andar - sala - 303");
		textEndere�o.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textEndere�o.getText().isEmpty()){
					textEndere�o.setForeground(Color.GRAY);
					textEndere�o.setText("ex: R. Ayrton Senna da Silva, 500\nEdif�cio Torre di Pietra - 3� andar - sala - 303");
				} else if(!u.validaTexto(textEndere�o.getText())){
					textEndere�o.setBorder(naoValidou);
				} else {
					textEndere�o.setBorder(simValidou);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textEndere�o.setForeground(Color.black);
				if(textEndere�o.getText().equals("ex: R. Ayrton Senna da Silva, 500\nEdif�cio Torre di Pietra - 3� andar - sala - 303")){
					textEndere�o.setText(null);
				}
			}
		});
		frame.getContentPane().add(textEndere�o);	
		
		JLabel lblSexo = new JLabel("Sexo:* ");
		lblSexo.setBounds(65, 232, 46, 14);
		frame.getContentPane().add(lblSexo);
		
		JLabel lblMale = new JLabel("Masculino");
		lblMale.setBounds(150, 232, 60, 14);
		frame.getContentPane().add(lblMale);
		
		JLabel lblFemale = new JLabel("Feminino");
		lblFemale.setBounds(320, 232, 60, 14);
		frame.getContentPane().add(lblFemale);
		
		JRadioButton radioButtonMale = new JRadioButton("");
		radioButtonMale.setBounds(215, 229, 20, 23);
		frame.getContentPane().add(radioButtonMale);

		JRadioButton radioButtonFemale = new JRadioButton("");
		radioButtonFemale.setBounds(380, 229, 20, 23);
		frame.getContentPane().add(radioButtonFemale);
		
		JLabel lblOccupation = new JLabel("Curso:* ");
		lblOccupation.setBounds(53, 271, 67, 14);
		frame.getContentPane().add(lblOccupation);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Selecionar...");
		comboBox.addItem("Java WEB");
		comboBox.addItem("Cobol");
		comboBox.addItem(".NET");
		comboBox.addItem("Redes");
		comboBox.addItem("Python");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBox.setBounds(150, 268, 250, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblTelefone = new JLabel("Telefone:* ");
		lblTelefone.setBounds(55, 305, 60, 14);
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
		lblEMail.setBounds(65, 339, 60, 14);
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
		textEMail.setBounds(150, 336, 250, 20);
		frame.getContentPane().add(textEMail);
		textEMail.setColumns(10);
		
		JLabel lblObrigatorio = new JLabel("*: campo obrigat�rio");
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
				
				if(textNome.getText().isEmpty() || textNome.getText().equals("ex: Jos�")) {
					erros = erros + "Campo Nome precisa estar preenchido;\n";
					numeros++;
				}
				if(textCpf.getText().isEmpty() || textCpf.getText().equals("ex: 12345678901")){
					erros = erros + "Campo CPF deve ser preenchido (apenas n�meros);\n";
					numeros++;
				} else if(!u.validaCpf(textCpf.getText())){
					erros = erros + "CPF inv�lido;\n";
				}
				if(textMatricula.getText().isEmpty() || textMatricula.getText().equals("ex: 123456789")){
					erros = erros + "Campo matr�cula deve ser preenchido (apenas n�meros);\n";
					numeros++;
				} else if (!u.validaApenasNumeros(textMatricula.getText())){
					erros = erros + "Matr�cula deve conter apenas n�meros;\n";
					numeros++;
				} else if (textMatricula.getText().length() > 9){
					erros = erros + "Matr�cula deve ter no m�ximo 9 d�gitos;\n";
					numeros++;
				}
				if (textData.getText().isEmpty() || textData.getText().equals("dd/mm/aaaa")) {
					erros = erros + "Campo Data de Nascimento deve ser preenchido;\n";
					numeros++;
				} else if (!u.validaData(textData.getText())){
					erros = erros + "Data inv�lida, utilize o formato dd/mm/aaaa;\n";
					numeros++;
				} 
				if(textEndere�o.getText().isEmpty() || textEndere�o.getText().equals("ex: R. Ayrton Senna da Silva, 500\nEdif�cio Torre di Pietra - 3� andar - sala - 303")){
					erros = erros + "Campo Endere�o deve ser preenchido;\n";
					numeros++;
				}
				if(!radioButtonMale.isSelected() && !radioButtonFemale.isSelected()){
					erros = erros + "� necess�rio informar seu g�nero;\n";
					numeros++;
				} else {
					if(radioButtonMale.isSelected()) {
						sexo = 'M';
					} else {
						sexo = 'F';
					}
				}
				if(comboBox.getSelectedItem().equals("Selecione...")){
					erros = erros + "� necess�rio informar a disciplina;\n";
					numeros++;
				}
				if(textTelefone.getText().isEmpty() || textTelefone.getText().equals("ex: 43999565338")){
					erros = erros + "Campo Telefone deve ser preenchido;\n";
					numeros++;
				} else {
					if (!u.validaApenasNumeros(textTelefone.getText())){
						erros = erros + "Campo Telefone deve ser preenchido corretamente (apenas n�meros);\n";
						numeros++;
					}
				}
				if(textEMail.getText().isEmpty() || textEMail.getText().equals("ex: nome@site.com")){
					erros = erros + "Campo e-Mail deve ser preenchido;\n";
					numeros++;
				} else {
					boolean verificaEmail = false;
					for (int i = 0; i < textEMail.getText().length(); i++){
						if(textEMail.getText().charAt(i) == '@') {
							verificaEmail = true;
						}
					}
					if (verificaEmail == false){
						erros = erros + "Campo e-mail deve ser preenchido corretamente (nome@site.com);\n";
						numeros++;
					}
				}
				if (numeros == 0){
					data = u.transformaData(textData.getText());
					Aluno a = new Aluno(textNome.getText(), textCpf.getText(), textMatricula.getText(), data, textEndere�o.getText(), sexo, comboBox.getSelectedItem().toString(), textTelefone.getText(), textEMail.getText());
					cA.cadastraAluno(a);
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
				textEndere�o.setForeground(Color.GRAY);
				textCpf.setForeground(Color.GRAY);
				textTelefone.setForeground(Color.GRAY);
				textNome.setBorder(defaultBorder);
				textEMail.setBorder(defaultBorder);
				textTelefone.setBorder(defaultBorder);
				textCpf.setBorder(defaultBorder);
				textEndere�o.setBorder(border);
				textData.setBorder(defaultBorder);
				textNome.setText("ex: Jos�");
				textEndere�o.setText("ex: R. Ayrton Senna da Silva, 500\nEdif�cio Torre di Pietra - 3� andar - sala - 303");
				textData.setText("dd/mm/aaaa");
				textEMail.setText("ex: nome@site.com");
				textTelefone.setText("ex: 43999565338");
				textCpf.setText("ex: 123445678901");
				textMatricula.setText("ex: 123456789");
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
