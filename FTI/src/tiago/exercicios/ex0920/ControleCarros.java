package tiago.exercicios.ex0920;

import javax.swing.JOptionPane;

public class ControleCarros {
	private static boolean sinal;
	
	public String recebeCarro(int i) {
		boolean verifica = false;
		String carro = "      ";
		while(verifica == false) {
			carro = JOptionPane.showInputDialog("Insira o nome do carro nº: " + (i+1));
			if (!carro.equals("0")) {
				if (carro != null && !carro.equals("      ") && !carro.equals("")) {
					verifica = true;
				} else {
					JOptionPane.showMessageDialog(null, "Insira o nome de um carro", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return carro;
	}
	public int atribuiNumero(String inicial) {
		int ordem = 0;
		
		switch(inicial.toLowerCase()){
			case "a" : case "á" : case "à" : case "ä" : case "â" : case "ã" : ordem = 1; break;
			case "b" : ordem = 2; break;
			case "c": ordem = 3; break;
			case "d": ordem = 4; break;
			case "e": case "é" : case "ê" : case "è" : case "ë" : ordem = 5; break;
			case "f": ordem = 6; break;
			case "g": ordem = 7; break;
			case "h": ordem = 8; break;
			case "i": case "í" : case "ì" : case "ï" : case "î" : ordem = 9; break;
			case "j": ordem = 10; break;
			case "k": ordem = 11; break;
			case "l": ordem = 12; break;
			case "m": ordem = 13; break;
			case "n": ordem = 14; break;
			case "o": case "ó" : case "ò" : case "õ" : case "ô" : case "ö" : ordem = 15; break;
			case "p": ordem = 16; break;
			case "q": ordem = 17; break;
			case "r": ordem = 18; break;
			case "s": ordem = 19; break;
			case "t": ordem = 20; break;
			case "u": case "ú" : case "ù" : case "ü" : case "û" : ordem = 21; break;
			case "v": ordem = 22; break;
			case "w": ordem = 23; break;
			case "x": ordem = 24; break;
			case "y": ordem = 25; break;
			case "z": ordem = 26; break;
		}
		return ordem;
	}
	
	public int[] montaLinhas(int[] vetorOrdenado) {
		sinal = false;
		int[] vetorLinhas = new int[5];
		for (int i = 0; i <= vetorOrdenado.length-2; i++) {
			if (vetorOrdenado[i] == vetorOrdenado[i+1] && vetorOrdenado[i+1] != 27 && vetorOrdenado[i] != 27) {
				sinal = true;
				for (int j = i+1; j <= vetorOrdenado.length-1; j++) {
					if (vetorOrdenado[j] != 27) {
						if (j != vetorOrdenado.length-1) {
							vetorOrdenado[j] = vetorOrdenado[j+1];
						} else {
							vetorOrdenado[j] = 27;
						}
						if (j == (vetorOrdenado.length-(i+1))) {
							vetorOrdenado[vetorOrdenado.length-(i+1)] = 27;
						}
					}
				}
			}
		}
		if (sinal == true) {
			sinal = false;
			montaLinhas(vetorOrdenado);
		}
		vetorLinhas = vetorOrdenado;
		return vetorLinhas;
	}
	
	public String[][] montaMatriz(String[] matrizOriginal, int[] linhas, int[] letraInicial) {
		String[][] matriz = new String[5][5];
		for (int i = 0; i < 5; i++) {
			if (linhas[i] == 27) {
				break;
			}
			for (int j = 0; j < 5; j++) {
				if (letraInicial[j] == linhas[i]) {
					if (matriz[i][0] == null) {
						matriz[i][0] = matrizOriginal[j];
					} else if (matriz[i][1] == null){
						matriz[i][1] = matrizOriginal[j];
					} else if (matriz[i][2] == null) {
						matriz[i][2] = matrizOriginal[j];
					} else if (matriz[i][3] == null) {
						matriz[i][3] = matrizOriginal[j];
					} else {
						matriz[i][4] = matrizOriginal[j];
					}
				}
			}
		}
		return matriz;
	}
	public void imprimeMatriz(String[][] matrizFormatada, int[] linhas) {
		String saida = "";
		for (int i = 0; i < 5; i++) {
			if (linhas[i] != 27) {
				saida = saida + matrizFormatada[i][0].substring(0, 1).toUpperCase() + "  ";
				for (int j = 0; j < 5; j++) {
					if (matrizFormatada[i][j] != null) {
						saida = saida + matrizFormatada[i][j] + "  ";
					} else {
						if (linhas[i+1]!= 27){
						saida = saida + "\n";
						break;
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, saida, "Tabela de Carros Organizados", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			if (i == 4){
				JOptionPane.showMessageDialog(null, saida, "Tabela de Carros Organizados", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public int[] ordenaArray(int[] arrayNumeros, int inicio, int fim) {
		int[] array = arrayNumeros;
		int i = inicio, j = fim, meio = array[(i+j)/2], aux;
		
		do {
			while(array[i] < meio){
				i++;
			}
			while(array[j] > meio){
				j--;
			}
			if(i <= j){
				aux = array[i];
				array[i] = array[j];
				array[j] = aux;
				i++;
				j--;
			}
		} while (i <= j);
		
		if(inicio < j) {
			ordenaArray(array, inicio, j);
		}
		if(i < fim) {
			ordenaArray(array, i, fim);
		}
		return array;
	}
}