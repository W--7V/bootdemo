package local;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPreparedStatement {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM novel where id = ?");
			prepareStatement.setString(1, "1'");
			System.out.println(prepareStatement.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
