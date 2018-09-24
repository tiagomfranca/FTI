package tiago.exercicios.ex0920;

public class InitArray {

	public static void main(String[] args) {
		//declara array variável e o inicializa com um objeto array
		int[] array = new int[10];//cria um objeto array
		
		System.out.printf("%s%8s%n", "Index", "Value");//títulos de coluna
		
		for(int counter = 0; counter < array.length; counter++){ //gera saída do valor de cada elemento do array
//			System.out.printf("%5d%8d%n", counter, array[counter]);
			System.out.println("" + counter + "    " + array[counter]);

		}
	}
}