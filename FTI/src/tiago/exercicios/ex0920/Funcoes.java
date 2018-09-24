package tiago.exercicios.ex0920;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import java.util.ArrayList;

public class Funcoes {
	public static ArrayList<JTextField> campos = new ArrayList<JTextField>();

	
	public int recebeValorArray(int indice){
		int valor = Integer.parseInt(JOptionPane.showInputDialog("Insira o valor do elemento " + indice));
		return valor;
	}

	public int[] ordenaArray(int[] arrayNumeros, int inicio, int fim) {
		int[] array = arrayNumeros;
		int i, j, meio, aux;
		i = inicio;
		j = fim;
		meio = array[(i+j)/2];
		
		do{
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
	
	public String stringArray(int[] arrayEntrada){
		int[] array = arrayEntrada;
		String saida = "";
		for (int i = 0; i <= 9; i++){
			saida = saida + array[i] + "  ";
		}
		return saida;
	}
	
	public void criaCampos(){
		
		JTextField zeroField = new JTextField(5);
	    JTextField umField = new JTextField(5);
	    JTextField doisField = new JTextField(5);
	    JTextField tresField = new JTextField(5);
	    JTextField quatroField = new JTextField(5);
	    JTextField cincoField = new JTextField(5);
	    JTextField seisField = new JTextField(5);
	    JTextField seteField = new JTextField(5);
	    JTextField oitoField = new JTextField(5);
	    JTextField noveField = new JTextField(5);
	    campos.add(zeroField);
	    campos.add(umField);
	    campos.add(doisField);
	    campos.add(tresField);
	    campos.add(quatroField);
	    campos.add(cincoField);
	    campos.add(seisField);
	    campos.add(seteField);
	    campos.add(oitoField);
	    campos.add(noveField);
	}
	public JPanel criaJanela(){		
	    JPanel myPanel = new JPanel();

		criaCampos();

	    myPanel.add(new JLabel("0:"));
	    myPanel.add(campos.get(0));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("1:"));
	    myPanel.add(campos.get(1));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("2:"));
	    myPanel.add(campos.get(2));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("3:"));
	    myPanel.add(campos.get(3));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("4:"));
	    myPanel.add(campos.get(4));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("5:"));
	    myPanel.add(campos.get(5));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("6:"));
	    myPanel.add(campos.get(6));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("7:"));
	    myPanel.add(campos.get(7));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("8:"));
	    myPanel.add(campos.get(8));
	    myPanel.add(Box.createHorizontalStrut(8));
	    myPanel.add(new JLabel("9:"));
	    myPanel.add(campos.get(9));
	    myPanel.add(Box.createHorizontalStrut(8));
	    
	    return myPanel;
	}
	
	public int[] recebeVetor(){
		int[]array = new int[10];
	    JPanel myPanel = criaJanela();
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Insira os valores do vetor", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  array[0] = Integer.parseInt(campos.get(0).getText());
	    	  array[1] = Integer.parseInt(campos.get(1).getText());
	    	  array[2] = Integer.parseInt(campos.get(2).getText());
	    	  array[3] = Integer.parseInt(campos.get(3).getText());
	    	  array[4] = Integer.parseInt(campos.get(4).getText());
	    	  array[5] = Integer.parseInt(campos.get(5).getText());
	    	  array[6] = Integer.parseInt(campos.get(6).getText());
	    	  array[7] = Integer.parseInt(campos.get(7).getText());
	    	  array[8] = Integer.parseInt(campos.get(8).getText());
	    	  array[9] = Integer.parseInt(campos.get(9).getText());
	    	  }
	      return array;
	}
}