package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.artiste.ArtisteManager;

/**
 * Servlet implementation class AddArtisteServlet
 */
@WebServlet(name="AddArtisteServlet",  urlPatterns = {"/servlet/addArtiste"})
public class AddArtisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private ArtisteManager artisteManager;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddArtisteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/add-artiste.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = (String) request.getParameter("nom");
		
		if(nom.equals("")){
			response.sendRedirect(request.getContextPath()+"/servlet/addArtiste?erreur=nom");
		}
		else{
			if(artisteManager.artisteExist(nom)){
				response.sendRedirect(request.getContextPath()+"/servlet/addArtiste?erreur=nomExist");
			}
			else{
				artisteManager.addArtiste(nom);
				response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
			}
		}
	}

}
