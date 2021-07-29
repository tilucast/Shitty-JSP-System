package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import DAO.LoginRepository;
import DAO.UserRepository;
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
	private UserRepository userRepository = new UserRepository();
    
    public ServletLogin() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if(action != null && action.equals("logout") && !action.isEmpty()) {
			request.getSession().invalidate();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
				
		try {
			
			String login = request.getParameter("login");
			String senha = request.getParameter("password");
			String url = request.getParameter("url");
			Login loginCredentials = new Login(login, senha);
			
			if((login == null && senha == null) || login == null || senha == null) {
				login = "";
				senha = "";
			}
			
			Login currentUser = loginRepository.authenticate(loginCredentials);
			
			if(login.isEmpty() || senha.isEmpty() || currentUser.getId() == null) {
				
				RequestDispatcher redirect = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("Message", "Login and/or password are either incorrect, or were not provided.");
				redirect.forward(request, response);
				return;
			}
			
			
			if(url == null || url.equals("null")) {
				url = "/principal/principal.jsp";
			}
			
			request.getSession().setAttribute("user", currentUser);
			RequestDispatcher redirect = request.getRequestDispatcher("/principal/principal.jsp");
			response.sendRedirect(request.getContextPath() + "/principal/principal.jsp");
			//redirect.forward(request, response);
			
			
			String loggedUserLogin = ((Login) currentUser).getLogin();
			try {
				List<Login> usersFound = userRepository.getAll(loggedUserLogin);
				request.getSession().setAttribute("usersOnTeam", usersFound);
			} catch (SQLException e) {
				e.printStackTrace();
			}
						
			
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
