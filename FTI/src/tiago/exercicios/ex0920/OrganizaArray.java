package tiago.exercicios.ex0920;

import javax.swing.*;

public class OrganizaArray {
	//private static int[] arrayNumeros = new int[10];

	public static void main(String[] args) {
		Funcoes f = new Funcoes();
		int[] arrayNumeros = new int[10];
		arrayNumeros = f.recebeVetor();
		String pane = "Array original:  " + f.stringArray(arrayNumeros);
		
		arrayNumeros = f.ordenaArray(arrayNumeros, 0, arrayNumeros.length-1);
		
		pane = pane + "\nArray ordenado:  " + f.stringArray(arrayNumeros);
		JOptionPane.showMessageDialog(null, pane, "Array ordenado", JOptionPane.INFORMATION_MESSAGE);
	}

}