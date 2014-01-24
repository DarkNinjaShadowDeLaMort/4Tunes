package manager.genre;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Genre;
@Stateless
public class GenreManagerBean implements GenreManager{
	@PersistenceContext	
	private EntityManager em;
	
	@Override
	public List<Genre> getGenres() {
		Query q= em.createQuery("FROM Genre g ORDER BY g.nom ASC");
		
		List <Genre> liste = q.getResultList();
		
		return liste;
	}


	@Override
	public Genre getGenreByName(String nom) {
		Query q= em.createQuery("FROM Genre g WHERE g.nom like :pGenre ORDER BY g.nom ASC");	
	 	q.setParameter("pGenre",nom);
	 	
	 	List <Genre> liste = (List <Genre>) q.getResultList();
	 	
	 	if(liste.size() == 0){
	 		return null;
	 	}
	 	else{
	 		return liste.get(0);
	 	}
	}


	@Override
	public Genre getGenreById(int id) {
		Query q= em.createQuery("FROM Genre g WHERE g.id = :id ORDER BY g.nom ASC");	
	 	q.setParameter("id",id);
	 	
	 	List <Genre> liste = (List <Genre>) q.getResultList();
	 	
	 	if(liste.size() == 0){
	 		return null;
	 	}
	 	else{
	 		return liste.get(0);
	 	}
	}

}
