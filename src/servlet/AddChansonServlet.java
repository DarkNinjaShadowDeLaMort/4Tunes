package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import manager.InfoManager;
import model.Album;
import model.Artiste;
import model.Genre;

/**
 * Servlet implementation class AddChansonServlet
 */
@WebServlet(name="AddChansonServlet",  urlPatterns = {"/servlet/addChanson"})
@MultipartConfig
public class AddChansonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private InfoManager infoManager;
	
    public AddChansonServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Genre> listeGenre = infoManager.getGenres();
		List <Artiste> listeArtiste = infoManager.getArtistes();
		List <Album> listeAlbum = infoManager.getAlbums();
		
		request.setAttribute("listeGenre", listeGenre);
		request.setAttribute("listeArtiste", listeArtiste);
		request.setAttribute("listeAlbum", listeAlbum);
		
		request.getRequestDispatcher("/add-song.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("fichier");
		String titre = request.getParameter("titre");
		String artiste = request.getParameter("artiste");
		String album = request.getParameter("album");
		String genre = request.getParameter("genre");
		
		System.out.println("fichier : "+part);
		System.out.println("titre : "+titre);
		System.out.println("artiste : "+artiste);
		System.out.println("album : "+album);
		System.out.println("genre : "+genre);
		
		response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
	}

}
