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

public class CadastroAluno {

	private JFrame frame;
	private JTextField textField;
	private JTextField textCpf;
	private JTextField textMatricula;
	private JTextField textData;
	private JTextField textTelefone;
	private JTextField textEMail;
	
	private static boolean nomeValidado, cpfValidado, matriculaValidado, dataValidado, endereçoValidado, sexoValidado, cursoValidado,
		telefoneValidado, emailValidado;

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
		Controller c = new Controller();
		nomeValidado = false; cpfValidado = false; matriculaValidado = false; dataValidado = false; 
		endereçoValidado = false; sexoValidado = false; cursoValidado = false; telefoneValidado = false; emailValidado = false;
		Border naoValidou = BorderFactory.createLineBorder(Color.RED);
		Border simValidou = BorderFactory.createLineBorder(Color.GREEN);
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(Color.gray);
		textField.setText("ex: José");
		textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textField.getText().isEmpty()){
					textField.setForeground(Color.GRAY);
					textField.setText("ex: José");
				} else if(!c.validaTexto(textField.getText())){
					textField.setBorder(naoValidou);
				} else {
					textField.setBorder(simValidou);
					nomeValidado = true;
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textField.setForeground(Color.black);
				if(textField.getText().equals("ex: José")){
					textField.setText(null);
				}
			}
		});
		textField.setBounds(150, 28, 250, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Nome:* ");
		lblName.setBounds(65, 31, 46, 14);
		frame.getContentPane().add(lblName);
		
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
					textCpf.setText("ex: 12345678901");
				} else if(!c.validaCpf(textCpf.getText())){
					textCpf.setBorder(naoValidou);
				} else {
					textCpf.setBorder(simValidou);
					cpfValidado = true;
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textCpf.setForeground(Color.black);
				if(textCpf.getText().equals("ex: 12345678901")){
					textCpf.setText(null);
				}
			}
		});
		textCpf.setBounds(150, 62, 250, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matrícula*: ");
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
				} else if(!c.validaApenasNumeros(textMatricula.getText())){
					textMatricula.setBorder(naoValidou);
				} else {
					textMatricula.setBorder(simValidou);
					matriculaValidado = true;
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textMatricula.setForeground(Color.black);
				if(textMatricula.getText().equals("ex: 123456789")){
					textMatricula.setText(null);
				}
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
				} else if(!c.validaData(textData.getText())){
					textData.setBorder(naoValidou);
				} else {
					textData.setBorder(simValidou);
					dataValidado = true;
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
		
		JLabel lblAddress = new JLabel("Endereço:* ");
		lblAddress.setBounds(53, 179, 80, 14);
		frame.getContentPane().add(lblAddress);
				
		JTextArea textArea1 = new JTextArea();
		textArea1.setBounds(150, 176, 250, 40);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
	    textArea1.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		textArea1.setForeground(Color.gray);
		textArea1.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
		textArea1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(textArea1.getText().isEmpty()){
					textArea1.setForeground(Color.GRAY);
					textArea1.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
				} else if(!c.validaTexto(textArea1.getText())){
					textArea1.setBorder(naoValidou);
				} else {
					textArea1.setBorder(simValidou);
					endereçoValidado = true;
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textArea1.setForeground(Color.black);
				if(textArea1.getText().equals("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303")){
					textArea1.setText(null);
				}
			}
		});
		frame.getContentPane().add(textArea1);	
		
		JLabel lblSex = new JLabel("Sexo:* ");
		lblSex.setBounds(65, 232, 46, 14);
		frame.getContentPane().add(lblSex);
		
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
				} else if(!c.validaApenasNumeros(textTelefone.getText())){
					textTelefone.setBorder(naoValidou);
				} else {
					textTelefone.setBorder(simValidou);
					telefoneValidado = true;
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textTelefone.setForeground(Color.black);
				if(textTelefone.getText().equals("ex: 43999565338")){
					textTelefone.setText(null);
				}
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
				} else if(!c.validaTexto(textEMail.getText())){
					textEMail.setBorder(naoValidou);
				} else {
					textEMail.setBorder(simValidou);
					emailValidado = true;
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
		
		JLabel lblObrigatorio = new JLabel("*: campo obrigatório");
		lblObrigatorio.setBounds(180, 420, 200, 14);
		frame.getContentPane().add(lblObrigatorio);
		
		JButton btnClear = new JButton("Limpar");
		
		btnClear.setBounds(312, 387, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnSubmit = new JButton("Cadastrar");
		
		btnSubmit.setBackground(Color.BLACK);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBounds(65, 387, 100, 23);
		frame.getContentPane().add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				Controller c = new Controller();
				String erros = "";
				int numeros = 0;
				
				if(textField.getText().isEmpty()) {
					erros = erros + "Campo Nome precisa estar preenchido;\n";
					numeros++;
				}
				if(textCpf.getText().isEmpty()){
					erros = erros + "Campo CPF deve ser preenchido (apenas números);\n";
					numeros++;
				} else if(!c.validaCpf(textCpf.getText())){
					erros = erros + "CPF inválido;\n";
				}
				if(textMatricula.getText().isEmpty()){
					erros = erros + "Campo matrícula deve ser preenchido (apenas números);\n";
					numeros++;
				} else if (!c.validaApenasNumeros(textMatricula.getText())){
					erros = erros + "Matrícula deve conter apenas números;\n";
					numeros++;
				} else if (textMatricula.getText().length() > 9){
					erros = erros + "Matrícula deve ter no máximo 9 dígitos;\n";
					numeros++;
				}
				if(!c.validaData(textData.getText())){
					erros = erros + "Campo Data de Nascimento deve ser preenchido corretamente (dd/mm/aaaa);\n";
					numeros++;
				} 
				if(textArea1.getText().isEmpty()){
					erros = erros + "Campo Endereço deve ser preenchido;\n";
					numeros++;
				}
				if(!radioButtonMale.isSelected() && !radioButtonFemale.isSelected()){
					erros = erros + "É necessário informar seu gênero;\n";
					numeros++;
				}
				if(comboBox.getSelectedItem().equals("Selecione...")){
					erros = erros + "É necessário informar a disciplina;\n";
					numeros++;
				}
				if(textTelefone.getText().isEmpty()){
					erros = erros + "Campo Telefone deve ser preenchido;\n";
					numeros++;
				} else {
					if (!c.validaApenasNumeros(textTelefone.getText())){
						erros = erros + "Campo Telefone deve ser preenchido corretamente (apenas números);\n";
						numeros++;
					}
				}
				if(textEMail.getText().isEmpty()){
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
					Aluno a = new Aluno();
					c.cadastraAluno(a);
					JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, erros, numeros + " erros encontrados:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textArea1.setText(null);
				textData.setText(null);
				textEMail.setText(null);
				textTelefone.setText(null);
				textCpf.setText(null);
				textMatricula.setText(null);
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
