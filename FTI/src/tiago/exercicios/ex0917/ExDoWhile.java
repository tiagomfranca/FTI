package tiago.exercicios.ex0917;

import javax.swing.JOptionPane;

public class ExDoWhile {

	public static void main(String[] args) {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Número alvo"));
		int input;
		do {
			input = Integer.parseInt(JOptionPane.showInputDialog("Insira um número"));
		} while (input != n);
		JOptionPane.showMessageDialog(null, "Número alcançado!");
		}

}