package tiago.exercicios.ex0917;

import javax.swing.JPanel;
import java.awt.Graphics;

public class DrawPanel extends JPanel{ //desenha um x a partir dos cantos do painel

	public void paintComponent(Graphics g){
		super.paintComponent(g); //chama paintComponent para assegurar que o painel é exibido corretamente
		int width = getWidth(); //largura total
		int height = getHeight(); //altura total
		
		g.drawLine(0, 0, width, height); //desenha uma linha a partir do canto superior esquerdo até o inferior direito
		
		g.drawLine(0, height, width, 0); //desenha uma linha a partir do canto inferior esquerdo até o superior direito
	}

}