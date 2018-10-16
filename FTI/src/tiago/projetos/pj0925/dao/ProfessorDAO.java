package tiago.projetos.pj0925.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import tiago.projetos.pj0925.model.Pessoa;
import tiago.projetos.pj0925.model.Professor;
import tiago.projetos.pj0925.dao.BancoDados;

public class ProfessorDAO {
	
	private BancoDados db = null;
	private int codigoAtual;
	
	public ProfessorDAO() {
		try {
			db = new BancoDados("curso_java");
			codigoAtual = 0;
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	public void cadastrarProfessor(Professor prof) {
		
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
			
			if (prof.getSexo() == 'M'){sexo = "Masculino";} else {sexo = "Feminino";}
			
			java.sql.Date d = new java.sql.Date(prof.getDataNascimento().getTime());
			
			stmt.setString(1, String.valueOf(prof.getNome()));
			stmt.setString(2, String.valueOf(prof.getCpf()));
			stmt.setString(3, String.valueOf(sexo));
			stmt.setDate(4, d);
			stmt.setString(5, String.valueOf(prof.getEndereço()));
			stmt.setString(6, String.valueOf(prof.getCargo()));
			stmt.setString(7, String.valueOf(prof.getDisciplina()));
			stmt.setDouble(8, Double.valueOf(prof.getSalario()));
			stmt.setDouble(9, Double.valueOf(prof.getValeAlimentação()));
			stmt.setDouble(10, Double.valueOf(prof.getValeRefeição()));
			stmt.setDouble(11, Double.valueOf(prof.getValeTransporte()));
			stmt.setString(12, String.valueOf(prof.getTelefone()));
			stmt.setString(13, String.valueOf(prof.geteMail()));
			stmt.setInt(14, Integer.valueOf(prof.getFilhos()));
			stmt.setInt(15, 1);
			
			stmt.execute();
			conn.commit();
			
			String sqlLastInsert = "SELECT LAST_INSERT_ID()";
			
			db.finalizaObjetos(null, stmt, null);
			
			stmt = conn.prepareStatement(sqlLastInsert);
			
			rs = stmt.executeQuery();

			if (rs.next()) {
				prof.setCodCadastro(String.valueOf(rs.getInt(1)));
				registrarFilhos(prof);
				db.finalizaObjetos(null, stmt, null);
			}
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método cadastrarProfessor - rollback");
				}
			}
			System.out.println("Erro no método cadastrarProfessor");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
		System.out.println("Professor " + prof.getNome() + " do cargo " + prof.getCargo() + " cadastrado com sucesso!");
	}
	
	private void registrarFilhos(Professor prof) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			String sql = "INSERT INTO filhos (fk_codigo, nome, datanascimento) VALUES (?, ?, ?); ";
			
			stmt = conn.prepareStatement(sql);
			
			for (Pessoa filho : prof.getCadastroFilhos()) {
				java.sql.Date d = new java.sql.Date(filho.getDataNascimento().getTime());
				
				stmt.setInt(1, Integer.parseInt(prof.getCodCadastro()));
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

	public ArrayList<Professor> consultarListaProfessor() {

		ArrayList<Professor> listaProf = new ArrayList<Professor>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT FUNC.codigo, FUNC.nome, FUNC.cpf, FUNC.sexo, FUNC.datanascimento, FUNC.endereço, FUNC.cargo, FUNC.disciplina, FUNC.salario, "
					+ "FUNC.vale_alimentacao, FUNC.vale_refeicao, FUNC.vale_transporte, FUNC.telefone, FUNC.email, FUNC.numero_filhos, FILHO.nome, FILHO.datanascimento"
					+ " FROM funcionarios AS FUNC LEFT JOIN filhos AS FILHO ON FUNC.codigo = FILHO.fk_codigo "
					+ "WHERE FUNC.ativo = 1 AND FUNC.cargo = 'Professor' ORDER BY codigo ASC";

			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			Professor prof = new Professor();		
			char sexo = '0';
			ArrayList<Pessoa> arrayFilhos = new ArrayList<Pessoa>();
			
			while (rs.next()) {
				if (codigoAtual == rs.getInt(1)) {
					if (rs.getInt(15) != 0) {
						Pessoa filho = new Pessoa();
						filho.setNome(rs.getString(16));
						filho.setDataNascimento(new Date(rs.getTimestamp("FILHO.datanascimento").getTime()));
						arrayFilhos.add(filho);
					}
				} else {
					if (rs.getRow() != 1) {
						prof.setCadastroFilhos(arrayFilhos);
						listaProf.add(prof);
					}
					codigoAtual = rs.getInt(1);
					prof = new Professor();
					
					if (rs.getString(4).equals("Masculino")){sexo = 'M';} else {sexo = 'F';}
					
					prof.setCodCadastro(rs.getString(1));
					prof.setNome(rs.getString(2));
					prof.setCpf(rs.getString(3));
					prof.setSexo(sexo);
					prof.setDataNascimento(new Date(rs.getTimestamp("FUNC.datanascimento").getTime()));
					prof.setEndereço(rs.getString(6));
					prof.setCargo(rs.getString(7));
					prof.setDisciplina(rs.getString(8));
					prof.setSalario(rs.getDouble(9));
					prof.setValeAlimentação(rs.getDouble(10));
					prof.setValeRefeição(rs.getDouble(11));
					prof.setValeTransporte(rs.getDouble(12));
					prof.setTelefone(rs.getString(13));
					prof.seteMail(rs.getString(14));
					prof.setFilhos(rs.getInt(15));
					
					arrayFilhos = new ArrayList<Pessoa>();
					
					if (prof.getFilhos() > 0) {
						Pessoa filho = new Pessoa();
						filho.setNome(rs.getString(16));
						filho.setDataNascimento(new Date(rs.getTimestamp("FILHO.datanascimento").getTime()));
						arrayFilhos.add(filho);
					}
				}
			}
			rs.beforeFirst();
			if (rs.next()) {
				prof.setCadastroFilhos(arrayFilhos);
				listaProf.add(prof);
			}
		} catch (SQLException e) {
			System.out.println("Erro no método consultarProf");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
			codigoAtual = 0;
		}
		return listaProf;
	}
	
