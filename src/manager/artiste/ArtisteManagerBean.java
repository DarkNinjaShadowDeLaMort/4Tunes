package manager.artiste;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Artiste;

@Stateless
public class ArtisteManagerBean implements ArtisteManager {
	@PersistenceContext	
	private EntityManager em;
	
	@Override
	public boolean artisteExist(String nom) {
		Query q= em.createQuery("FROM Artiste a WHERE a.nom like :pNom");	
	 	q.setParameter("pNom",nom);
	 	
	 	List liste = q.getResultList();
	 	
	 	if(liste.size() == 0){
	 		return false;
	 	}
	 	else{
	 		return true;
	 	}
	 	
	 	
	}

	@Override
	public void addArtiste(String nom) {
		Artiste artiste = new Artiste();
		artiste.setNom(nom);
		
		em.persist(artiste);
	}

	@Override
	public List<Artiste> getArtistes() {
		Query q= em.createQuery("FROM Artiste a");
		
		List <Artiste> liste = q.getResultList();
		
		return liste;
	}

	@Override
	public Artiste getArtisteByName(String nom) {
		Query q= em.createQuery("FROM Artiste a WHERE a.nom like :pNom");	
	 	q.setParameter("pNom",nom);
	 	
	 	List <Artiste> liste = (List <Artiste>) q.getResultList();
	 	
	 	if(liste.size() == 0){
	 		return null;
	 	}
	 	else{
	 		return liste.get(0);
	 	}
	}

	@Override
	public Artiste getArtisteById(int id) {
		Query q= em.createQuery("FROM Artiste a WHERE a.id = :id");	
	 	q.setParameter("id",id);
	 	
	 	List <Artiste> liste = (List <Artiste>) q.getResultList();
	 	
	 	if(liste.size() == 0){
	 		return null;
	 	}
	 	else{
	 		return liste.get(0);
	 	}
	}

	@Override
	public List<Artiste> getArtistesByIdUser(int idUser) {
		//Query q= em.createQuery("SELECT DISTINCT nom FROM Artiste a, Chanson c WHERE a.id=c.artisteId AND c.utilisateurId=:idUser");
		Query q= em.createQuery("FROM Artiste a, Chanson c WHERE a.id=c.artisteId AND c.utilisateurId=:idUser");
		q.setParameter("idUser", idUser);
		
		List <Artiste> liste = q.getResultList();
		Set<Artiste> set = new HashSet<Artiste>();
		set.addAll(liste);
		liste.clear();
		liste.addAll(set);
		
		return liste;
	}
}
