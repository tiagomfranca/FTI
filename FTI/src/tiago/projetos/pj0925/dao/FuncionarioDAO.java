package tiago.projetos.pj0925.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import tiago.projetos.pj0925.model.Funcionario;
import tiago.projetos.pj0925.model.Pessoa;
import tiago.projetos.pj0925.model.Professor;
import tiago.projetos.pj0925.dao.BancoDados;

public class FuncionarioDAO {
	
	private BancoDados db = null;
	private int codigoAtual;
	
	public FuncionarioDAO() {
		try {
			db = new BancoDados("curso_java");
			codigoAtual = 0;
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	public void cadastrarFuncionario(Funcionario funcionario) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO funcionarios (nome, cpf, sexo, datanascimento, endereço, cargo, disciplina, salario, vale_alimentacao, vale_refeicao, vale_transporte,"
					+ " telefone, email, numero_filhos, ativo)");
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt = conn.prepareStatement(sql.toString());	

			String sexo = "";
			
			if (funcionario.getSexo() == 'M'){sexo = "Masculino";} else {sexo = "Feminino";}
			
			java.sql.Date d = new java.sql.Date(funcionario.getDataNascimento().getTime());
			
			stmt.setString(1, String.valueOf(funcionario.getNome()));
			stmt.setString(2, String.valueOf(funcionario.getCpf()));
			stmt.setString(3, String.valueOf(sexo));
			stmt.setDate(4, d);
			stmt.setString(5, String.valueOf(funcionario.getEndereço()));
			stmt.setString(6, String.valueOf(funcionario.getCargo()));
			
			if (funcionario instanceof Professor){
				stmt.setString(7, String.valueOf(((Professor)funcionario).getDisciplina()));
			} else {
				stmt.setNull(7, Types.NULL);
			}
			
			stmt.setDouble(8, Double.valueOf(funcionario.getSalario()));
			stmt.setDouble(9, Double.valueOf(funcionario.getValeAlimentação()));
			stmt.setDouble(10, Double.valueOf(funcionario.getValeRefeição()));
			stmt.setDouble(11, Double.valueOf(funcionario.getValeTransporte()));
			stmt.setString(12, String.valueOf(funcionario.getTelefone()));
			stmt.setString(13, String.valueOf(funcionario.geteMail()));
			stmt.setInt(14, Integer.valueOf(funcionario.getFilhos()));
			stmt.setInt(15, 1);
			
			stmt.execute();
			conn.commit();
			
			String sqlLastInsert = "SELECT LAST_INSERT_ID()";
			
			db.finalizaObjetos(null, stmt, null);
			
			stmt = conn.prepareStatement(sqlLastInsert);
			
			rs = stmt.executeQuery();

			if (rs.next()) {
				funcionario.setCodCadastro(String.valueOf(rs.getInt(1)));
				registrarFilhos(funcionario);
				db.finalizaObjetos(null, stmt, null);
			}
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método cadastrarFuncionario - rollback");
				}
			}
			System.out.println("Erro no método cadastrarFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
		System.out.println("Funcionário " + funcionario.getNome() + " do cargo " + funcionario.getCargo() + " cadastrado com sucesso!");
	}
	
