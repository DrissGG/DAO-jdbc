package dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSingleton {
	
	private final static String URL = "jdbc:mysql://localhost:3307/dao";
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String USER = "dao";
	private final static String PWD = "dao";
	
	private Connection connection = null;
	private static final JdbcSingleton INSTANCE = new JdbcSingleton(); 
	
	private JdbcSingleton() {
		try {
			Class.forName(DRIVER);
			connection=DriverManager.getConnection(URL,USER,PWD);
			System.out.println("Connexion ouverte.");	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Connection Impossible");
			
		}
	}
	
	public static JdbcSingleton getInstance() {
		return INSTANCE;
	}
	public Connection getConnexion() {
		return connection;
	}

}
