package tiago.projetos.pj0925.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import tiago.projetos.pj0925.model.Aluno;
import tiago.projetos.pj0925.dao.BancoDados;

public class AlunoDAO {
	
	private BancoDados db = null;
	
	public AlunoDAO() {
		try {
			db = new BancoDados("curso_java");
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	public void cadastrarAluno(Aluno aluno) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO alunos (nome, cpf, sexo, datanascimento, endereço, curso, telefone, email, ativo)");
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt = conn.prepareStatement(sql.toString());	

			stmt.setString(1, String.valueOf(aluno.getNome()));
			stmt.setString(2, String.valueOf(aluno.getCpf()));
			
			String sexo = "";
			if (aluno.getSexo() == 'M'){
				sexo = "Masculino";
			} else {
				sexo = "Feminino";
			}
			
			stmt.setString(3, String.valueOf(sexo));
			java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());
			stmt.setDate(4, d);
			stmt.setString(5, String.valueOf(aluno.getEndereço()));
			stmt.setString(6, String.valueOf(aluno.getCurso()));
			stmt.setString(7, String.valueOf(aluno.getTelefone()));
			stmt.setString(8, String.valueOf(aluno.geteMail()));
			stmt.setInt(9, 1);
			
			stmt.execute();
			conn.commit();
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método cadastrarAluno - rollback");
				}
			}
			System.out.println("Erro no método cadastrarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
		System.out.println("Professor " + aluno.getNome() + " da disciplina " + aluno.getCurso() + " cadastrado com sucesso!");
	}
	
	
	public ArrayList<Aluno> consultarListaAluno() {

		ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT matricula, nome, cpf, sexo, datanascimento, endereço, curso, telefone, email FROM alunos WHERE ativo = 1 ORDER BY matricula ASC";

			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setMatricula(rs.getString(1));
				aluno.setNome(rs.getString(2));
				aluno.setCpf(rs.getString(3));
				char sexo = '0';
				if (rs.getString(4).equals("Masculino")){
					sexo = 'M';
				} else {
					sexo = 'F';
				}
				aluno.setSexo(sexo);
				aluno.setDataNascimento(new Date(rs.getTimestamp("datanascimento").getTime()));
				aluno.setEndereço(rs.getString(6));
				aluno.setCurso(rs.getString(7));
				aluno.setTelefone(rs.getString(8));
				aluno.seteMail(rs.getString(9));
				
				listaAluno.add(aluno);
			}

		} catch (SQLException e) {
			System.out.println("Erro no método consultarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		return listaAluno;
	}
	
	public void alterarAluno(Aluno aluno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE alunos SET nome = ?, cpf = ?, sexo = ?, datanascimento = ?, endereço = ?, curso = ?, telefone = ?, email = ?");
			sql.append("WHERE matricula = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(1, String.valueOf(aluno.getNome()));
			stmt.setString(2, String.valueOf(aluno.getCpf()));
			
			String sexo = "";
			if (aluno.getSexo() == 'M'){
				sexo = "Masculino";
			} else {
				sexo = "Feminino";
			}
			
			stmt.setString(3, String.valueOf(sexo));
			java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());
			stmt.setDate(4, d);
			stmt.setString(5, String.valueOf(aluno.getEndereço()));
			stmt.setString(6, String.valueOf(aluno.getCurso()));
			stmt.setString(7, String.valueOf(aluno.getTelefone()));
			stmt.setString(8, String.valueOf(aluno.geteMail()));
			stmt.setInt(9, Integer.valueOf(aluno.getMatricula()));
			
			stmt.execute();
			conn.commit();
			
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
	
	public void inativarAluno(int matricula) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE alunos SET ativo = ? ");
			sql.append("WHERE matricula = ?;");

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, 0);
			stmt.setInt(2, matricula);
			
			stmt.execute();
			conn.commit();
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método inativarAluno - rollback");
				}
			}
			System.out.println("Erro no método inativarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}	
}
