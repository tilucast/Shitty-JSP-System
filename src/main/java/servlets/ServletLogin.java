package servlets;

import java.io.IOException;
import java.sql.SQLException;

import DAO.LoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Login;

@WebServlet(urlPatterns = {"/principal/ServletLogin","/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginRepository loginRepository = new LoginRepository();
    
    public ServletLogin() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Get request");
		request.getRequestDispatcher("/principal/principal.jsp").forward(request, response);
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			
			String login = request.getParameter("login");
			String senha = request.getParameter("password");
			String url = request.getParameter("url");
			Login loginCredentials = new Login(login, senha);
			
			if(login.isEmpty() || senha.isEmpty() || !loginRepository.authenticate(loginCredentials)) {
				
				RequestDispatcher redirect = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("Message", "Login and/or password are either incorrect, or were not provided.");
				redirect.forward(request, response);
				return;
			}
			
			
			if(url == null || url.equals("null")) {
				url = "/principal/principal.jsp";
			}
			
			request.getSession().setAttribute("user", loginCredentials);
			RequestDispatcher redirect = request.getRequestDispatcher(url);
			redirect.forward(request, response);
			
			
		}catch(SQLException throwable) {
			throwable.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("/error.jsp");
			request.setAttribute("Error", "Some unexpected error happened. Try again later.");
			redirect.forward(request, response);
		}
		
		
	}

}
