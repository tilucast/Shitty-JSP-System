package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Login;

@WebServlet(urlPatterns= {"/ServletCreateUser"})
public class ServletCreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRepository userRepository = new UserRepository();
	private Connection connection;

    
    public ServletCreateUser() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String login = request.getParameter("login");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm-password");
			
			Login createNewUser = new Login(login, email, password);
			
			if(!password.equals(confirmPassword) || password.isEmpty() || confirmPassword.isEmpty()) {
				
				request.setAttribute("Message", "Both password fields should be equal.");
				request.setAttribute("formFieldsInfo", createNewUser);
				request.getRequestDispatcher("create-user.jsp").forward(request, response);
			}
			
			userRepository.create(createNewUser);
			request.setAttribute("Success", "User created successfully. Go back and login.");
			request.getRequestDispatcher("create-user.jsp").forward(request, response);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("Message", e.getMessage());
			request.getRequestDispatcher("create-user.jsp").forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("/error.jsp");
			request.setAttribute("Error", "Some unexpected error happened. Try again later.");
			redirect.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
