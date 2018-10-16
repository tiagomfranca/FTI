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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
	private static CadastroAluno cA;
	private static CadastroFuncionario cF;
	private static JTextPane textPaneAluno, textPaneFuncionario, textPaneProfessor;

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

	public Menu() {
		cM = new ControllerMenu();
		textPaneAluno = new JTextPane();
		textPaneFuncionario = new JTextPane();
		textPaneProfessor = new JTextPane();
		
		initialize();
	}

	private void initialize() {
		pessoaEditada = -1;
		adicionando = false;
		editando = false;
		cA = new CadastroAluno();
		cF = new CadastroFuncionario();
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
		
		textPaneAluno.setText("Selecione um aluno para ver mais informações.");
		if (cA.getCA().getArrayDisplay().size() == 0){
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
					textPaneAluno.setText(cM.setTextPaneAluno(tabelaAluno.getSelectedRow(), cA));
				} else {
					if (cA.getCA().getArrayDisplay().size() == 0){
						textPaneAluno.setText("Para cadastrar um aluno, clique em Adicionar.");
					} else {
						textPaneAluno.setText("Selecione um aluno para ver mais informações.");
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (tabelaAluno.rowAtPoint(e.getPoint()) < cA.getCA().getArrayDisplay().size()) {
					textPaneAluno.setText(cM.setTextPaneAluno(tabelaAluno.rowAtPoint(e.getPoint()), cA));
				}
				tabelaAluno.addMouseMotionListener(new MouseMotionListener() {
						
						@Override
						public void mouseMoved(MouseEvent evt) {
							if (tabelaAluno.rowAtPoint(evt.getPoint()) < cA.getCA().getArrayDisplay().size() && tabelaAluno.rowAtPoint(evt.getPoint()) > -1) {
								textPaneAluno.setText(cM.setTextPaneAluno(tabelaAluno.rowAtPoint(evt.getPoint()), cA));
							} else if (tabelaAluno.getSelectedRow() >= 0) {
								textPaneAluno.setText(cM.setTextPaneAluno(tabelaAluno.getSelectedRow(), cA));
							} else {
								if (cA.getCA().getArrayDisplay().size() == 0){
									textPaneAluno.setText("Para cadastrar um aluno, clique em Adicionar.");
								} else {
									textPaneAluno.setText("Selecione um aluno para ver mais informações.");
								}
							}
						}
						
						@Override
						public void mouseDragged(MouseEvent e) {
						}
					});

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
					int i = JOptionPane.showOptionDialog(null, "Deseja remover " + cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getNome() + "?", "Confirmar remoção", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolhas, escolhas[1]);
					if (i == 0){
						cA.getCA().removeAluno(Integer.parseInt((String)tabelaFuncionario.getValueAt(tabelaFuncionario.getSelectedRow(), 0)),tabelaAluno.getSelectedRow());
						if (cA.getCA().getArrayDisplay().size() == 0){
							textPaneAluno.setText("Para cadastrar um aluno, clique em Adicionar.");
						}
					}
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum aluno selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
					xcp.printStackTrace();
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
					String nome = cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getNome();
					editando = true;
					cA.getFrame().setVisible(true);
					String cpf = cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getCpf();
					String matricula = cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getMatricula();
					
					String data = ControllerMenu.sdf.format(cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getDataNascimento());
					
					String endereço = cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getEndereço();
					String email = cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).geteMail();
					String telefone = cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getTelefone();
					char sexo = cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getSexo();
					String curso = cA.getCA().getArrayDisplay().get(tabelaAluno.getSelectedRow()).getCurso();
					pessoaEditada = tabelaAluno.getSelectedRow();
					cA.preencheCampo(nome, cpf, matricula, data, endereço, email, telefone, sexo, curso);
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum aluno selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
					xcp.printStackTrace();
				}
			}
		});
		containerTextAluno.add(botaoEditarAluno);
		JPanel abaFuncionario = new JPanel();
		abaFuncionario.setLayout(null);
		abaFuncionario.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				cF.getCF().iniciaTabela();
			}
		});
		tabbedPane.addTab("Lista de Funcionários", null, abaFuncionario, null);
		
		JPanel containerTextFuncionario = new JPanel();
		containerTextFuncionario.setLayout(null);
		containerTextFuncionario.setBorder(new LineBorder(new Color(0, 0, 0)));
		containerTextFuncionario.setBounds(465, 11, 304, 437);
		abaFuncionario.add(containerTextFuncionario);
		
		textPaneFuncionario.setText("Selecione um funcionário para ver mais informações.");
		if (cF.getCF().getArrayDisplay().size() == 0){
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
					if (JOptionPane.showOptionDialog(null, "Deseja remover " + cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getNome() + "?", 
							"Confirmar remoção", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolhas, escolhas[1]) == 0){
						cF.getCF().removeFuncionario(Integer.parseInt((String)tabelaFuncionario.getValueAt(tabelaFuncionario.getSelectedRow(), 0)), tabelaFuncionario.getSelectedRow());
						if (cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getCargo().equals("Professor")){
							cF.getCP().iniciaTabela();
						}
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
					String cadastro = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getCodCadastro();
					editando = true;
					cF.getFrame().setVisible(true);
					String nome = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getNome();
					String cpf = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getCpf();
					String data = ControllerMenu.sdf.format(cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getDataNascimento());
					String endereço = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getEndereço();
					String salario = "" + cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getSalario();
					String vA = "" + cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getValeAlimentação();
					String vR = "" + cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getValeRefeição();
					String vT = "" + cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getValeTransporte();
					String telefone = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getTelefone();
					String email = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).geteMail();
					int filhos = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getFilhos();
					char sexo = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getSexo();
					String cargo = cF.getCF().getArrayDisplay().get(tabelaFuncionario.getSelectedRow()).getCargo();
					pessoaEditada = tabelaFuncionario.getSelectedRow();
					cF.preencheCampoFuncionário(cadastro, nome, cpf, data, endereço, salario, vA, vR, vT, telefone, email, filhos, sexo, cargo);
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum funcionário selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
					xcp.printStackTrace();
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
		tabelaFuncionario.setModel(cF.getCF().modelFuncionario());
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
					textPaneFuncionario.setText(cM.setTextPaneFuncionario(tabelaFuncionario.getSelectedRow(), cF));
				} else {
					if (cF.getCF().getArrayDisplay().size() == 0){
						textPaneFuncionario.setText("Para cadastrar um funcionário, clique em Adicionar.");
					} else {
						textPaneFuncionario.setText("Selecione um funcionário para ver mais informações.");
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (tabelaFuncionario.rowAtPoint(e.getPoint()) < cF.getCF().getArrayDisplay().size()) {
					textPaneFuncionario.setText(cM.setTextPaneFuncionario(tabelaFuncionario.rowAtPoint(e.getPoint()), cF));
				}
					tabelaFuncionario.addMouseMotionListener(new MouseMotionListener() {
						
						@Override
						public void mouseMoved(MouseEvent evt) {
							if (tabelaFuncionario.rowAtPoint(evt.getPoint()) < cF.getCF().getArrayDisplay().size() && tabelaFuncionario.rowAtPoint(evt.getPoint()) > -1) {
								textPaneFuncionario.setText(cM.setTextPaneFuncionario(tabelaFuncionario.rowAtPoint(evt.getPoint()), cF));
							} else if (tabelaFuncionario.getSelectedRow() >= 0) {
								textPaneFuncionario.setText(cM.setTextPaneFuncionario(tabelaFuncionario.getSelectedRow(), cF));
							} else {
								if (cF.getCF().getArrayDisplay().size() == 0){
									textPaneFuncionario.setText("Para cadastrar um funcionário, clique em Adicionar.");
								} else {
									textPaneFuncionario.setText("Selecione um funcionário para ver mais informações.");
								}
							}
						}
						
						@Override
						public void mouseDragged(MouseEvent e) {
						}
					});

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {	
			}
		});
		
		JPanel abaProfessor = new JPanel();
		abaProfessor.setLayout(null);
		abaProfessor.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				cF.getCP().iniciaTabela();
			}
		});
		tabbedPane.addTab("Lista de Professores", null, abaProfessor, null);
		
		JPanel containerTextProfessor = new JPanel();
		containerTextProfessor.setLayout(null);
		containerTextProfessor.setBorder(new LineBorder(new Color(0, 0, 0)));
		containerTextProfessor.setBounds(465, 11, 304, 437);
		abaProfessor.add(containerTextProfessor);
		
		textPaneProfessor.setText("Selecione um professor para ver mais informações.");
		if (cF.getCP().getArrayDisplay().size() == 0){
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
					if (JOptionPane.showOptionDialog(null, "Deseja remover " + cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getNome() + "?", 
							"Confirmar remoção", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolhas, escolhas[1]) == 0){
						cF.getCP().removeProfessor(Integer.parseInt((String)tabelaProfessor.getValueAt(tabelaProfessor.getSelectedRow(), 0)));
						cF.getCF().iniciaTabela();
					}
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum professor selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
					xcp.printStackTrace();
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
					String cadastro = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getCodCadastro();
					editando = true;
					cF.getFrame().setVisible(true);
					String nome = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getNome();
					String cpf = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getCpf();
					String data = ControllerMenu.sdf.format(cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getDataNascimento());
					String endereço = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getEndereço();
					String salario = "" + cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getSalario();
					String vA = "" + cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getValeAlimentação();
					String vR = "" + cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getValeRefeição();
					String vT = "" + cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getValeTransporte();
					String telefone = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getTelefone();
					String email = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).geteMail();
					int filhos = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getFilhos();
					char sexo = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getSexo();
					String cargo = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getCargo();
					String disciplina = cF.getCP().getArrayDisplay().get(tabelaProfessor.getSelectedRow()).getDisciplina();
					pessoaEditada = tabelaProfessor.getSelectedRow();
					cF.preencheCampoProfessor(cadastro, nome, cpf, data, endereço, salario, vA, vR, vT, telefone, email, filhos, sexo, cargo, disciplina);
				} catch (Exception xcp) {
					JOptionPane.showMessageDialog(null, "Nenhum professor selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
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
					textPaneProfessor.setText(cM.setTextPaneProfessor(tabelaProfessor.getSelectedRow(), cF));
				} else {
					if (cF.getCP().getArrayDisplay().size() == 0){
						textPaneProfessor.setText("Para cadastrar um professor, clique em Adicionar.");
					} else {
						textPaneProfessor.setText("Selecione um professor para ver mais informações");
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (tabelaProfessor.rowAtPoint(e.getPoint()) < cF.getCP().getArrayDisplay().size()) {
					textPaneProfessor.setText(cM.setTextPaneProfessor(tabelaProfessor.rowAtPoint(e.getPoint()), cF));
				}
					tabelaProfessor.addMouseMotionListener(new MouseMotionListener() {
						
						@Override
						public void mouseMoved(MouseEvent evt) {
							if (tabelaProfessor.rowAtPoint(evt.getPoint()) < cF.getCP().getArrayDisplay().size() && tabelaProfessor.rowAtPoint(evt.getPoint()) > -1) {
								textPaneProfessor.setText(cM.setTextPaneProfessor(tabelaProfessor.rowAtPoint(evt.getPoint()), cF));
							} else if (tabelaProfessor.getSelectedRow() >= 0) {
								textPaneProfessor.setText(cM.setTextPaneProfessor(tabelaProfessor.getSelectedRow(), cF));
							} else {
								if (cF.getCP().getArrayDisplay().size() == 0){
									textPaneProfessor.setText("Para cadastrar um professor, clique em Adicionar.");
								} else {
									textPaneProfessor.setText("Selecione um professor para ver mais informações.");
								}
							}
						}
						
						@Override
						public void mouseDragged(MouseEvent e) {
						}
					});

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {	
			}
		});
	}
	
	public static void setTextAluno(){
		String texto = "";
		if (cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).getSexo() == 'M'){
			texto = "Masculino";
		} else {
			texto = "Feminino";
		}
		texto = "Matrícula: " + cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).getMatricula() + ";\nNome: " + cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).getNome() + 
				";\nCPF: " + cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).getCpf() + ";\nData de Nascimento: " +	
				ControllerMenu.sdf.format(cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).getDataNascimento()) + 	";\nEndereço: " + cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).getEndereço() +
				";\nSexo: " + texto + ";\nCurso: " + cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).getCurso() +	";\nTelefone: " + cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).getTelefone() + 
				";\ne-mail: " + cA.getCA().getArrayDisplay().get(Menu.pessoaEditada).geteMail() + ";";
		textPaneAluno.setText(texto);
	}
	
	public static void setTextFuncionario(){
		String texto = "";
		if (cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getSexo() == 'M'){
			texto = "Masculino";
		} else {
			texto = "Feminino";
		}
		texto = "Código do cadastro: " + cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getCodCadastro() + ";\nNome: " + cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getNome() + 
				";\nCPF: " + cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getDataNascimento()) + 
				";\nEndereço: " + cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getEndereço() + ";\nSexo: " + texto + ";\nCargo: " + 
				cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getCargo() + ";\nSalário: " + ControllerMenu.nF.format(cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getSalario()) + 
				";\nValor do Vale Alimentação: " + 	ControllerMenu.nF.format(cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getValeAlimentação()) + ";\nValor do Vale Refeição: " + 
				ControllerMenu.nF.format(cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getValeRefeição()) + ";\nValor do Vale Transporte: " +	ControllerMenu.nF.format(cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getValeTransporte()) + 
				";\nTelefone: " + cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getTelefone() + ";\ne-mail: " + cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).geteMail() + 
		";\nNúmero de filhos: " + cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getFilhos() + ";";
		if (cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getFilhos() != 0) {
			texto += "\nFilhos:\n";
			for (Pessoa p : cF.getCF().getArrayDisplay().get(Menu.pessoaEditada).getCadastroFilhos()) {
				texto += p.getNome() + " - " + ControllerMenu.sdf.format(p.getDataNascimento()) + "\n";
			}
		}
		Menu.textPaneFuncionario.setText(texto);
		cF.getCF().iniciaTabela();
	}
	
	public static void setTextProfessor(){
		String texto = "";
		if (cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getSexo() == 'M'){
			texto = "Masculino";
		} else {
			texto = "Feminino";
		}
		texto = "Código do cadastro: " + cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getCodCadastro() + ";\nNome: " + cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getNome() + 
				";\nCPF: " + cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getCpf() + ";\nData de Nascimento: " + ControllerMenu.sdf.format(cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getDataNascimento()) + 
				";\nEndereço: " + cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getEndereço() + ";\nSexo: " + texto + ";\nCargo: " + 
				cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getCargo() + ";\nSalário: " + ControllerMenu.nF.format(cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getSalario()) + 
				";\nValor do Vale Alimentação: " + 	ControllerMenu.nF.format(cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getValeAlimentação()) + ";\nValor do Vale Refeição: " + 
				ControllerMenu.nF.format(cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getValeRefeição()) + ";\nValor do Vale Transporte: " +	ControllerMenu.nF.format(cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getValeTransporte()) + 
				";\nTelefone: " + cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getTelefone() + ";\ne-mail: " + cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).geteMail() + 
		";\nNúmero de filhos: " + cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getFilhos() + ";";
		if (cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getFilhos() != 0) {
			texto += "\nFilhos:\n";
			for (Pessoa p : cF.getCP().getArrayDisplay().get(Menu.pessoaEditada).getCadastroFilhos()) {
				texto += p.getNome() + " - " + ControllerMenu.sdf.format(p.getDataNascimento()) + "\n";
			}
		}
	}
}
