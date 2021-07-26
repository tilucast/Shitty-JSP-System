package model;

import java.io.Serializable;

public class Login implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String login;
	private String password;
	
	public Login() {
		
	}
	
	public Login(String login, String email, String password) {
		this.login = login;
		this.email = email;
		this.password = password;
	}
	
	public Login(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Login [login=" + login + ", password=" + password + "]";
	}
	
	
	
}
