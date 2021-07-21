package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.Login;

public class LoginRepository {

	private Connection connection;
	
	public LoginRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean authenticate(Login user) throws SQLException {
		
		String sql = String.format("SELECT * FROM user_info WHERE user_info.login = '%s' AND user_info.password = '%s'", user.getLogin(), user.getPassword());
		PreparedStatement sqlStatement = connection.prepareStatement(sql);
		ResultSet result = sqlStatement.executeQuery();
		
		if(result.next()) {
			return true;
		}
		
		return false;
	}
}
