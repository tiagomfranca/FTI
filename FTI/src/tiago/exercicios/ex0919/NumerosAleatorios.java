package tiago.exercicios.ex0919;

import java.security.SecureRandom;

public class NumerosAleatorios {
	public static void main(String[] args) {
		SecureRandom secure = new SecureRandom();
		for (int i = 1; i <= 20; i++) { //faz o loop 20 vezes
			int face = secure.nextInt(20);//seleciona o inteiro aleatório entre 0 e 6
			System.out.printf("%d", face);
			if (i%5 == 0){
				System.out.println();
			}
		}
	}
}