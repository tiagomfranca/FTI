package tiago.exercicios.ex0917;

import javax.swing.JFrame;

public class TesteDesenho {
	public static void main(String[] args){
	DrawPanel teste = new DrawPanel();
	JFrame application = new JFrame();
	application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	application.add(teste);
	application.setSize(540, 320);
	application.setVisible(true);
	
	}

}