	public void alterarProfessor(Professor prof) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE funcionarios SET nome = ?, cpf = ?, sexo = ?, datanascimento = ?, endereço = ?, cargo = ?, disciplina = ?, "
					+ "salario = ?, vale_alimentacao = ?, vale_refeicao = ?, vale_transporte = ?, telefone = ?, email = ?, numero_filhos = ? ");
			sql.append("WHERE codigo = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			String sexo = ""; if (prof.getSexo() == 'M'){sexo = "Masculino";} else {sexo = "Feminino";}
			java.sql.Date d = new java.sql.Date(prof.getDataNascimento().getTime());
			
			stmt.setString(1, String.valueOf(prof.getNome()));
			stmt.setString(2, String.valueOf(prof.getCpf()));
			stmt.setString(3, String.valueOf(sexo));
			stmt.setDate(4, d);
			stmt.setString(5, String.valueOf(prof.getEndereço()));
			stmt.setString(6, String.valueOf(prof.getCargo()));
			stmt.setString(7, String.valueOf(prof.getDisciplina()));
			stmt.setDouble(8, Double.valueOf(prof.getSalario()));
			stmt.setDouble(9, Double.valueOf(prof.getValeAlimentação()));
			stmt.setDouble(10, Double.valueOf(prof.getValeRefeição()));
			stmt.setDouble(11, Double.valueOf(prof.getValeTransporte()));
			stmt.setString(12, String.valueOf(prof.getTelefone()));
			stmt.setString(13, String.valueOf(prof.geteMail()));
			stmt.setInt(14, Integer.valueOf(prof.getFilhos()));
			stmt.setInt(15, Integer.valueOf(Integer.parseInt(prof.getCodCadastro())));
			
			stmt.execute();
			conn.commit();
			alterarFilhos(prof);
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método alterarProfessor - rollback");
				}
			}
			System.out.println("Erro no método alterarProfessor");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
	
	private void alterarFilhos(Professor prof) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			
			String querry = "DELETE FROM filhos\nWHERE fk_codigo = ?;";
			
			stmt = conn.prepareStatement(querry);
			
			stmt.setInt(1,  Integer.parseInt(prof.getCodCadastro()));
			
			stmt.execute();
			conn.commit();
			
			db.finalizaObjetos(null, stmt, null);
			
			String sql = "INSERT INTO filhos (fk_codigo, nome, datanascimento) VALUES (?, ?, ?); ";
			
			stmt = conn.prepareStatement(sql);
			
			for (Pessoa filho : prof.getCadastroFilhos()) {
				java.sql.Date d = new java.sql.Date(filho.getDataNascimento().getTime());
				
				stmt.setInt(1, Integer.parseInt(prof.getCodCadastro()));
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

	public void inativarProfessor(int codigo) {
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
					System.out.println("Erro no método inativarProfessor - rollback");
				}
			}
			System.out.println("Erro no método inativarProfessor");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
}
