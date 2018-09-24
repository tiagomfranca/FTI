package tiago.exercicios.ex0914;

import javax.swing.JOptionPane;

public class Factorial {

	public static void main(String[] args) {

		int n = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor"));
		Fatorial b = new Fatorial();
		int m = b.fatorialRecursivo(n);
		JOptionPane.showMessageDialog(null, "O fatorial de " + n + " é " + m, "Resultado", 2);
	}
}