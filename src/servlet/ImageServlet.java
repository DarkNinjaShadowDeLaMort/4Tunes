package servlet;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import manager.album.AlbumManager;
import model.Album;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet(name="ImageServlet",  urlPatterns = {"/servlet/image"})
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AlbumManager albumManager;
       
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Album album = albumManager.getAlbumById(id);
		byte[] pochette = album.getPochette();
		
		ByteArrayInputStream bis = new ByteArrayInputStream(pochette);
		
		InputStream input = bis;
		response.setContentType("image/jpeg");
		
		 try{
				IOUtils.copy(bis,response.getOutputStream());
				
				bis.close();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
