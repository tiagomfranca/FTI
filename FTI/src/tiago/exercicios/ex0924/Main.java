package tiago.exercicios.ex0924;

public class Main {
	public static void main(String[] args){
		Controle c = new Controle();
		Pessoa[] listaPessoa = new Pessoa[5];
		listaPessoa = c.recebePessoas();
		c.separaPessoas(listaPessoa);
	}
}
