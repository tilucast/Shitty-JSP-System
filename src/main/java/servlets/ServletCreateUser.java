package servlets;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.UserRepository;
import connection.SingleConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Login;

/* 
 * Notes about this Servlet. There is no Filter behind the createUser Route. So, i'm setting and rolling back the connection here.
 * Probably not a very good design, but i don't know how to fix it.
 * 
 * */

@WebServlet(urlPatterns= {"/ServletCreateUser"})
public class ServletCreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRepository userRepository = new UserRepository();
	
	private static Connection connection = SingleConnection.getConnection();

    public ServletCreateUser() {
        
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
			request.setAttribute("Message", e.getLocalizedMessage());
			request.getRequestDispatcher("create-user.jsp").forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}catch(Exception e) {
			
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
