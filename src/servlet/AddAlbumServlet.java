package servlet;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import manager.album.AlbumManager;
import manager.artiste.ArtisteManager;
import model.Artiste;
import sun.misc.IOUtils;

/**
 * Servlet implementation class AddAlbum
 */
@WebServlet(name="AddAlbumServlet",  urlPatterns = {"/servlet/addAlbum"})
@MultipartConfig
public class AddAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB 
	private ArtisteManager artisteManager;
	
	@EJB 
	private AlbumManager albumManager;
	
    public AddAlbumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Artiste> listeArtiste = artisteManager.getArtistes();
		request.setAttribute("listeArtiste", listeArtiste);
		
		request.getRequestDispatcher("/add-album.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part part = request.getPart("pochette");
	    String fileName = getFileName( part );
	    
	    byte[] pochetteArray = null;
	    if(fileName != null && ! fileName.isEmpty()){
		    InputStream is = part.getInputStream();

		    BufferedImage originalImage = ImageIO.read(is);
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ImageIO.write( originalImage, "jpg", baos );
		    baos.flush();
		    pochetteArray = baos.toByteArray();
		    baos.close();
	    }
	    
		String nom = (String) request.getParameter("nom");
		String date = request.getParameter("date");
		String artiste = request.getParameter("artiste");
		Integer iDate = null;
		
		if(nom == null || nom.equals("")){
			response.sendRedirect(request.getContextPath()+"/servlet/addAlbum?erreur=nom");
		}
		else{
			if(date != null && ! date.isEmpty()){
				if(!isInteger(date)){
					response.sendRedirect(request.getContextPath()+"/servlet/addAlbum?erreur=date");
					return;
				}
				else{
					iDate = Integer.valueOf(date);
				}
			}
			if(artiste == null || artiste.isEmpty() || artiste.equals("Aucun")){
				response.sendRedirect(request.getContextPath()+"/servlet/addAlbum?erreur=artiste");
			}
			else{
				albumManager.addAlbum(nom, pochetteArray, iDate, artiste);
				response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
			}
		}
	}
	
	public static String getFileName( Part part ) {
	    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	        if ( contentDisposition.trim().startsWith("filename") ) {
	            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	        }
	    }
	    return null;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
}
