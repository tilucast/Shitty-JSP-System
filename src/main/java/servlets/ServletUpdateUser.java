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


/* 
 * This Servlet is handling deleting and updating the current logged user, even though it shouldn't.
 * 
 * */

@WebServlet(urlPatterns= {"/principal/ServletUpdateUser"})
public class ServletUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRepository userRepository = new UserRepository();

    public ServletUpdateUser() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("delete-account");
		String email = ((Login) request.getSession().getAttribute("user")).getEmail();
		
		if(action.equals("delete-account") && !action.isEmpty()) {
			try {
				userRepository.delete(email);
				request.getSession().setAttribute("user", null);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} catch (SQLException e) {
				
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 
		 * Current code adapted to handle fetch request from the browser. To change to the normal flow state (redirecting the user, refresh the current page, etc) comment every .getWriter() and uncomment the .getRequestDispatcher, and change the html form to use the button with type submit instead.
		 * 
		 * */
		
		try {
			String login = request.getParameter("login");
			String email = request.getParameter("email");
			String nickname = request.getParameter("nickname");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm-password");
			String currentEmail = (String) ((Login) request.getSession().getAttribute("user")).getEmail();
			Login createNewUser = new Login(login, email, nickname, password);
			
			if(!password.equals(confirmPassword) || password.isEmpty() || confirmPassword.isEmpty()) {
				
				request.setAttribute("Message", "Both password fields should be equal.");
				request.setAttribute("formFieldsInfo", createNewUser);
				response.setStatus(400);
				//response.getWriter().write("Both password fields should be equal.");
				request.getRequestDispatcher("/principal/profile.jsp").forward(request, response);
				return;
			}
			
			userRepository.update(currentEmail, createNewUser);
			request.getSession().setAttribute("user", createNewUser);
			request.setAttribute("Success", "Informations have been updated successfully.");
			//response.getWriter().write("Informations have been updated successfully.");
			request.getRequestDispatcher("/principal/profile.jsp").forward(request, response);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("Message", e.getLocalizedMessage());
			//response.getWriter().write(e.getLocalizedMessage());
			//response.setStatus(400);
			request.getRequestDispatcher("/principal/profile.jsp").forward(request, response);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("/error.jsp");
			request.setAttribute("Error", "Some unexpected error happened. Try again later.");
			redirect.forward(request, response);
			
		}
		
	}

}
