package tiago.exercicios.ex0914;

import javax.swing.JOptionPane;

public class FatorialMesmo {

	public static void main(String[] args) {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor"));
		FatorialMesmo b = new FatorialMesmo();	
		int m = b.fatorialRecursivo(n);
	JOptionPane.showMessageDialog(null, "O fatorial de " + n + " é " + m, "Resultado", 2);
	}
	
	public int fatorialRecursivo (int num){
		if (num == 0){
				return 1;
		}
		return num*fatorialRecursivo(num-1);
	}
}