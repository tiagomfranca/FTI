package tiago.exercicios.ex0917;

import javax.swing.JOptionPane;

public class ExFor {

	public static void main(String[] args) {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Quantas vezes deseja repetir?"));
		for (int i = 0; i < n; i++){
			System.out.println(i);
		}
	}
}