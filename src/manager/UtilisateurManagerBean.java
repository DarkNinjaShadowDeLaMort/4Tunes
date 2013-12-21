package manager;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Utilisateur;

@Stateless
public class UtilisateurManagerBean implements UtilisateurManager {

	@PersistenceContext	
	private EntityManager em;
	
	@Override
	public void addUtilisateur(String username, String password, String eMail) {
		Utilisateur u = new Utilisateur();
		u.setLogin(username);
		u.setPassword(password);
		u.setEmail(eMail);
		
		em.persist(u);
		em.flush();
	}

	@Override
	public int login(String username, String password) {
		Query q= em.createQuery("FROM Utilisateur u WHERE u.login like :pLogin AND u.password like :pPassword");	
	 	q.setParameter("pLogin",username);
	 	q.setParameter("pPassword", password);
	 
	 	Utilisateur utilisateur = (Utilisateur) q.getSingleResult();
	 	
	 	return 0;
	}

}
