package tiago.exercicios.ex0925;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ControleArrayList {
	private ArrayList<Integer> arrayNumeros;
	
	public ControleArrayList(){
		arrayNumeros = new ArrayList<Integer>();
	}
	
	public void recebeNumeros(){
		
		for (int i = 0; i < 10; i++){
			arrayNumeros.add(Integer.parseInt(JOptionPane.showInputDialog("Insira um número: ")));
		}
	}

	public void organizaNumeros(){
//		Collections.sort(arrayNumeros);
		arrayNumeros.sort(null);
	}
	
	public void imprimeNumeros(){
		String resultado = "";
		for (Integer numero : arrayNumeros){
			resultado = resultado + "\n" + numero;
		}
//		for (int i = 0; i < 10; i++){
//			resultado = resultado + "\n" + arrayNumeros.get(i);
//		}
		JOptionPane.showMessageDialog(null, resultado, "Lista organizada", JOptionPane.INFORMATION_MESSAGE);
	}
}
