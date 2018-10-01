package tiago.projetos.pj0925.view;

import tiago.projetos.pj0925.view.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu {
	
	private JFrame janelaPrincipal, janelaListaAluno, janelaListaProfessor, janelaListaFuncionário;
	
	public static void main (String[] args){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					Menu janela = new Menu();
					janela.janelaPrincipal.setVisible(true);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	public Menu(){
		iniciaJanela();
	}
	
	public void iniciaJanela(){
		CadastroFuncionário cF = new CadastroFuncionário();
		cF.getFrame().setVisible(false);
		
		janelaPrincipal = new JFrame("Menu");
		janelaPrincipal.setLayout(null);
		janelaPrincipal.setSize(new Dimension(500, 485));
		
		JPanel painel = new JPanel();
		painel.setLayout(null);
		painel.setBounds(0,0, 400, 380);
		
		JButton func = new JButton("Adicionar Funcionário");
		func.setBounds(50,50, 200, 50);
		func.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cF.getFrame().setVisible(true);
				janela.setVisible(false);
			}
		});
		painel.add(func);
		//func.setBounds(0,0, 100, 100);
		janelaPrincipal.getContentPane().add(func);
	}
	
	public void listaFuncionários(){
		janelaListaFuncionário = new JFrame("Lista de Funcionários");
		
	}
}
