package tiago.exercicios.ex0924;

import java.util.ArrayList;

public class ArrayListCollection {
	public static void main(String[] args){
		//cria um novo ArrayList de strings com uma capacidade inicial de 10
		ArrayList<String> items = new ArrayList<String>();
		
		items.add("Red"); //anexa um item à lista;
		items.add(0, "Yellow"); //insere "yellow" no índice 0
		
		//cabeçalho
		System.out.println("Display list contents with counter-controlled loop:");
		
		//exibe as cores na lista
		for (int i = 0; i < items.size(); i++){
			System.out.println( items.get(i));
		}
		display(items, "Display list contents with enhanced for statement:");
		
		items.add("Green");
		items.add("Yellow");
		
		display(items, "List with new elements");
		
		items.remove(2);
		display(items, "Remove second list element (green)");
		
	}
	public static void display (ArrayList<String> items, String cabecalho){
		System.out.println(cabecalho);
		for(int i = 0; i< items.size(); i++){
			System.out.println(items.get(i));
		}
	}
}
