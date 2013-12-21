

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UtilisateurManager;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet",  urlPatterns = {"/servlet/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UtilisateurManager utilisateurManager;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dans le post de login servlet");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		
		System.out.println("username : "+username);
		System.out.println("password ; "+password);
		
		//utilisateurManager.addUtilisateur(username, password, "flute@truite.com");
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
