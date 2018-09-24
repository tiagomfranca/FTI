package tiago.exercicios.ex0919;

public class CalculaSalario {
	public double calculaSalario(double salario){
		return calculaSalario(salario, 0);
	}
	
	public double calculaSalario(double salario, double comissao){
		return (salario + comissao) * 1.1;
	}
}