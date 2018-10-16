package tiago.projetos.pj0925.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;


public class BancoDados {

    public static boolean isFinalizacao = true;

    /**
     * Objeto <i>DataSource</i> que possui a conexão.
     */
    private DataSource dataSource = null;

    public BancoDados(String nome) throws NamingException {
        
    	if (nome.equalsIgnoreCase("curso_java")) {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setServerName("localhost");
            ds.setPortNumber(3306);
            ds.setDatabaseName("curso_java?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false");
            ds.setUser("Tiago");
            ds.setPassword("ftiago");
            dataSource = ds;
        }
    }

    /**
     * Método que obtem a conexão com o banco de dados
     *
     * @return Objeto <i>Connection</i>.
     * @throws SQLException Erro ao obter conexão
     */
    public Connection obterConexao() throws SQLException {

        Connection conn = null;

        try {

            conn = dataSource.getConnection();

        } catch (SQLException slqE) {
            StringBuffer info = new StringBuffer();
            info.append("\n ErrorCode: " + slqE.getErrorCode());
            info.append("\n LocalizedMessage:" + slqE.getLocalizedMessage());
            info.append("\n Message: " + slqE.getMessage());
            info.append("\n SQLState: " + slqE.getSQLState());
            System.out.println(info.toString());
        	this.finalizaObjetos(null, null, conn);
            conn = null;
            throw slqE;
        }
        
        // TODO usado localmente, desabilitar em producao.
//       mudarSQLMode(conn);
        
        mudarTimeZone(conn);
        return conn;
    }

    private void mudarSQLMode(Connection conn) {
    	
		Statement stmt = null;

		try {

			stmt = conn.createStatement();
			stmt.executeQuery("SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));");

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println(e.getMessage());
		} 
	}
    
    private void mudarTimeZone(Connection conn) {
    	
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			stmt.execute("set @@global.time_zone = '-2:00';");
			stmt.close();
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
			System.out.println(e.getMessage());
		} 
	}

	/**
     * Método responsável por fechar objetos.
     *
     * @param rs ResultSet
     * @param stmt PreparedStatement
     * @param conn Connection
     */
    public void finalizaObjetos(ResultSet rs, PreparedStatement stmt, Connection conn) {
        if (isFinalizacao) {
        	try {
        		if (rs != null) {
        			rs.close();
        			rs = null;
        		}
        	} catch (SQLException e) {
				System.out.println(e.getMessage());
        	}
        	try {
        		if (stmt != null) {
        			stmt.close();
        			stmt = null;
        		}
        	} catch (SQLException e) {
				System.out.println(e.getMessage());
        	}
        	try {
        		if (conn != null && !conn.isClosed()) {
        			conn.close();
        			conn = null;
        		}
        	} catch (SQLException e) {
				System.out.println(e.getMessage());
        	}
        }
    }

    /**
     * Obtem o ultimo ID criado.
     *
     * @param rs ResultSet
     * @param stmt Statement
     * @param conn Connection
     * @return id
     * @throws SQLException Erro
     */
    public int getLastId(ResultSet rs, PreparedStatement stmt, Connection conn) throws SQLException {
        int id = 0;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT LAST_INSERT_ID()");

        stmt = conn.prepareStatement(sql.toString());
        rs = stmt.executeQuery();

        while (rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }
}
