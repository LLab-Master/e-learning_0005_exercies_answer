package finalExercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private Connection con;
	private static final String URL = "jdbc:h2:tcp://localhost/~/h2db/employeedb";
	private static final String USER = "sa";
	private static final String PASS = "";

	public Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(URL, USER, PASS);
			} catch (SQLException e) {
				throw new RuntimeException("データベースの接続に失敗しました", e);
			}
		}
		return con;
	}

	public void closeConnection() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("データベースの切断に失敗しました", e);
		}
	}
}
