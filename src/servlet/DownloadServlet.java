package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import manager.artiste.ArtisteManager;
import manager.chanson.ChansonManager;
import model.Chanson;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet(name="DownloadServlet",  urlPatterns = {"/servlet/download"})
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ChansonManager chansonManager;
       
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		String idChanson = request.getParameter("id");
		
		if(idChanson != null){
			HttpSession session = request.getSession(false);
			int id = (int) session.getAttribute("id");
			
			int idC = Integer.valueOf(idChanson);
			
			Chanson chanson = chansonManager.getChanson(idC);
			
			response.setContentType("application/octet-stream");
			File f = new File("Musique/"+id+"/"+chanson.getUrl());
			response.setContentLength((int) f.length());
			
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");
	        
	        try{
				InputStream is = new FileInputStream(f);
				IOUtils.copy(is,response.getOutputStream());
				
				is.close();
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
			/*
			OutputStream out = response.getOutputStream();
			 int nextChar;
			 while (( nextChar = is.read()) != -1){
				 out.write(nextChar);
			 }
			 out.flush();
			 */
		}
				
		
		//response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void service(ServletRequest request, ServletResponse response)throws ServletException, IOException{
		 super.service(request,  response);
	 }
}
