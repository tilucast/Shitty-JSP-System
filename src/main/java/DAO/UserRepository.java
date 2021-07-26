package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.Login;

public class UserRepository {
	
	private Connection connection;
	
	public UserRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean create(Login user) throws SQLException {
		
		String sql = String.format("INSERT INTO user_info (login, email, password) VALUES('%s', '%s', '%s')", user.getLogin(), user.getEmail(), user.getPassword());
		PreparedStatement sqlStatement =  connection.prepareStatement(sql);
		int query = sqlStatement.executeUpdate();
		connection.commit();
		
		if(query == 1) {
			System.out.println("Query has created 1 row(s).");
			return true;
		}
		System.out.println("Query has created 0 row(s)");
		return false;
		
	}
	
	public Login get(String email) throws SQLException {
		String sql = String.format("SELECT * FROM user_info WHERE user_info.email = '%s'", email);
		PreparedStatement sqlStatement = connection.prepareStatement(sql);
		ResultSet result = sqlStatement.executeQuery();
		Login user = new Login();
		
		if(result.next()) {
			user.setLogin(result.getString(1));
			user.setPassword(result.getString(2));
			user.setId(result.getLong(3));
			user.setEmail(result.getString(4));
		}
		
		return user;
		
	}
	
	public boolean update(String email, Login user) throws SQLException{
		String sql = String.format("UPDATE user_info SET login = '%s', email = '%s', password = '%s' WHERE user_info.email = '%s'", user.getLogin(), user.getEmail(), user.getPassword(), email);
		PreparedStatement sqlStatement = connection.prepareStatement(sql);
		int query = sqlStatement.executeUpdate();
		connection.commit();
		
		if(query == 1) {
			System.out.println("Query has updated 1 row(s)");
			return true;
		}
		
		System.out.println("Query has updated 0 row(s)");
		return false;
	}

}
