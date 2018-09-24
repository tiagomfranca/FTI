package tiago.exercicios.ex0914;

public class Fatorial {
	public int fatorialRecursivo(int num) {
		if (num == 0) {
			return 1;
		}
		return num * fatorialRecursivo(num - 1);
	}
}