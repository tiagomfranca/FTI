package tiago.projetos.pj0925.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import tiago.projetos.pj0925.model.Professor;
import tiago.projetos.pj0925.dao.BancoDados;

public class ProfessorDAO {
	
	private BancoDados db = null;
	
	public ProfessorDAO() {
		try {
			db = new BancoDados("curso_java");
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	public void cadastrarProfessor(Professor professor) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO  ");
			sql.append("VALUES(?,?)");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, String.valueOf(professor.getCpf()));

			stmt.execute();
			conn.commit();
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
		
		System.out.println("Professor " + professor.getNome() + " da disciplina " + professor.getDisciplina() + " cadastrado com sucesso!");
	}
	
	
	public List<Professor> consultarListaProfessor() {

		List<Professor> listaProfessor = new ArrayList<Professor>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT  "
					+ "FROM  "
					+ "ORDER BY nivel ASC";

			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Professor professor = new Professor();
				listaProfessor.add(professor);
			}

		} catch (SQLException e) {
			System.out.println("Erro no método consultarListaProfessor");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		return listaProfessor;
	}
	
	public void alterarProfessor(Professor professor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE nome_tabela SET nome_campo = ? ");
			sql.append("WHERE nome_campo_id = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			//stmt.setInt(1, professor.getId());

			stmt.execute();
			conn.commit();
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
	
}
