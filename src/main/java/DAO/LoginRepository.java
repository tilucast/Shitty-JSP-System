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
	
	public Login authenticate(Login user) throws SQLException {
		
		String sql = String.format("SELECT * FROM user_info WHERE user_info.login = '%s' AND user_info.password = '%s'", user.getLogin(), user.getPassword());
		PreparedStatement sqlStatement = connection.prepareStatement(sql);
		ResultSet result = sqlStatement.executeQuery();
		Login authenticatedUser = new Login();
		
		if(result.next()) {
			authenticatedUser.setLogin(result.getString(1));
			authenticatedUser.setPassword(result.getString(2));
			authenticatedUser.setId(result.getLong(3));
			authenticatedUser.setEmail(result.getString(4));
		}
		
		return authenticatedUser;
	}
}
