package tiago.projetos.pj0925.view;

import tiago.projetos.pj0925.controller.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.JComboBox;

public class CadastroAluno {

	private JFrame frame;
	private JTextArea textEndereço;
	private JTextField textNome, textCpf, textMatricula, textData, textTelefone, textEMail;
	private JRadioButton botaoMale, botaoFemale;
	private Border defaultBorder, simValidou;
	private JButton botaoClear, botaoCadastrar;
	private JComboBox<String> boxCurso;
	private ControllerAluno cA;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAluno window = new CadastroAluno();
					window.frame.setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}

	public CadastroAluno() {
		iniciaJanela();
	}

	private void iniciaJanela() {
		ControllerUtil u = new ControllerUtil();
		cA = new ControllerAluno();
		simValidou = BorderFactory.createLineBorder(Color.GREEN);
		frame = new JFrame("Cadastro de Aluno");
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				if(!Menu.editando){
					if(!Menu.adicionando){
						frame.setVisible(false);
						resetaBotao();
					}
				}
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				Menu.editando = false;
				Menu.adicionando = false;
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
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
		textCpf = new JTextField("ex: 12345678901");
		textCpf.setForeground(Color.gray);
		textCpf.addFocusListener(u.focusListenCpf(textCpf));
		textCpf.setBounds(150, 62, 250, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);
		
//		lbl setBounds(55, 99, 80, 14);
//		text setBounds(150, 96, 250, 20);
		
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
		
		botaoClear = new JButton("Limpar");
		
		botaoClear.setBounds(312, 387, 89, 23);
		frame.getContentPane().add(botaoClear);
		
		botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setBounds(65, 387, 100, 23);
		frame.getContentPane().add(botaoCadastrar);
		
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!Menu.editando){
				cA.botaoCadastrar(textNome.getText(), textCpf.getText(), textMatricula.getText(), botaoMale.isSelected(), botaoFemale.isSelected(), 
						textData.getText(), textEndereço.getText(), boxCurso.getSelectedItem().toString(), textTelefone.getText(), textEMail.getText());
				} else {
					botaoCadastrar.setText("Salvar");
					cA.botaoEditar(textNome.getText(), textCpf.getText(), botaoMale.isSelected(), botaoFemale.isSelected(), 
							textData.getText(), textEndereço.getText(), boxCurso.getSelectedItem().toString(), textTelefone.getText(), textEMail.getText());
					if (!Menu.editando) {
						cA.iniciaTabela();
					}
				}
				if(!Menu.editando) {
					botaoCadastrar.setText("Cadastrar");
				}
			}
		});
		
		botaoClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Menu.editando) {
					botaoCadastrar.setText("Cadastrar");
				}
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
				textCpf.setText("ex: 12345678901");
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
		return botaoClear;
	}
	
	public JButton getBtnCadastro(){
		return botaoCadastrar;
	}
	
	public void preencheCampo(String nome, String cpf, String matricula, String data, String endereço, String email, String telefone, char sexo, String curso) {
		if (Menu.editando) {
			botaoCadastrar.setText("Salvar");
		}
		textNome.setForeground(Color.black);
		textEMail.setForeground(Color.black);
		textData.setForeground(Color.black);
		textEndereço.setForeground(Color.black);
		textCpf.setForeground(Color.black);
		textTelefone.setForeground(Color.black);
		//textMatricula.setForeground(Color.black);
		textNome.setBorder(simValidou);
		textEMail.setBorder(simValidou);
		textTelefone.setBorder(simValidou);
		textCpf.setBorder(simValidou);
		//textMatricula.setBorder(simValidou);
		//textMatricula.setEditable(false);
		textEndereço.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.green), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		textData.setBorder(simValidou);
		textNome.setText(nome);
		textEndereço.setText(endereço);
		textData.setText(data);
		textEMail.setText(email);
		textTelefone.setText(telefone);
		textCpf.setText(cpf);
		//textMatricula.setText(matricula);
		if (sexo == 'M') {
			botaoFemale.setSelected(false);
			botaoMale.setSelected(true);
		} else {
			botaoFemale.setSelected(true);
			botaoMale.setSelected(false);
		}
		boxCurso.setSelectedItem(curso);
	}
	
	public ControllerAluno getCA(){
		return this.cA;
	}
	
	public void resetaBotao(){
		botaoCadastrar.setText("Cadastrar");
	}
}
