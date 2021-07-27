package model;

import java.io.Serializable;

public class Login implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String login;
	private String password;
	private String nickname;
	
	public Login() {
		
	}
	
	public Login(String login, String email, String nickname, String password) {
		this.login = login;
		this.email = email;
		this.nickname = nickname;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", login=" + login + ", password=" + password + ", nickname="
				+ nickname + "]";
	}
	
}
