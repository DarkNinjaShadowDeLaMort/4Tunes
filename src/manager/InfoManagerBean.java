package manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Album;
import model.Artiste;
import model.Genre;

@Stateless
public class InfoManagerBean implements InfoManager {
	@PersistenceContext	
	private EntityManager em;

	@Override
	public List<Genre> getGenres() {
		Query q= em.createQuery("FROM Genre g");
		
		List <Genre> liste = q.getResultList();
		
		return liste;
	}

	@Override
	public void addGenre(String nom, String pere) {
		Genre genre = new Genre();
		genre.setNom(nom);		
		
		if(pere != null && ! pere.equals("")){
			Genre genrePere = getGenreByName(pere);
			genre.setGenre(genrePere);
		}
		
		em.persist(genre);
	}

	@Override
	public Genre getGenreByName(String nom) {
		Query q= em.createQuery("FROM Genre g WHERE g.nom like :pGenre");	
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
	public void addAlbum(String nom, byte[] pochette, Integer date, String artiste) {
		Artiste a = getArtisteByName(artiste);		
		
		Album album = new Album();
		album.setNom(nom);
		album.setPochette(pochette);
		album.setDate(date);
		album.setArtiste(a);
		
		em.persist(album);		
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
	public List <Album> getAlbums() {
		Query q= em.createQuery("FROM Album a");
		
		List <Album> liste = q.getResultList();
		
		return liste;
	}
	
	
}
