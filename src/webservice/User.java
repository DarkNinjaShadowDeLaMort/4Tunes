package webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import manager.chanson.ChansonManager;
import manager.utilisateur.UtilisateurManager;
import model.Chanson;

@WebService()
public class User {

	@EJB
	UtilisateurManager utilisateurManager;

	@EJB
	ChansonManager chansonManager;

	@Resource
	WebServiceContext wsContext;

	private HashMap<String, HttpSession> listeSessions;
	private JsonBuilderFactory factory;

	public User() {
		listeSessions = new HashMap<String, HttpSession>();
		System.out.println("Constructeur");
		factory = Json.createBuilderFactory(null);
	}

	@WebMethod()
	public String connexion(@WebParam(name = "login") String login,
			@WebParam(name = "password") String password) {
		System.out.println("login : " + login);
		System.out.println("password : " + password);

		int connexion = utilisateurManager.login(login, password);

		if (connexion != -1) {
			final MessageContext mc = this.wsContext.getMessageContext();
			final ServletRequest sr = (ServletRequest) mc
					.get(MessageContext.SERVLET_REQUEST);
			final HttpServletRequest hsr = (HttpServletRequest) sr;
			HttpSession session = hsr.getSession(true);
			session.setMaxInactiveInterval(30);
			session.setAttribute("login", login);
			session.setAttribute("idBDD", connexion);

			ArrayList<String> listeKeys = new ArrayList<String>();

			for (Entry<String, HttpSession> entry : listeSessions.entrySet()) {
				String key = entry.getKey();
				HttpSession value = entry.getValue();

				try {
					value.getCreationTime();
				} catch (Exception e) {
					listeKeys.add(key);
				}
			}

			for (String key : listeKeys) {
				listeSessions.remove(key);
				System.out.println("Suppression de : " + key);
			}

			listeSessions.put(session.getId(), session);

			System.out.println("size : " + listeSessions.size());

			System.out.println("session id : " + session.getId());

			JsonObject value = factory.createObjectBuilder().add("etat", "OK")
					.add("id", session.getId()).build();

			return value.toString();
		} else {
			JsonObject value = factory.createObjectBuilder().add("etat", "KO")
					.build();

			return value.toString();
		}
	}

	@WebMethod()
	public String getChansons(@WebParam(name = "id") String id) {
		HttpSession session = listeSessions.get(id);
		if (session.getAttribute("login") != null) {
			List<Chanson> listeChansons = chansonManager
					.getChansons((Integer) session.getAttribute("idBDD"));

			JsonArrayBuilder array = factory.createArrayBuilder();
			for (Chanson chanson : listeChansons) {
				JsonObject value = factory.createObjectBuilder()
						.add("titre", chanson.getTitre()).build();
				array.add(value);
			}

			JsonObject value = factory.createObjectBuilder().add("etat", "OK")
					.add("tableau", array).build();

			return value.toString();
		} else {
			if (listeSessions.containsKey(id)) {
				listeSessions.remove(id);
			}
			JsonObject value = factory.createObjectBuilder().add("etat", "KO")
					.build();

			return value.toString();
		}
	}

	@WebMethod()
	public String deconnexion(@WebParam(name = "id") String id) {
		System.out.println("id : " + id);
		if (listeSessions.containsKey(id)) {
			listeSessions.remove(id);
		}

		JsonObject value = factory.createObjectBuilder().add("etat", "OK")
				.build();

		return value.toString();
	}
}