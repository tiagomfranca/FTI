package tiago.projetos.pj0925.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import tiago.projetos.pj0925.dao.*;

public class TesteBanco {

	public static void main(String[] args) {
		System.out.println("***************** Início ROBO TESTE *****************");
		BancoDados db = null;

		try {
			db = new BancoDados("curso_java");//LOCALHOST
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = db.obterConexao();

			String sql = "SELECT * FROM alunos;";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			int i=1;
			while (rs.next()) {
				System.out.println("Nome "+ i++ + " - NOME: " + rs.getString("nome") );
				//stmt.executeUpdate();
				//conn.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		System.out.println("***************** Fim ROBO  TESTE *****************");
	}

}
