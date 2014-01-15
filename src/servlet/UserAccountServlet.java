package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.chanson.ChansonManager;
import manager.utilisateur.UtilisateurManager;
import model.Chanson;
import model.Utilisateur;

/**
 * Servlet implementation class UserAccountServlet
 */
@WebServlet(name="UserAccountServlet",  urlPatterns = {"/servlet/userAccount"})
public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UtilisateurManager utilisateurManager;
	
	@EJB
	private ChansonManager chansonManager;
    
    public UserAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		else{
			int id = (int) session.getAttribute("id");
			
			Utilisateur utilisateur = utilisateurManager.getUtilisateur(id);

			System.out.println("id de l'utilisateur : "+id);
			System.out.println("utilisateur : "+utilisateur);
			
			//List <Chanson> listeChanson = chansonManager.getChansons(id);
			List <Chanson> listeChanson = utilisateur.getChansons();
			System.out.println("listeChanson : "+listeChanson);
						
			request.setAttribute("listeChanson", listeChanson);
			
			System.out.println("dispatcher : "+request.getRequestDispatcher("/user-account.jsp"));
			System.out.println("request : "+request);
			System.out.println("response : "+response);
			
			request.getRequestDispatcher("/user-account.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
