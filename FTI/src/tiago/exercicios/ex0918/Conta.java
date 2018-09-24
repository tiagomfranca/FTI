package tiago.exercicios.ex0918;

abstract class Conta {
	private double saldo;
	
	public void setSaldo(double saldo){
		this.saldo = saldo;
	}
	
	public double getSaldo(){
		return saldo;
	}
	
	public abstract void imprimeExtrato();
}