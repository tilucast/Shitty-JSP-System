package Filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnection;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Login;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAuthenticate implements Filter {
	
	private static Connection connection;
	
   
    public FilterAuthenticate() {
    	
    }

	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			Login loggedUser = (Login) session.getAttribute("user");
			String currentUrl = req.getServletPath();
			
			if(loggedUser == null && !currentUrl.contains("/principal/ServletLogin")) {
				RequestDispatcher redirect = request.getRequestDispatcher("/index.jsp?url=" + currentUrl);
				request.setAttribute("Message", "Please, login before accessing the system.");
				redirect.forward(request, response);
				return;
			}
				
			chain.doFilter(request, response);
			
			connection.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConnection();
	}

}
