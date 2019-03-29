package br.com.caleum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexoes {

	public Connection getConnetion() throws ClassNotFoundException  {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21", "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
