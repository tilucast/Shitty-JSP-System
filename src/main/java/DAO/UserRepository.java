package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.Login;

public class UserRepository {
	
	private Connection connection;
	
	public UserRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean create(Login user) throws SQLException {
		
		String sql = String.format("INSERT INTO user_info (login, email, nickname, password) VALUES('%s', '%s', '%s', '%s')", user.getLogin(), user.getEmail(), user.getNickname(), user.getPassword());
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
			user.setNickname(result.getString(5));
		}
		
		return user;
		
	}
	
	public Login getByLogin(String login) throws SQLException {
		String sql = String.format("SELECT * FROM user_info WHERE user_info.login = '%s'", login);
		PreparedStatement sqlStatement = connection.prepareStatement(sql);
		ResultSet result = sqlStatement.executeQuery();
		Login user = new Login();
		
		if(result.next()) {
			user.setLogin(result.getString(1));
			user.setPassword(result.getString(2));
			user.setId(result.getLong(3));
			user.setEmail(result.getString(4));
			user.setNickname(result.getString(5));
		}
		
		return user;
		
	}
	
	public List<Login> getUsersByNickname(String nickname, String currentUserNickname) throws SQLException {
		String sql = "SELECT * FROM user_info WHERE user_info.nickname like ? AND user_info.nickname != ?";
		PreparedStatement sqlStatement = connection.prepareStatement(sql);
		sqlStatement.setString(1, "%" + nickname + "%");
		sqlStatement.setString(2, currentUserNickname);
		ResultSet result = sqlStatement.executeQuery();
		List<Login> users = new ArrayList<Login>();
		
		while(result.next()) {
			Login user = new Login();
			user.setLogin(result.getString(1));
			user.setPassword(result.getString(2));
			user.setId(result.getLong(3));
			user.setEmail(result.getString(4));
			user.setNickname(result.getString(5));
			
			users.add(user);
		}
		
		return users;
		
	}
	
	public boolean update(String email, Login user) throws SQLException{
		String sql = String.format("UPDATE user_info SET login = '%s', email = '%s', nickname = '%s', password = '%s' WHERE user_info.email = '%s'", user.getLogin(), user.getEmail(), user.getNickname(), user.getPassword(), email);
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
	
	public boolean delete(String email) throws SQLException{
		String sql = String.format("DELETE FROM user_info WHERE user_info.email = '%s'", email);
		PreparedStatement sqlStatement = connection.prepareStatement(sql);
		int query = sqlStatement.executeUpdate();
		connection.commit();
		
		if(query == 1) {
			System.out.println("Query has deleted 1 row(s)");
			return true;
		}
		
		System.out.println("Query has deleted 0 row(s)");
		return false;
	}

}
