package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;
import model.Login;

public class UserRepository {
	
	private Connection connection;
	
	public UserRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean create(Login user) throws SQLException {
		
		int query = 0;
		
		String sql = String.format("INSERT INTO user_info (login, email, password) VALUES('%s', '%s', '%s')", user.getLogin(), user.getEmail(), user.getPassword());
		PreparedStatement sqlStatement =  connection.prepareStatement(sql);
		query = sqlStatement.executeUpdate();
		connection.commit();
		
		if(query == 1) {
			System.out.println("Query has created 1 row(s).");
			return true;
		}
		System.out.println("Query has created 0 row(s)");
		return false;
		
	}

}
