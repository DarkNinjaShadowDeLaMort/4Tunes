package manager.album;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import manager.artiste.ArtisteManager;
import model.Album;
import model.Artiste;

@Stateless
public class AlbumManagerBean implements AlbumManager {
	@PersistenceContext	
	private EntityManager em;
	
	@EJB 
	private ArtisteManager artisteManager;
	
	@Override
	public void addAlbum(String nom, byte[] pochette, Integer date, String artiste) {
		Artiste a = artisteManager.getArtisteByName(artiste);		
		
		Album album = new Album();
		album.setNom(nom);
		album.setPochette(pochette);
		album.setDate(date);
		album.setArtiste(a);
		
		em.persist(album);		
	}

	

	@Override
	public List <Album> getAlbums() {
		Query q= em.createQuery("FROM Album a");
		
		List <Album> liste = q.getResultList();
		
		return liste;
	}

	@Override
	public Album getAlbumByName(String nom) {
		Query q= em.createQuery("FROM Album a WHERE a.nom like :pNom");	
	 	q.setParameter("pNom",nom);
	 	
	 	List <Album> liste = (List <Album>) q.getResultList();
	 	
	 	if(liste.size() == 0){
	 		return null;
	 	}
	 	else{
	 		return liste.get(0);
	 	}
	}

	@Override
	public Album getAlbumById(int id) {
		Query q= em.createQuery("FROM Album a WHERE a.id = :id");	
	 	q.setParameter("id",id);
	 	
	 	List <Album> liste = (List <Album>) q.getResultList();
	 	
	 	if(liste.size() == 0){
	 		return null;
	 	}
	 	else{
	 		return liste.get(0);
	 	}
	}

}
