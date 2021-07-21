package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static final String database = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static final String user = "postgres";
	private static final String password = "asbiredebob123";
	private static Connection connection = null;
	
	static {
		connect();
	}
	
	public SingleConnection() {
		connect();
	}
	
	private static void connect() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(database, user, password);
				connection.setAutoCommit(false);
				
				System.out.println("Database connected successfully.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
