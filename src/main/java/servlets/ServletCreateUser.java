package servlets;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

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
 * Also, this servlet is handling fetching users too.
 * */

@WebServlet(urlPatterns= {"/ServletCreateUser"})
public class ServletCreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRepository userRepository = new UserRepository();
	
	private static Connection connection = SingleConnection.getConnection();

    public ServletCreateUser() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("fetch-users");
		String nickname = request.getParameter("nickname");
		
		String currentUserNickname = ((Login) request.getSession().getAttribute("user")).getNickname();
		
		if(action.equals("fetch-users") && !action.isEmpty()) {
			try {
				
				List<Login> users = userRepository.getUsersByNickname(nickname, currentUserNickname);
				ObjectMapper objectMapper = new ObjectMapper();
				
			      try {
			         String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
			         response.getWriter().write(json);
			      } catch(Exception e) {
			         e.printStackTrace();
			      }
				
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String login = request.getParameter("login");
			String email = request.getParameter("email");
			String nickname = request.getParameter("nickname");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm-password");
			
			Login createNewUser = new Login(login, email, nickname, password);
			
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
