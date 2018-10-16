package tiago.projetos.pj0925.model;

import java.util.ArrayList;
import java.util.Date;

public class Funcionario extends Pessoa {
	private String codCadastro, endereço, cargo, telefone, eMail;
	private double salario, valeAlimentacao, valeTransporte, valeRefeicao;
	private int filhos;
	private ArrayList<Pessoa> cadastroFilhos;
	
	public Funcionario(){}
	
	public Funcionario(String codCadastro, String nome, String cpf, Date dataNascimento, String endereço, char sexo, String cargo, double salario,
			double valeAlimentacao, double valeTransporte, double valeRefeicao, int filhos, ArrayList<Pessoa> cadastroFilhos,
			String telefone, String eMail){
		super(nome, cpf, dataNascimento, sexo);
		this.codCadastro = codCadastro;
		this.endereço = endereço;
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
		return valeAlimentacao;
	}

	public void setValeAlimentação(double valeAlimentação) {
		this.valeAlimentacao = valeAlimentação;
	}

	public double getValeTransporte() {
		return valeTransporte;
	}

	public void setValeTransporte(double valeTransporte) {
		this.valeTransporte = valeTransporte;
	}

	public double getValeRefeição() {
		return valeRefeicao;
	}

	public void setValeRefeição(double valeRefeição) {
		this.valeRefeicao = valeRefeição;
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
