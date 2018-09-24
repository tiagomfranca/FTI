package tiago.exercicios.ex0919;

import javax.swing.JOptionPane;

public class SobrecargaDeMetodos {

	public static void main(String[] args) {
		int i = Integer.parseInt(JOptionPane.showInputDialog("Insira um inteiro"));
		System.out.println(square(i));
		double j = Double.parseDouble(JOptionPane.showInputDialog("Insira um double"));
		System.out.println(square(j));
	}
	
	public static int square(int valor){
		System.out.printf("%nsquare com inteiro: %d%n", valor);
		return (valor * valor);		
	}
	
	public static double square (double valor){
		System.out.printf("%nsquare com double: %f%n", valor);
		return valor*valor;
	}
}