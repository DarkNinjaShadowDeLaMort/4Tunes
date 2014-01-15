package servlet;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.utilisateur.UtilisateurManager;
import model.Chanson;
import model.Utilisateur;



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
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TEST");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dans le post de login servlet");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		
		System.out.println("username : "+username);
		System.out.println("password ; "+password);
		
		int userId = utilisateurManager.login(username, password);
		if(userId == -1){
			response.sendRedirect(request.getContextPath()+"/index.jsp?erreur=identification");
		}
		else{
			System.out.println("Id de l'utilisateur : "+userId);
						
			HttpSession session = request.getSession(true);
			int timeout = 1800;
			session.setMaxInactiveInterval(timeout);
			session.setAttribute("id", userId);
			
			// Cr�er le dossier Musique si il n'existe pas
		    File file = new File("Musique/"+userId+"/");
			if(!file.exists()){
				file.mkdirs();
			}
			
			response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
			//request.getRequestDispatcher("/servlet/userAccount").forward(request, response);
		}
		
		
	}

}
