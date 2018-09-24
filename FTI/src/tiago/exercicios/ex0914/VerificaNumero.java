package tiago.exercicios.ex0914;

import javax.swing.JOptionPane;

public class VerificaNumero {

	public static void main(String[] args) {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Insira o número"));
		if (n > 5) {
			JOptionPane.showMessageDialog(null, n + " é maior do que 5");
		} else {
			if (n < 5) {
				JOptionPane.showMessageDialog(null, n + " é menor do que 5");
			} else {
				JOptionPane.showMessageDialog(null, n + " é igual a 5");
			}
		}
	}
}