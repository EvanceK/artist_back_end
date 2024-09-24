package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static void main(String[] args) {
		Connection conn = DbConnection.getDb();

		System.out.println(conn);

	}

	public static Connection getDb() {
		Connection conn = null;
		String URI = "jdbc:mysql://localhost:3306/artistproject?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "1234";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URI, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

}
