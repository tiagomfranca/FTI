package tiago.exercicios.ex0917;

import javax.swing.JOptionPane;

public class ExBreak {

	public static void main(String[] args) {
		int j = 0;
		int n = Integer.parseInt(JOptionPane.showInputDialog("Após quantos ciclos interromper?"));
		while ( j < 10000) {
			if (j == n) {
				break;
			}
			System.out.println(j);
			j++;
		}
	}
}