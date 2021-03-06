package tiago.projetos.pj0925.model;

import java.util.ArrayList;
import java.util.Date;

public class Funcionario extends Pessoa {
	private String codCadastro, endere�o, cargo, telefone, eMail;
	private double salario, valeAlimentacao, valeTransporte, valeRefeicao;
	private int filhos;
	private ArrayList<Pessoa> cadastroFilhos;
	
	public Funcionario(){}
	
	public Funcionario(String codCadastro, String nome, String cpf, Date dataNascimento, String endere�o, char sexo, String cargo, double salario,
			double valeAlimentacao, double valeTransporte, double valeRefeicao, int filhos, ArrayList<Pessoa> cadastroFilhos,
			String telefone, String eMail){
		super(nome, cpf, dataNascimento, sexo);
		this.codCadastro = codCadastro;
		this.endere�o = endere�o;
		this.cargo = cargo;
		this.salario = salario;
		this.valeAlimentacao = valeAlimentacao;
		this.valeTransporte = valeTransporte;
		this.valeRefeicao = valeRefeicao;
		this.filhos = filhos;
		this.cadastroFilhos = cadastroFilhos;
		this.telefone = telefone;
		this.eMail = eMail;
	}

	public String getCodCadastro() {
		return codCadastro;
	}

	public void setCodCadastro(String codCadastro) {
		this.codCadastro = codCadastro;
	}

	public String getEndere�o() {
		return endere�o;
	}

	public void setEndere�o(String endere�o) {
		this.endere�o = endere�o;
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

	public double getValeAlimenta��o() {
		return valeAlimentacao;
	}

	public void setValeAlimenta��o(double valeAlimenta��o) {
		this.valeAlimentacao = valeAlimenta��o;
	}

	public double getValeTransporte() {
		return valeTransporte;
	}

	public void setValeTransporte(double valeTransporte) {
		this.valeTransporte = valeTransporte;
	}

	public double getValeRefei��o() {
		return valeRefeicao;
	}

	public void setValeRefei��o(double valeRefei��o) {
		this.valeRefeicao = valeRefei��o;
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
