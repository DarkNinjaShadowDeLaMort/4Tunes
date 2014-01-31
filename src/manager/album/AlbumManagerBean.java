package manager.album;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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



	@Override
	public List<Album> getAlbumsByIdUser(int idUser) {
		//Query q= em.createQuery("SELECT DISTINCT nom FROM Album a, Chanson c WHERE a.id=c.albumId AND c.utilisateurId=:idUser");
		Query q= em.createQuery("FROM Album a, Chanson c WHERE a.id=c.albumId AND c.utilisateurId=:idUser");
		q.setParameter("idUser", idUser);
		
		List<Album> liste = q.getResultList();
		Set<Album> set = new HashSet<Album>();
		set.addAll(liste);
		liste.clear();
		liste.addAll(set);
		
		return liste;
	}



	@Override
	public List<Album> getAlbumsByArtisteAndUser(int idArtiste, int idUser) {
		Query q= em.createQuery("FROM Album al, Artiste ar, Chanson c WHERE al.id=c.albumId AND al.artisteId=:idArtiste AND c.utilisateurId=:idUser");
		q.setParameter("idArtiste", idArtiste);
		q.setParameter("idUser", idUser);
		
		List<Album> liste = q.getResultList();
		Set<Album> set = new HashSet<Album>();
		set.addAll(liste);
		liste.clear();
		liste.addAll(set);
		
		return liste;
	}

}