	private void registrarFilhos(Funcionario funcionario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			String sql = "INSERT INTO filhos (fk_codigo, nome, datanascimento) VALUES (?, ?, ?); ";
			
			stmt = conn.prepareStatement(sql);
			
			for (Pessoa filho : funcionario.getCadastroFilhos()) {
				java.sql.Date d = new java.sql.Date(filho.getDataNascimento().getTime());
				
				stmt.setInt(1, Integer.parseInt(funcionario.getCodCadastro()));
				stmt.setString(2, String.valueOf(filho.getNome()));
				stmt.setDate(3, d);
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();

			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método registrarFilhos - rollback");
				}
			}
			System.out.println("Erro no método registrarFilhos");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}

	public ArrayList<Funcionario> consultarListaFuncinoario() {

		ArrayList<Funcionario> listaFunc = new ArrayList<Funcionario>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT FUNC.codigo, FUNC.nome, FUNC.cpf, FUNC.sexo, FUNC.datanascimento, FUNC.endereço, FUNC.cargo, FUNC.disciplina, FUNC.salario, "
					+ "FUNC.vale_alimentacao, FUNC.vale_refeicao, FUNC.vale_transporte, FUNC.telefone, FUNC.email, FUNC.numero_filhos, FILHO.nome, FILHO.datanascimento"
					+ " FROM funcionarios AS FUNC LEFT JOIN filhos AS FILHO ON FUNC.codigo = FILHO.fk_codigo "
					+ "WHERE FUNC.ativo = 1 ORDER BY codigo ASC";

			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			Funcionario func = new Funcionario();		
			char sexo = '0';
			ArrayList<Pessoa> arrayFilhos = new ArrayList<Pessoa>();
			
			while (rs.next()) {
				if (codigoAtual == rs.getInt(1)) {
					Pessoa filho = new Pessoa();
					filho.setNome(rs.getString(16));
					filho.setDataNascimento(new Date(rs.getTimestamp("FILHO.datanascimento").getTime()));
					arrayFilhos.add(filho);
				} else {
					if (rs.getRow() != 1) {
						func.setCadastroFilhos(arrayFilhos);
						listaFunc.add(func);
					}
					codigoAtual = rs.getInt(1);
					
					if (rs.getString(7).equals("Professor")){func = new Professor();} else {func = new Funcionario();}
					
					if (rs.getString(4).equals("Masculino")){sexo = 'M';} else {sexo = 'F';}
					
					func.setCodCadastro(rs.getString(1));
					func.setNome(rs.getString(2));
					func.setCpf(rs.getString(3));
					func.setSexo(sexo);
					func.setDataNascimento(new Date(rs.getTimestamp("FUNC.datanascimento").getTime()));
					func.setEndereço(rs.getString(6));
					func.setCargo(rs.getString(7));
					
					if (func instanceof Professor){
						((Professor) func).setDisciplina(rs.getString(8));
					}
					
					func.setSalario(rs.getDouble(9));
					func.setValeAlimentação(rs.getDouble(10));
					func.setValeRefeição(rs.getDouble(11));
					func.setValeTransporte(rs.getDouble(12));
					func.setTelefone(rs.getString(13));
					func.seteMail(rs.getString(14));
					func.setFilhos(rs.getInt(15));
					
					arrayFilhos = new ArrayList<Pessoa>();
					
					if (func.getFilhos() > 0) {
						Pessoa filho = new Pessoa();
						filho.setNome(rs.getString(16));
						filho.setDataNascimento(new Date(rs.getTimestamp("FILHO.datanascimento").getTime()));
						arrayFilhos.add(filho);
					}
				}
			}
			rs.beforeFirst();
			if (rs.next()) {
				func.setCadastroFilhos(arrayFilhos);
				listaFunc.add(func);
			}
		} catch (SQLException e) {
			System.out.println("Erro no método consultarFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
			codigoAtual = 0;
		}
		return listaFunc;
	}
	
	public void alterarFuncionario(Funcionario funcionario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE funcionarios SET nome = ?, cpf = ?, sexo = ?, datanascimento = ?, endereço = ?, cargo = ?, disciplina = ?, salario = ?, vale_alimentacao = ?, vale_refeicao = ?, "
					+ "vale_transporte = ?, telefone = ?, email = ?, numero_filhos = ? ");
			sql.append("WHERE codigo = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			String sexo = ""; if (funcionario.getSexo() == 'M'){sexo = "Masculino";} else {sexo = "Feminino";}
			java.sql.Date d = new java.sql.Date(funcionario.getDataNascimento().getTime());
			
			stmt.setString(1, String.valueOf(funcionario.getNome()));
			stmt.setString(2, String.valueOf(funcionario.getCpf()));
			stmt.setString(3, String.valueOf(sexo));
			stmt.setDate(4, d);
			stmt.setString(5, String.valueOf(funcionario.getEndereço()));
			stmt.setString(6, String.valueOf(funcionario.getCargo()));
			
			if (funcionario instanceof Professor){
				stmt.setString(7, String.valueOf(((Professor)funcionario).getDisciplina()));
			} else {
				stmt.setNull(7, Types.NULL);
			}
			
			stmt.setDouble(8, Double.valueOf(funcionario.getSalario()));
			stmt.setDouble(9, Double.valueOf(funcionario.getValeAlimentação()));
			stmt.setDouble(10, Double.valueOf(funcionario.getValeRefeição()));
			stmt.setDouble(11, Double.valueOf(funcionario.getValeTransporte()));
			stmt.setString(12, String.valueOf(funcionario.getTelefone()));
			stmt.setString(13, String.valueOf(funcionario.geteMail()));
			stmt.setInt(14, Integer.valueOf(funcionario.getFilhos()));
			stmt.setInt(15, Integer.valueOf(funcionario.getCodCadastro()));
			
			stmt.execute();
			conn.commit();

			editarFilhos(funcionario);
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método alterarAluno - rollback");
				}
			}
			System.out.println("Erro no método alterarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
	
	private void editarFilhos(Funcionario funcionario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			
			String querry = "DELETE FROM filhos\nWHERE fk_codigo = ?;";
			
			stmt = conn.prepareStatement(querry.toString());
			
			stmt.setInt(1,  Integer.parseInt(funcionario.getCodCadastro()));
			
			stmt.execute();
			conn.commit();
			db.finalizaObjetos(null, stmt, null);
			
			String sql = "INSERT INTO filhos (fk_codigo, nome, datanascimento) VALUES (?, ?, ?); ";
			
			stmt = conn.prepareStatement(sql);
			
			for (Pessoa filho : funcionario.getCadastroFilhos()) {
				java.sql.Date d = new java.sql.Date(filho.getDataNascimento().getTime());
				
				stmt.setInt(1, Integer.parseInt(funcionario.getCodCadastro()));
				stmt.setString(2, String.valueOf(filho.getNome()));
				stmt.setDate(3, d);
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();

			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método registrarFilhos - rollback");
				}
			}
			System.out.println("Erro no método registrarFilhos");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}

	public void inativarFuncionario(int codigo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE funcionarios SET ativo = ? ");
			sql.append("WHERE codigo = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, 0);
			stmt.setInt(2, codigo);
			
			stmt.execute();
			conn.commit();
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método inativarFuncionario - rollback");
				}
			}
			System.out.println("Erro no método inativarFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
}
