package manager.utilisateur;


import java.util.List;

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
	}

	@Override
	public int login(String username, String password) {
		Query q= em.createQuery("FROM Utilisateur u WHERE u.login like :pLogin AND u.password like :pPassword");	
	 	q.setParameter("pLogin",username);
	 	q.setParameter("pPassword", password);
	 
	 	List<Utilisateur> utilisateur = (List <Utilisateur>) q.getResultList();
	 	
	 	if(utilisateur.size() == 0){
	 		return -1;
	 	}
	 	else{
	 		return utilisateur.get(0).getId();
	 	}
	 	
	}
	
	@Override
	public boolean existUtilisateur(String login){
		Query q= em.createQuery("FROM Utilisateur u WHERE u.login like :pLogin");
		q.setParameter("pLogin",login);
		
		List <Object> liste = q.getResultList();
		
		if(liste.size() != 0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Utilisateur getUtilisateur(int id) {
		return em.find(Utilisateur.class, id);
	}

}
