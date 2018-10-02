package tiago.projetos.pj0925.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

public class containerAluno extends JPanel {

	/**
	 * Create the panel.
	 */
	public containerAluno() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 499, 800, -500);
		add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 780, 479);
		add(scrollPane);

	}
}
