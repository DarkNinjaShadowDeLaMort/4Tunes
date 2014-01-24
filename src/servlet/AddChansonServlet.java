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
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import manager.album.AlbumManager;
import manager.artiste.ArtisteManager;
import manager.chanson.ChansonManager;
import manager.genre.GenreManager;
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
	private ArtisteManager artisteManager;
	
	@EJB
	private AlbumManager albumManager;
	
	@EJB 
	private GenreManager genreManager;
	
	@EJB
	private ChansonManager chansonManager;
	
	File update_log = null;
	final String fileName = "my_updates.txt";
	
    public AddChansonServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Genre> listeGenre = genreManager.getGenres();
		List <Artiste> listeArtiste = artisteManager.getArtistes();
		List <Album> listeAlbum = albumManager.getAlbums();
		
		request.setAttribute("listeGenre", listeGenre);
		request.setAttribute("listeArtiste", listeArtiste);
		request.setAttribute("listeAlbum", listeAlbum);
		
		request.getRequestDispatcher("/add-song.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("fichier");
		String titre = request.getParameter("titre");
		int idArtiste = Integer.parseInt(request.getParameter("artiste"));
		int idAlbum = Integer.parseInt(request.getParameter("album"));
		int idGenre = Integer.parseInt(request.getParameter("genre"));
		
		System.out.println("fichier : "+part);
		System.out.println("titre : "+titre);
		System.out.println("idArtiste : "+idArtiste);
		System.out.println("idAlbum : "+idAlbum);
		System.out.println("idGenre : "+idGenre);
		
		HttpSession session = request.getSession(false);
		if(session != null){
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

					File file = new File("Musique/"+id+"/"+url);	

					inputStreamToFile(part.getInputStream(), file);
					
					double durationInSeconds = 0;
					try{
						AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
						AudioFormat format = audioInputStream.getFormat();
						long frames = audioInputStream.getFrameLength();
						durationInSeconds = (frames+0.0) / format.getFrameRate();  
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					chansonManager.addChanson(titre, (float)durationInSeconds, url, idArtiste, idAlbum, idGenre, id);
					
					// Test
					response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
				}
			}
		}
		else{
			response.sendRedirect(request.getContextPath()+"/index.jsp");
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
	
	
	public static void inputStreamToFile(InputStream inputStream, File file){
		OutputStream outputStream = null;
	 
		try {	 
			// write the inputStream to a FileOutputStream
			outputStream = new FileOutputStream(file);
	 
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
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
