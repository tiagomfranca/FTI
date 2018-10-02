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
	private JTextArea textEndereço;
	private JTextField textNome, textCpf, textMatricula, textData, textTelefone, textEMail;
	private JRadioButton botaoMale, botaoFemale;
	private Border defaultBorder, simValidou;
	private JButton btnClear;
	private JComboBox<String> boxCurso;

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
		simValidou = BorderFactory.createLineBorder(Color.GREEN);
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 489);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textNome = new JTextField("ex: José");
		defaultBorder = textNome.getBorder();
		textNome.setForeground(Color.gray);
		textNome.addFocusListener(u.focusListenNome(textNome, "ex: José"));
		textNome.setBounds(150, 28, 250, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:* ");
		lblNome.setBounds(65, 31, 46, 14);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:* ");
		lblCpf.setBounds(70, 65, 46, 14);
		frame.getContentPane().add(lblCpf);
		textCpf = new JTextField("ex: 123456789");
		textCpf.setForeground(Color.gray);
		textCpf.addFocusListener(u.focusListenCpf(textCpf));
		textCpf.setBounds(150, 62, 250, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matrícula*: ");
		lblMatricula.setBounds(55, 99, 80, 14);
		frame.getContentPane().add(lblMatricula);
		textMatricula = new JTextField("ex: 123456789");
		textMatricula.setForeground(Color.gray);
		textMatricula.addFocusListener(u.focusListenLimiteNumeros(textMatricula, "ex: 123456789", 9));
		textMatricula.setBounds(150, 96, 250, 20);
		frame.getContentPane().add(textMatricula);
		textMatricula.setColumns(10);
		
		JLabel lblData = new JLabel("Data de");
		lblData.setBounds(59, 130, 46, 14);
		frame.getContentPane().add(lblData);
		JLabel lblData2 = new JLabel("Nascimento:* ");
		lblData2.setBounds(45, 145, 100, 14);
		frame.getContentPane().add(lblData2);
		textData = new JTextField("dd/mm/aaaa");
		textData.setForeground(Color.GRAY);
		textData.addFocusListener(u.focusListenData(textData, "dd/mm/aaaa"));
		textData.setBounds(150, 132, 250, 20);
		frame.getContentPane().add(textData);
		textData.setColumns(10);
		
		JLabel lblEndereço = new JLabel("Endereço:* ");
		lblEndereço.setBounds(53, 179, 80, 14);
		frame.getContentPane().add(lblEndereço);
				
		textEndereço = new JTextArea("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
		textEndereço.setBounds(150, 176, 250, 40);
	    textEndereço.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		textEndereço.setForeground(Color.gray);
		textEndereço.addFocusListener(u.focusListenEndereço(textEndereço, "ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303"));
		frame.getContentPane().add(textEndereço);	
		
		JLabel lblSexo = new JLabel("Sexo:* ");
		lblSexo.setBounds(65, 232, 46, 14);
		frame.getContentPane().add(lblSexo);
		
		JLabel lblMale = new JLabel("Masculino");
		lblMale.setBounds(150, 232, 60, 14);
		frame.getContentPane().add(lblMale);
		
		JLabel lblFemale = new JLabel("Feminino");
		lblFemale.setBounds(320, 232, 60, 14);
		frame.getContentPane().add(lblFemale);
		
		botaoMale = new JRadioButton("");
		botaoMale.setBounds(215, 229, 20, 23);
		frame.getContentPane().add(botaoMale);

		botaoFemale = new JRadioButton("");
		botaoFemale.setBounds(380, 229, 20, 23);
		frame.getContentPane().add(botaoFemale);
		
		JLabel lblOccupation = new JLabel("Curso:* ");
		lblOccupation.setBounds(53, 271, 67, 14);
		frame.getContentPane().add(lblOccupation);
		
		boxCurso = new JComboBox<String>();
		boxCurso.addItem("Selecionar...");
		boxCurso.addItem("Java WEB");
		boxCurso.addItem("Cobol");
		boxCurso.addItem(".NET");
		boxCurso.addItem("Redes");
		boxCurso.addItem("Python");
		boxCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		boxCurso.setBounds(150, 268, 250, 20);
		frame.getContentPane().add(boxCurso);
		
		JLabel lblTelefone = new JLabel("Telefone:* ");
		lblTelefone.setBounds(55, 305, 60, 14);
		frame.getContentPane().add(lblTelefone);
		textTelefone = new JTextField("ex: 43999565338");
		textTelefone.setForeground(Color.GRAY);
		textTelefone.addFocusListener(u.focusListenInt(textTelefone, "ex: 43999565338"));
		textTelefone.setBounds(150, 302, 250, 20);
		frame.getContentPane().add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblEMail = new JLabel("e-mail:* ");
		lblEMail.setBounds(65, 339, 60, 14);
		frame.getContentPane().add(lblEMail);
		textEMail = new JTextField("ex: nome@site.com");
		textEMail.setForeground(Color.GRAY);
		textEMail.addFocusListener(u.focusListenEmail(textEMail, "ex: nome@site.com"));
		textEMail.setBounds(150, 336, 250, 20);
		frame.getContentPane().add(textEMail);
		textEMail.setColumns(10);
		
		JLabel lblObrigatorio = new JLabel("*: campo obrigatório");
		lblObrigatorio.setBounds(180, 420, 200, 14);
		frame.getContentPane().add(lblObrigatorio);
		
		btnClear = new JButton("Limpar");
		
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
					erros = erros + "CPF inválido;\n";
				}
				if(textMatricula.getText().isEmpty() || textMatricula.getText().equals("ex: 123456789")){
					erros = erros + "Campo matrícula deve ser preenchido (apenas números);\n";
					numeros++;
				} else if (!u.validaApenasNumeros(textMatricula.getText())){
					erros = erros + "Matrícula deve conter apenas números;\n";
					numeros++;
				} else if (textMatricula.getText().length() > 9){
					erros = erros + "Matrícula deve ter no máximo 9 dígitos;\n";
					numeros++;
				}
				if (textData.getText().isEmpty() || textData.getText().equals("dd/mm/aaaa")) {
					erros = erros + "Campo Data de Nascimento deve ser preenchido;\n";
					numeros++;
				} else if (!u.validaData(textData.getText())){
					erros = erros + "Data inválida, utilize o formato dd/mm/aaaa;\n";
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
				if(boxCurso.getSelectedItem().equals("Selecione...")){
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
					Aluno a = new Aluno(textNome.getText(), textCpf.getText(), textMatricula.getText(), data, textEndereço.getText(), sexo, boxCurso.getSelectedItem().toString(), textTelefone.getText(), textEMail.getText());
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
				textEndereço.setForeground(Color.GRAY);
				textCpf.setForeground(Color.GRAY);
				textTelefone.setForeground(Color.GRAY);
				textNome.setBorder(defaultBorder);
				textEMail.setBorder(defaultBorder);
				textTelefone.setBorder(defaultBorder);
				textCpf.setBorder(defaultBorder);
				textEndereço.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
				textData.setBorder(defaultBorder);
				textNome.setText("ex: José");
				textEndereço.setText("ex: R. Ayrton Senna da Silva, 500\nEdifício Torre di Pietra - 3° andar - sala - 303");
				textData.setText("dd/mm/aaaa");
				textEMail.setText("ex: nome@site.com");
				textTelefone.setText("ex: 43999565338");
				textCpf.setText("ex: 123445678901");
				textMatricula.setText("ex: 123456789");
				botaoFemale.setSelected(false);
				botaoMale.setSelected(false);
				boxCurso.setSelectedItem("Selecionar...");
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
	
	public JFrame getFrame(){
		return frame;
	}
	
	public JButton getBtnClear() {
		return btnClear;
	}
	
	public void preencheCampo(String nome, String cpf, String matrícula, String data, String endereço, String email, String telefone, char sexo, String curso) {
		textNome.setForeground(Color.black);
		textEMail.setForeground(Color.black);
		textData.setForeground(Color.black);
		textEndereço.setForeground(Color.black);
		textCpf.setForeground(Color.black);
		textTelefone.setForeground(Color.black);
		textNome.setBorder(simValidou);
		textEMail.setBorder(simValidou);
		textTelefone.setBorder(simValidou);
		textCpf.setBorder(simValidou);
		textEndereço.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		textData.setBorder(simValidou);
		textNome.setText(nome);
		textEndereço.setText(endereço);
		textData.setText(data);
		textEMail.setText(email);
		textTelefone.setText(telefone);
		textCpf.setText(cpf);
		textMatricula.setText(matrícula);
		if (sexo == 'M') {
			botaoFemale.setSelected(false);
			botaoMale.setSelected(true);
		} else {
			botaoFemale.setSelected(true);
			botaoMale.setSelected(false);
		}
		boxCurso.setSelectedItem(curso);
	}
}
