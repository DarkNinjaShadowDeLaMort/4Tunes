package servlet;


import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.hibernate.validator.internal.constraintvalidators.EmailValidator;

import manager.UtilisateurManager;

/**
 * Servlet implementation class SubscribeServlet
 */
@WebServlet(name="SubscribeServlet",  urlPatterns = {"/servlet/subscribe"})
public class SubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UtilisateurManager utilisateurManager;
	
    public SubscribeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dans le post de subscribe servlet");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String eMail = (String) request.getParameter("eMail");
		
		System.out.println("username : "+username);
		System.out.println("password : "+password);
		System.out.println("eMail : "+eMail);
		
		if(username == null || username.equals("")){
			response.sendRedirect(request.getContextPath()+"/subscribe.jsp?erreur=username");
		}
		else{
			if(password == null || password.equals("")){
				response.sendRedirect(request.getContextPath()+"/subscribe.jsp?erreur=password");
			}
			else{
				if(eMail == null || eMail.equals("")){
					response.sendRedirect(request.getContextPath()+"/subscribe.jsp?erreur=eMail");
				}
				else{
					
					if(utilisateurManager.existUtilisateur(username)){
						response.sendRedirect(request.getContextPath()+"/subscribe.jsp?erreur=usernameExist");
					}
					else{
						utilisateurManager.addUtilisateur(username, password, eMail);
						
						response.sendRedirect(request.getContextPath()+"/index.jsp?subscribe=ok");
					}
				}
			}
		}
		
		
	}

}
