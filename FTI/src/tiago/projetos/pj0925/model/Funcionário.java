package tiago.projetos.pj0925.model;

import java.util.ArrayList;
import java.util.Date;

public class Funcionário extends Pessoa {
	private String endereço, cargo, telefone, eMail;
	double salario, valeAlimentação, valeTransporte, valeRefeição;
	private int filhos;
	private ArrayList<Pessoa> cadastroFilhos;
	
	public Funcionário(String nome, String cpf, Date dataNascimento, String endereço, char sexo, String cargo, double salario,
			double valeAlimentação, double valeTransporte, double valeRefeição, int filhos, ArrayList<Pessoa> cadastroFilhos,
			String telefone, String eMail){
		super(nome, cpf, dataNascimento, sexo);
		this.endereço = endereço;
		this.cargo = cargo;
		this.salario = salario;
		this.valeAlimentação = valeAlimentação;
		this.valeTransporte = valeTransporte;
		this.valeRefeição = valeRefeição;
		this.filhos = filhos;
		this.cadastroFilhos = cadastroFilhos;
		this.telefone = telefone;
		this.eMail = eMail;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getValeAlimentação() {
		return valeAlimentação;
	}

	public void setValeAlimentação(double valeAlimentação) {
		this.valeAlimentação = valeAlimentação;
	}

	public double getValeTransporte() {
		return valeTransporte;
	}

	public void setValeTransporte(double valeTransporte) {
		this.valeTransporte = valeTransporte;
	}

	public double getValeRefeição() {
		return valeRefeição;
	}

	public void setValeRefeição(double valeRefeição) {
		this.valeRefeição = valeRefeição;
	}

	public int getFilhos() {
		return filhos;
	}

	public void setFilhos(int filhos) {
		this.filhos = filhos;
	}

	public ArrayList<Pessoa> getCadastroFilhos() {
		return cadastroFilhos;
	}

	public void setCadastroFilhos(ArrayList<Pessoa> cadastroFilhos) {
		this.cadastroFilhos = cadastroFilhos;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
