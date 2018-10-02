package tiago.projetos.pj0925.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class MenuTeste {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuTeste window = new MenuTeste();
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
	public MenuTeste() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 784, 462);
		frame.getContentPane().add(tabbedPane);
		
		JPanel abaAluno = new JPanel();
		tabbedPane.addTab("Lista de Alunos", null, abaAluno, null);
		abaAluno.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 448, 412);
		abaAluno.add(scrollPane_1);
		
		JPanel panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Matrícula", "Curso", "Nome", "CPF", "e-mail"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(59);
		table.getColumnModel().getColumn(1).setPreferredWidth(96);
		table.getColumnModel().getColumn(2).setPreferredWidth(254);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		table.setBounds(10, 11, 426, 388);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		panel_1.add(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(468, 11, 301, 412);
		abaAluno.add(panel_2);
		panel_2.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 281, 356);
		panel_2.add(textPane);
		
		JButton btnAdicionarAluno = new JButton("Adicionar");
		btnAdicionarAluno.setBounds(10, 378, 89, 23);
		panel_2.add(btnAdicionarAluno);
		
		JButton btnEditarAluno = new JButton("Editar");
		btnEditarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarAluno.setBounds(109, 378, 89, 23);
		panel_2.add(btnEditarAluno);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(208, 378, 89, 23);
		panel_2.add(btnDeletar);
		
		JPanel abaFuncionário = new JPanel();
		abaFuncionário.setLayout(null);
		tabbedPane.addTab("Lista de Funcion\u00E1rios", null, abaFuncionário, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 448, 412);
		abaFuncionário.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(468, 11, 301, 412);
		abaFuncionário.add(panel_4);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(10, 11, 281, 356);
		panel_4.add(textPane_1);
		
		JButton button = new JButton("Adicionar");
		button.setBounds(10, 378, 89, 23);
		panel_4.add(button);
		
		JButton button_1 = new JButton("Editar");
		button_1.setBounds(109, 378, 89, 23);
		panel_4.add(button_1);
		
		JButton button_2 = new JButton("Deletar");
		button_2.setBounds(208, 378, 89, 23);
		panel_4.add(button_2);
		
		JPanel abaProfessor = new JPanel();
		tabbedPane.addTab("Lista de Professores", null, abaProfessor, null);
		abaProfessor.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 448, 412);
		abaProfessor.add(scrollPane_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(468, 11, 301, 412);
		abaProfessor.add(panel_3);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(10, 11, 281, 356);
		panel_3.add(textPane_2);
		
		JButton button_3 = new JButton("Adicionar");
		button_3.setBounds(10, 378, 89, 23);
		panel_3.add(button_3);
		
		JButton button_4 = new JButton("Editar");
		button_4.setBounds(109, 378, 89, 23);
		panel_3.add(button_4);
		
		JButton button_5 = new JButton("Deletar");
		button_5.setBounds(208, 378, 89, 23);
		panel_3.add(button_5);
	}
}
