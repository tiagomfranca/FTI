package tiago.projetos.pj0925.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;

import tiago.projetos.pj0925.controller.ControllerMenu;
import tiago.projetos.pj0925.model.Pessoa;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Menu {
	public static boolean editando, adicionando;
	public static int pessoaEditada;
	private JFrame frame;
	private JTable tabelaAluno;
	private JTable tabelaFuncionario;
	private JTable tabelaProfessor;
	private DefaultTableCellRenderer centerRenderer;
	private ControllerMenu cM;
	private static JTextPane textPaneAluno, textPaneFuncionario, textPaneProfessor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public Menu() {
		cM = new ControllerMenu();
		textPaneAluno = new JTextPane();
		textPaneFuncionario = new JTextPane();
		textPaneProfessor = new JTextPane();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		pessoaEditada = -1;
		editando = false;
		CadastroAluno cA = new CadastroAluno();
		CadastroFuncionário cF = new CadastroFuncionário();
		cA.getFrame().setVisible(false);
		cF.getFrame().setVisible(false);
		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		frame = new JFrame("Cadastro FTI");
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 784, 487);
		frame.getContentPane().add(tabbedPane);
		
		JPanel abaAluno = new JPanel();
		tabbedPane.addTab("Lista de Alunos", null, abaAluno, null);
		abaAluno.setLayout(null);
		
		JScrollPane scrollTabelaAluno = new JScrollPane();
		scrollTabelaAluno.setBounds(10, 11, 445, 437);
		abaAluno.add(scrollTabelaAluno);

		tabelaAluno = new JTable();
		tabelaAluno.setCellSelectionEnabled(false);
		tabelaAluno.getTableHeader().setReorderingAllowed(false);
		tabelaAluno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaAluno.setRowSelectionAllowed(true);
		tabelaAluno.setFillsViewportHeight(true);
		tabelaAluno.setEnabled(true);
		tabelaAluno.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabelaAluno.setDefaultRenderer(Object.class, centerRenderer);
		tabelaAluno.setModel(cA.getCA().modelAluno());
		tabelaAluno.getColumnModel().getColumn(0).setResizable(false);
		tabelaAluno.getColumnModel().getColumn(0).setPreferredWidth(109);
		tabelaAluno.getColumnModel().getColumn(1).setResizable(false);
		tabelaAluno.getColumnModel().getColumn(1).setPreferredWidth(135);
		tabelaAluno.getColumnModel().getColumn(2).setResizable(false);
		tabelaAluno.getColumnModel().getColumn(2).setPreferredWidth(258);
		tabelaAluno.getColumnModel().getColumn(3).setResizable(false);
		tabelaAluno.getColumnModel().getColumn(3).setPreferredWidth(121);
		scrollTabelaAluno.setViewportView(tabelaAluno);
		
		JPanel containerTextAluno = new JPanel();
		containerTextAluno.setBorder(new LineBorder(new Color(0, 0, 0)));
		containerTextAluno.setBounds(465, 11, 304, 437);
		abaAluno.add(containerTextAluno);
		containerTextAluno.setLayout(null);
		
		textPaneAluno.setText("Selecione um aluno para ver mais informações");
		if (ControllerMenu.getArrayAluno().size() == 0){
			textPaneAluno.setText("Para cadastrar um aluno, clique em Adicionar.");
		}
		textPaneAluno.setForeground(Color.black);
		textPaneAluno.setBackground(UIManager.getColor("TextPane.disabledBackground"));
		textPaneAluno.setEnabled(true);
		textPaneAluno.setEditable(false);
		textPaneAluno.setBounds(10, 11, 284, 381);		
		textPaneAluno.setBorder(new CompoundBorder(new LineBorder(Color.lightGray), new EmptyBorder(5, 5, 5, 5)));
		containerTextAluno.add(textPaneAluno);
		tabelaAluno.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (tabelaAluno.getSelectedRow() >= 0){
					textPaneAluno.setText(cM.setTextPaneAluno(tabelaAluno.getSelectedRow()));
				} else {
					if (ControllerMenu.getArrayAluno().size() == 0){
						textPaneAluno.setText("Para cadastrar um aluno, clique em Adicionar.");
					} else {
						textPaneAluno.setText("Selecione um aluno para ver mais informações");
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				try{
					int linha = tabelaAluno.rowAtPoint(e.getPoint());
					tabelaAluno.addMouseMotionListener(new MouseMotionListener() {
						
						@Override
						public void mouseMoved(MouseEvent evt) {
							int linha2 = tabelaAluno.rowAtPoint(evt.getPoint());
							if (linha2 != linha && linha2 >= 0) {
								textPaneAluno.setText(cM.setTextPaneAluno(linha2));
							}
						}
						
						@Override
						public void mouseDragged(MouseEvent e) {
						}
					});
					if (tabelaAluno.getSelectedRow() >= 0){
						if (linha != tabelaAluno.getSelectedRow() && linha >= 0){
							textPaneAluno.setText(cM.setTextPaneAluno(linha));
						}
					} else {
//						if (ControllerMenu.getArrayAluno().size() == 0){
//							textPaneAluno.setText("Para cadastrar um aluno, clique em Adicionar.");
//						}
					}
				} catch(Exception xcp){
					textPaneAluno.setText("Selecione um aluno para ver mais informações");
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {	
			}
		});
		
		JButton botaoAdicionarAluno = new JButton("Adicionar");
		botaoAdicionarAluno.setBounds(10, 403, 89, 23);
		botaoAdicionarAluno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionando = true;
				cA.getFrame().setVisible(true);
				cA.getBtnClear().doClick();
				
			}
		});
		containerTextAluno.add(botaoAdicionarAluno);
		
		JButton botaoRemoverAluno = new JButton("Remover");
		botaoRemoverAluno.setBounds(205, 403, 89, 23);
		botaoRemoverAluno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object[] escolhas = {"Sim", "Não"};
					int i = JOptionPane.showOptionDialog(null, "Deseja remover " + ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getNome() + "?", "Confirmar remoção", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolhas, escolhas[1]);
					if (i == 0){
						cA.getCA().removeAluno(tabelaAluno.getSelectedRow());
						if (ControllerMenu.getArrayAluno().size() == 0){
							textPaneAluno.setText("Para cadastrar um aluno, clique em Adicionar.");
						}
					}
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum aluno selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		containerTextAluno.add(botaoRemoverAluno);
		
		JButton botaoEditarAluno = new JButton("Editar");
		botaoEditarAluno.setBounds(107, 403, 89, 23);
		botaoEditarAluno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getNome();
					editando = true;
					cA.getFrame().setVisible(true);
					String cpf = ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getCpf();
					String matricula = ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getMatricula();
					String data = ControllerMenu.sdf.format(ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getDataNascimento());
					String endereço = ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getEndereço();
					String email = ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).geteMail();
					String telefone = ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getTelefone();
					char sexo = ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getSexo();
					String curso = ControllerMenu.getArrayAluno().get(tabelaAluno.getSelectedRow()).getCurso();
					pessoaEditada = tabelaAluno.getSelectedRow();
					cA.preencheCampo(nome, cpf, matricula, data, endereço, email, telefone, sexo, curso);
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum aluno selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		containerTextAluno.add(botaoEditarAluno);
		JPanel abaFuncionario = new JPanel();
		abaFuncionario.setLayout(null);
		tabbedPane.addTab("Lista de Funcionários", null, abaFuncionario, null);
		
		JPanel containerTextFuncionario = new JPanel();
		containerTextFuncionario.setLayout(null);
		containerTextFuncionario.setBorder(new LineBorder(new Color(0, 0, 0)));
		containerTextFuncionario.setBounds(465, 11, 304, 437);
		abaFuncionario.add(containerTextFuncionario);
		
		textPaneFuncionario.setText("Selecione um funcionário para ver mais informações");
		if (ControllerMenu.getArrayFuncionário().size() == 0){
			textPaneFuncionario.setText("Para cadastrar um funcionário, clique em Adicionar.");
		}
		textPaneFuncionario.setForeground(Color.black);
		textPaneFuncionario.setEnabled(true);
		textPaneFuncionario.setEditable(false);
		textPaneFuncionario.setBorder(new CompoundBorder(new LineBorder(Color.lightGray), new EmptyBorder(5, 5, 5, 5)));
		textPaneFuncionario.setBackground(SystemColor.menu);
		textPaneFuncionario.setBounds(10, 11, 284, 381);
		containerTextFuncionario.add(textPaneFuncionario);
		
		JButton botaoAdicionarFuncionario = new JButton("Adicionar");
		botaoAdicionarFuncionario.setBounds(10, 403, 89, 23);
		containerTextFuncionario.add(botaoAdicionarFuncionario);
		botaoAdicionarFuncionario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionando = true;
				cF.getFrame().setVisible(true);
				cF.getBotaoLimpar().doClick();
			}
		});
		
		JButton botaoRemoverFuncionario = new JButton("Remover");
		botaoRemoverFuncionario.setBounds(205, 403, 89, 23);
		botaoRemoverFuncionario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object[] escolhas = {"Sim", "Não"};
					if (JOptionPane.showOptionDialog(null, "Deseja remover " + ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getNome() + "?", 
							"Confirmar remoção", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolhas, escolhas[1]) == 0){
						cF.getCF().removeFuncionario(tabelaFuncionario.getSelectedRow());
					}
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum funcionário selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		containerTextFuncionario.add(botaoRemoverFuncionario);
		
		JButton botaoEditarFuncionario = new JButton("Editar");
		botaoEditarFuncionario.setBounds(107, 403, 89, 23);
		botaoEditarFuncionario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cF.getBotaoLimpar().doClick();
					String cadastro = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getCodCadastro();
					editando = true;
					cF.getFrame().setVisible(true);
					String nome = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getNome();
					String cpf = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getCpf();
					String data = ControllerMenu.sdf.format(ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getDataNascimento());
					String endereço = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getEndereço();
					String salario = "" + ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getSalario();
					String vA = "" + ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getValeAlimentação();
					String vR = "" + ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getValeRefeição();
					String vT = "" + ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getValeTransporte();
					String telefone = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getTelefone();
					String email = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).geteMail();
					int filhos = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getFilhos();
					char sexo = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getSexo();
					String cargo = ControllerMenu.getArrayFuncionário().get(tabelaFuncionario.getSelectedRow()).getCargo();
					pessoaEditada = tabelaFuncionario.getSelectedRow();
					cF.preencheCampoFuncionário(cadastro, nome, cpf, data, endereço, salario, vA, vR, vT, telefone, email, filhos, sexo, cargo);
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum funcionário selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		containerTextFuncionario.add(botaoEditarFuncionario);
		
		JScrollPane scrollTabelaFuncionario = new JScrollPane();
		scrollTabelaFuncionario.setBounds(10, 11, 445, 437);
		abaFuncionario.add(scrollTabelaFuncionario);
		
		tabelaFuncionario = new JTable();
		
		tabelaFuncionario.setCellSelectionEnabled(false);
		tabelaFuncionario.getTableHeader().setReorderingAllowed(false);
		tabelaFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaFuncionario.setRowSelectionAllowed(true);
		tabelaFuncionario.setFillsViewportHeight(true);
		tabelaFuncionario.setEnabled(true);
		tabelaFuncionario.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabelaFuncionario.setDefaultRenderer(Object.class, centerRenderer);
		scrollTabelaFuncionario.setViewportView(tabelaFuncionario);
		tabelaFuncionario.setModel(cF.getCF().modelFuncionário());
		tabelaFuncionario.getColumnModel().getColumn(0).setResizable(false);
		tabelaFuncionario.getColumnModel().getColumn(0).setPreferredWidth(109);
		tabelaFuncionario.getColumnModel().getColumn(1).setResizable(false);
		tabelaFuncionario.getColumnModel().getColumn(1).setPreferredWidth(135);
		tabelaFuncionario.getColumnModel().getColumn(2).setResizable(false);
		tabelaFuncionario.getColumnModel().getColumn(2).setPreferredWidth(258);
		tabelaFuncionario.getColumnModel().getColumn(3).setResizable(false);
		tabelaFuncionario.getColumnModel().getColumn(3).setPreferredWidth(121);
		tabelaFuncionario.setToolTipText("Lista de Funcionarios cadastrados");
		tabelaFuncionario.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (tabelaFuncionario.getSelectedRow() >= 0){
					textPaneFuncionario.setText(cM.setTextPaneFuncionario(tabelaFuncionario.getSelectedRow()));
				} else {
					if (ControllerMenu.getArrayFuncionário().size() == 0){
						textPaneFuncionario.setText("Para cadastrar um funcionário, clique em Adicionar.");
					} else {
						textPaneFuncionario.setText("Selecione um funcionário para ver mais informações");
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				try{
					int linha = tabelaFuncionario.rowAtPoint(e.getPoint());
					tabelaFuncionario.addMouseMotionListener(new MouseMotionListener() {
						
						@Override
						public void mouseMoved(MouseEvent evt) {
							int linha2 = tabelaFuncionario.rowAtPoint(evt.getPoint());
							if (linha2 != linha && linha2 >= 0) {
								textPaneFuncionario.setText(cM.setTextPaneFuncionario(linha2));
							}
						}
						
						@Override
						public void mouseDragged(MouseEvent e) {
						}
					});
					if (tabelaFuncionario.getSelectedRow() >= 0){
						if (linha != tabelaFuncionario.getSelectedRow() && linha >= 0){
							textPaneFuncionario.setText(cM.setTextPaneFuncionario(linha));
						}
					} else {
						textPaneFuncionario.setText("Para cadastrar um funcionário, clique em Adicionar.");
					}
				} catch(Exception xcp){
					textPaneFuncionario.setText("Selecione um funcionário para ver mais informações");
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {	
			}
		});
		
		JPanel abaProfessor = new JPanel();
		abaProfessor.setLayout(null);
		tabbedPane.addTab("Lista de Professores", null, abaProfessor, null);
		
		JPanel containerTextProfessor = new JPanel();
		containerTextProfessor.setLayout(null);
		containerTextProfessor.setBorder(new LineBorder(new Color(0, 0, 0)));
		containerTextProfessor.setBounds(465, 11, 304, 437);
		abaProfessor.add(containerTextProfessor);
		
		textPaneProfessor.setText("Selecione um professor para ver mais informações");
		if (ControllerMenu.getArrayProfessor().size() == 0){
			textPaneProfessor.setText("Para cadastrar um professor, clique em Adicionar.");
		}
		textPaneProfessor.setEnabled(true);
		textPaneProfessor.setEditable(false);
		textPaneProfessor.setForeground(Color.black);
		textPaneProfessor.setBackground(SystemColor.menu);
		textPaneProfessor.setBounds(10, 11, 284, 381);
		textPaneProfessor.setBorder(new CompoundBorder(new LineBorder(Color.lightGray), new EmptyBorder(5, 5, 5, 5)));
		containerTextProfessor.add(textPaneProfessor);
		
		JButton botaoAdicionarProfessor = new JButton("Adicionar");
		botaoAdicionarProfessor.setBounds(10, 403, 89, 23);
		botaoAdicionarProfessor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionando = true;
				cF.getFrame().setVisible(true);
				cF.getBotaoLimpar().doClick();
				cF.setBoxCargo();
			}
		});
		containerTextProfessor.add(botaoAdicionarProfessor);
		
		JButton botaoRemoverProfessor = new JButton("Remover");
		botaoRemoverProfessor.setBounds(205, 403, 89, 23);
		botaoRemoverProfessor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object[] escolhas = {"Sim", "Não"};
					if (JOptionPane.showOptionDialog(null, "Deseja remover " + ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getNome() + "?", 
							"Confirmar remoção", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolhas, escolhas[1]) == 0){
						cF.getCP().removeProfessor(tabelaProfessor.getSelectedRow());
					}
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum professor selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		containerTextProfessor.add(botaoRemoverProfessor);
		
		JButton botaoEditarProfessor = new JButton("Editar");
		botaoEditarProfessor.setBounds(107, 403, 89, 23);
		botaoEditarProfessor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cF.getBotaoLimpar().doClick();
					String cadastro = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getCodCadastro();
					editando = true;
					cF.getFrame().setVisible(true);
					String nome = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getNome();
					String cpf = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getCpf();
					String data = ControllerMenu.sdf.format(ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getDataNascimento());
					String endereço = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getEndereço();
					String salario = "" + ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getSalario();
					String vA = "" + ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getValeAlimentação();
					String vR = "" + ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getValeRefeição();
					String vT = "" + ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getValeTransporte();
					String telefone = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getTelefone();
					String email = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).geteMail();
					int filhos = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getFilhos();
					char sexo = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getSexo();
					String cargo = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getCargo();
					String disciplina = ControllerMenu.getArrayProfessor().get(tabelaProfessor.getSelectedRow()).getDisciplina();
					pessoaEditada = tabelaProfessor.getSelectedRow();
					cF.preencheCampoProfessor(cadastro, nome, cpf, data, endereço, salario, vA, vR, vT, telefone, email, filhos, sexo, cargo, disciplina);
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum funcionário selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		containerTextProfessor.add(botaoEditarProfessor);
		
		JScrollPane scrollTabelaProfessor = new JScrollPane();
		scrollTabelaProfessor.setBounds(10, 11, 445, 437);
		abaProfessor.add(scrollTabelaProfessor);
		
		tabelaProfessor = new JTable();
		tabelaProfessor.setCellSelectionEnabled(false);
		tabelaProfessor.getTableHeader().setReorderingAllowed(false);
		tabelaProfessor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProfessor.setRowSelectionAllowed(true);
		tabelaProfessor.setFillsViewportHeight(true);
		tabelaProfessor.setEnabled(true);
		tabelaProfessor.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabelaProfessor.setDefaultRenderer(Object.class, centerRenderer);
		scrollTabelaProfessor.setViewportView(tabelaProfessor);
		tabelaProfessor.setModel(cF.getCP().modelProfessor());
		tabelaProfessor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProfessor.getColumnModel().getColumn(0).setResizable(false);
		tabelaProfessor.getColumnModel().getColumn(0).setPreferredWidth(109);
		tabelaProfessor.getColumnModel().getColumn(1).setResizable(false);
		tabelaProfessor.getColumnModel().getColumn(1).setPreferredWidth(135);
		tabelaProfessor.getColumnModel().getColumn(2).setResizable(false);
		tabelaProfessor.getColumnModel().getColumn(2).setPreferredWidth(258);
		tabelaProfessor.getColumnModel().getColumn(3).setResizable(false);
		tabelaProfessor.getColumnModel().getColumn(3).setPreferredWidth(121);
		tabelaProfessor.setToolTipText("Lista de professores cadastrados");
		tabelaProfessor.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (tabelaProfessor.getSelectedRow() >= 0){
					textPaneProfessor.setText(cM.setTextPaneProfessor(tabelaProfessor.getSelectedRow()));
				} else {
					if (ControllerMenu.getArrayProfessor().size() == 0){
						textPaneProfessor.setText("Para cadastrar um professor, clique em Adicionar.");
					} else {
						textPaneProfessor.setText("Selecione um professor para ver mais informações");
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				try{
					int linha = tabelaProfessor.rowAtPoint(e.getPoint());
					tabelaProfessor.addMouseMotionListener(new MouseMotionListener() {
						
						@Override
						public void mouseMoved(MouseEvent evt) {
							int linha2 = tabelaProfessor.rowAtPoint(evt.getPoint());
							if (linha2 != linha && linha2 >= 0) {
								textPaneProfessor.setText(cM.setTextPaneProfessor(linha2));
							}
						}
						
						@Override
						public void mouseDragged(MouseEvent e) {
						}
					});
					if (tabelaProfessor.getSelectedRow() >= 0){
						if (linha != tabelaProfessor.getSelectedRow() && linha >= 0){
							textPaneProfessor.setText(cM.setTextPaneProfessor(linha));
						}
					} else {
						textPaneProfessor.setText("Para cadastrar um professor, clique em Adicionar.");
					}
				} catch(Exception xcp){
					textPaneProfessor.setText("Selecione um professor para ver mais informações");
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {	
			}
		});
	}
	public static void setTextAluno(){
		String texto = "";
		if (ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).getSexo() == 'M'){
			texto = "Masculino";
		} else {
			texto = "Feminino";
		}
		texto = "Matrícula: " + ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).getMatricula() + ";\nNome: " + ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).getNome() + 
				";\nCPF: " + ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).getCpf() + ";\nData de Nascimento: " +	
				ControllerMenu.sdf.format(ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).getDataNascimento()) + 	";\nEndereço: " + ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).getEndereço() +
				";\nSexo: " + texto + ";\nCurso: " + ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).getCurso() +	";\nTelefone: " + ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).getTelefone() + 
				";\ne-mail: " + ControllerMenu.getArrayAluno().get(Menu.pessoaEditada).geteMail() + ";";
		textPaneAluno.setText(texto);
	}
	
	public static void setTextFuncionário(){
		String texto = "";
		if (ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getSexo() == 'M'){
			texto = "Masculino";
		} else {
			texto = "Feminino";
		}
		texto = "Código do cadastro: " + ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getCodCadastro() + ";\nNome: " + ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getNome() + 
				";\nCPF: " + ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getDataNascimento()) + 
				";\nEndereço: " + ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getEndereço() + ";\nSexo: " + texto + ";\nCargo: " + 
				ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getCargo() + ";\nSalário: " + ControllerMenu.nF.format(ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getSalario()) + 
				";\nValor do Vale Alimentação: " + 	ControllerMenu.nF.format(ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getValeAlimentação()) + ";\nValor do Vale Refeição: " + 
				ControllerMenu.nF.format(ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getValeRefeição()) + ";\nValor do Vale Transporte: " +	ControllerMenu.nF.format(ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getValeTransporte()) + 
				";\nTelefone: " + ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getTelefone() + ";\ne-mail: " + ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).geteMail() + 
		";\nNúmero de filhos: " + ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getFilhos() + ";";
		if (ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getFilhos() != 0) {
			texto += "\nFilhos:\n";
			for (Pessoa p : ControllerMenu.getArrayFuncionário().get(Menu.pessoaEditada).getCadastroFilhos()) {
				texto += p.getNome() + " - " + ControllerMenu.sdf.format(p.getDataNascimento()) + "\n";
			}
		}
		Menu.textPaneFuncionario.setText(texto);
	}
	
	public static void setTextProfessor(){
		String texto = "";
		if (ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getSexo() == 'M'){
			texto = "Masculino";
		} else {
			texto = "Feminino";
		}
		texto = "Código do cadastro: " + ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getCodCadastro() + ";\nNome: " + ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getNome() + 
				";\nCPF: " + ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getDataNascimento()) + 
				";\nEndereço: " + ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getEndereço() + ";\nSexo: " + texto + ";\nCargo: " + 
				ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getCargo() + ";\nSalário: " + ControllerMenu.nF.format(ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getSalario()) + 
				";\nValor do Vale Alimentação: " + 	ControllerMenu.nF.format(ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getValeAlimentação()) + ";\nValor do Vale Refeição: " + 
				ControllerMenu.nF.format(ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getValeRefeição()) + ";\nValor do Vale Transporte: " +	ControllerMenu.nF.format(ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getValeTransporte()) + 
				";\nTelefone: " + ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getTelefone() + ";\ne-mail: " + ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).geteMail() + 
		";\nNúmero de filhos: " + ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getFilhos() + ";";
		if (ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getFilhos() != 0) {
			texto += "\nFilhos:\n";
			for (Pessoa p : ControllerMenu.getArrayProfessor().get(Menu.pessoaEditada).getCadastroFilhos()) {
				texto += p.getNome() + " - " + ControllerMenu.sdf.format(p.getDataNascimento()) + "\n";
			}
		}
	}
}
