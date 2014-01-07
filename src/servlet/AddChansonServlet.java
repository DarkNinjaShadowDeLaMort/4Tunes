package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import manager.InfoManager;
import model.Album;
import model.Artiste;
import model.Chanson;
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
	
	File update_log = null;
	final String fileName = "my_updates.txt";
	
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
		
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("id");
		
		if(titre == null || titre.isEmpty()){
			response.sendRedirect(request.getContextPath()+"/servlet/addChanson?erreur=titre");
		}
		else{
			String name = AddAlbumServlet.getFileName(part);
			if(name == null || name.isEmpty()){
				response.sendRedirect(request.getContextPath()+"/servlet/addChanson?erreur=fichier");
			}
			else{
				String url = AddAlbumServlet.getFileName(part);
				
				System.out.println("url : "+url);
				
				infoManager.addChanson(titre, 0.0f, url, artiste, album, genre, id);

				File file = new File("Musique/"+id+"/"+url);	
				
				inputStreamToFile(part.getInputStream(), file);
				
				response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
			}
		}
		
		
		
	}
	
	@Override
	public void init() throws ServletException {
	    super.init();
	    String file_path = getServletContext().getRealPath(fileName);
	    update_log = new File(file_path);
	    if (!update_log.exists()) {
	        try {
	            update_log.createNewFile();
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Error while creating file : " + fileName);
	        }
	    }
	}

	public synchronized void update_to_file(String userName, String query) {

	    if (update_log != null && update_log.exists()) {
	        FileOutputStream fos = null;
	        try {
	            fos = new FileOutputStream(update_log, true);
	            fos.write((userName+" "+query+"\n").getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (fos != null) {
	                try {
	                    fos.flush();
	                    fos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}
	
	public void inputStreamToFile(InputStream inputStream, File file){
		OutputStream outputStream = null;
	 
		try {	 
			// write the inputStream to a FileOutputStream
			outputStream = 
	                    new FileOutputStream(file);
	 
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
			System.out.println("Done!");
	 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	 
			}
		}
	}

}
