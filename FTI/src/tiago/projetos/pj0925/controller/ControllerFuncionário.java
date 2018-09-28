package tiago.projetos.pj0925.controller;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControllerFuncionário {
	public JPanel reajustaJanela(int filhos, JPanel container) {
		JPanel novoContainer = container;
		novoContainer.setPreferredSize(new Dimension(750, ((35*filhos)+350)));
		for (int i = filhos; i >= 0; i--) {
			JTextField campoFilho = new JTextField();
			
		}
		return novoContainer;
	}
}
