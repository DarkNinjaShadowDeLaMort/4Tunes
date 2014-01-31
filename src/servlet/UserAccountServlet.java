package servlet;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.album.AlbumManager;
import manager.artiste.ArtisteManager;
import manager.chanson.ChansonManager;
import manager.genre.GenreManager;
import manager.utilisateur.UtilisateurManager;
import model.Album;
import model.Artiste;
import model.Chanson;
import model.Genre;
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
	
	@EJB
	private AlbumManager albumManager;
	
	@EJB
	private ArtisteManager artisteManager;
	
	@EJB
	private GenreManager genreManager;
    
    public UserAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else {
			int idUser = (int) session.getAttribute("id");
			
			List <Chanson> listeChanson = chansonManager.getChansons(idUser);
					
			if(listeChanson != null) {
				request.setAttribute("listeChanson", listeChanson);
			}

			request.setAttribute("listeArtiste", artisteManager.getArtistesByIdUser(idUser));
			request.setAttribute("listeAlbum", albumManager.getAlbumsByIdUser(idUser));
			
			request.setAttribute("selectedArtiste", new Integer(-1));
			request.setAttribute("selectedAlbum", new Integer(-1));
			
			request.getRequestDispatcher("/user-account.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else {
			int idUser = (int) session.getAttribute("id");
			
			List<Chanson> listeChanson = null;
			List<Artiste> listArtiste = null;
			List<Album> listAlbum = null;
			
			listArtiste = artisteManager.getArtistesByIdUser(idUser);
			
			if(request.getParameter("artiste") != null && request.getParameter("album") != null) {
				int idArtiste = Integer.parseInt(request.getParameter("artiste"));
				int idAlbum = Integer.parseInt(request.getParameter("album"));
				
				if(idArtiste != -1) {
					listAlbum = albumManager.getAlbumsByArtisteAndUser(idArtiste, idUser);
					listeChanson = chansonManager.getChansonsByArtiste(idUser, idArtiste);
				} else {
					listAlbum = albumManager.getAlbumsByIdUser(idUser);
					listeChanson = chansonManager.getChansons(idUser);
				}
				
				if(idAlbum != -1) {
					listeChanson = chansonManager.getChansonsByAlbum(idUser, idAlbum);
				}
				
				request.setAttribute("selectedArtiste", idArtiste);
				request.setAttribute("selectedAlbum", idAlbum);

				request.setAttribute("listeChanson", listeChanson);
				request.setAttribute("listeArtiste", listArtiste);
			} else {

				listeChanson = chansonManager.getChansons(idUser);
			}
			
			request.setAttribute("listeAlbum", listAlbum);
			
			request.getRequestDispatcher("/user-account.jsp").forward(request, response);
		}
	}

}
