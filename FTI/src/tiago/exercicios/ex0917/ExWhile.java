package tiago.exercicios.ex0917;

import javax.swing.JOptionPane;

public class ExWhile {

	public static void main(String[] args) {
		int j = 0;
		int n = Integer.parseInt(JOptionPane.showInputDialog(null, 
				"Insira o número de vezes para repetir"));
		while (j <= n){
			System.out.println(n);
			n++;
		}

	}

}