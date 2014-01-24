package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.chanson.ChansonManager;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet(name="DeleteServlet",  urlPatterns = {"/servlet/delete"})
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ChansonManager chansonManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("id");
		
		HttpSession session = request.getSession(false);
		if(session == null || sId == null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		else{
			int id = Integer.valueOf(sId);

			chansonManager.deleteChanson(id);

			response.sendRedirect(request.getContextPath()+"/servlet/userAccount");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
