package tiago.exercicios.ex0920;

public class OrganizaCarros {

	public static void main(String[] args) {
		ControleCarros c = new ControleCarros();
		String[] nomeCarros = new String[5];
		String[][] carros = new String[5][5];
		int[] letraInicial = new int[5];
		int[] ordemLinha = new int[5];
		
		for (int i = 0; i < 5; i++) {
			nomeCarros[i] = c.recebeCarro(i);			
		}
		
		for (int i = 0; i < 5; i++) {
			letraInicial[i] = c.atribuiNumero(nomeCarros[i].substring(0, 1));
			ordemLinha[i] = c.atribuiNumero(nomeCarros[i].substring(0, 1));
		}
		ordemLinha = c.ordenaArray(ordemLinha, 0, 4);
		ordemLinha = c.montaLinhas(ordemLinha);
		carros = c.montaMatriz(nomeCarros, ordemLinha, letraInicial);
		c.imprimeMatriz(carros, ordemLinha);
	}
}