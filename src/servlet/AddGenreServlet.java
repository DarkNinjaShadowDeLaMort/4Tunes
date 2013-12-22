package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.InfoManager;
import model.Genre;

/**
 * Servlet implementation class AddGenreServlet
 */
@WebServlet(name="AddGenreServlet",  urlPatterns = {"/servlet/addGenre"})
public class AddGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private InfoManager infoManager;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGenreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Genre> listeGenre = infoManager.getGenres();
		
		request.setAttribute("listeGenre", listeGenre);
		request.getRequestDispatcher("/add-genre.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = (String) request.getParameter("genre");
		String pere = (String) request.getParameter("pere");
		
		System.out.println("genre ; "+nom);
		System.out.println("pere : "+pere);
		
		if(nom.equals("")){
			response.sendRedirect(request.getContextPath()+"/servlet/addGenre?erreur=nom");
		}
		else{
			
			if(pere.equals("Aucun")){
				pere = "";
			}
			
			infoManager.addGenre(nom, pere);
			
			//request.getRequestDispatcher("/user-account.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
		}
	}

}
