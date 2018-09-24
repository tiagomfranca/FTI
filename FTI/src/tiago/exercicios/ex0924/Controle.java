package tiago.exercicios.ex0924;

import javax.swing.JOptionPane;

public class Controle {
	
	public Pessoa[] recebePessoas(){		
		Pessoa[] listaPessoas = new Pessoa[5];
		String nome = "";
		int idade = 0;
		char sexo = 'X';
		
		for (int i = 0; i < 5; i++){	
			nome = JOptionPane.showInputDialog("Insira o nome da pessoa");
			idade = Integer.parseInt(JOptionPane.showInputDialog("Insira a idade da pessoa"));
			sexo = JOptionPane.showInputDialog("Insira o Sexo da Pessoa (M ou F)").charAt(0);
			listaPessoas[i] = new Pessoa(nome, idade, sexo);
		}
		
		return listaPessoas;
	}

	public void separaPessoas(Pessoa[] listaPessoas){
		String abaixoDe30 = "";
		String acimaDe30 = "";
		
		for (int i = 0; i < 5; i++){
			
			if(listaPessoas[i].getIdade() < 30){
				abaixoDe30 = abaixoDe30 + "Nome: " + listaPessoas[i].getNome() + "\nIdade: " + 
			listaPessoas[i].getIdade() + "\nSexo: " + listaPessoas[i].getSexo() + '\n';
			} else {
				acimaDe30 = acimaDe30 + "Nome: " + listaPessoas[i].getNome() + "\nIdade: " + 
				listaPessoas[i].getIdade() + "\nSexo: " + listaPessoas[i].getSexo() + '\n';
			}
		}
		
		JOptionPane.showMessageDialog(null, abaixoDe30, "Pessoas com menos de 30 anos", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, acimaDe30, "Pessoas com 30 anos ou mais", JOptionPane.INFORMATION_MESSAGE);
	}
}
