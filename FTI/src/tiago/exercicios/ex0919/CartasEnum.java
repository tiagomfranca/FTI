package tiago.exercicios.ex0919;

public enum CartasEnum {
	J(11), Q(12), K(13), A(14);
	
	public int valorCarta;
	
	CartasEnum (int valor){
		valorCarta = valor;
	}
}