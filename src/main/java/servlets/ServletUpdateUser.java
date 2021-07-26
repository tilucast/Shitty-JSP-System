package servlets;

import java.io.IOException;

import java.sql.SQLException;

import DAO.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Login;


@WebServlet(urlPatterns= {"/principal/ServletUpdateUser"})
public class ServletUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRepository userRepository = new UserRepository();

    public ServletUpdateUser() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am a get request");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String login = request.getParameter("login");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm-password");
			String currentEmail = (String) ((Login) request.getSession().getAttribute("user")).getEmail();
			
			Login createNewUser = new Login(login, email, password);
			
			if(!password.equals(confirmPassword) || password.isEmpty() || confirmPassword.isEmpty()) {
				
				request.setAttribute("Message", "Both password fields should be equal.");
				request.setAttribute("formFieldsInfo", createNewUser);
				request.getRequestDispatcher("/principal/profile.jsp").forward(request, response);
			}
			
			userRepository.update(currentEmail, createNewUser);
			request.getSession().setAttribute("user", createNewUser);
			request.setAttribute("Success", "Informations have been updated successfully.");
			request.getRequestDispatcher("/principal/profile.jsp").forward(request, response);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("Message", e.getLocalizedMessage());
			request.getRequestDispatcher("/principal/profile.jsp").forward(request, response);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("/error.jsp");
			request.setAttribute("Error", "Some unexpected error happened. Try again later.");
			redirect.forward(request, response);
			
		}
		
	}

}
