package manager.chanson;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import manager.album.AlbumManager;
import manager.artiste.ArtisteManager;
import manager.genre.GenreManager;
import model.Album;
import model.Artiste;
import model.Chanson;
import model.Genre;
import model.Utilisateur;
@Stateless
public class ChansonMangerBean implements ChansonManager {
	@PersistenceContext	
	private EntityManager em;
	
	@EJB 
	private ArtisteManager artisteManager;
	
	@EJB 
	private AlbumManager albumManager;
	
	@EJB 
	private GenreManager genreManager;
	
	@Override
	public void addChanson(String titre, float duree, String url, int artiste, int album, int genre, int id) {
		Chanson chanson = new Chanson();
		chanson.setTitre(titre);
		chanson.setDuree(duree);
		chanson.setUrl(url);
		
		Artiste a = artisteManager.getArtisteById(artiste);
		chanson.setArtiste(a);
		
		Album al = albumManager.getAlbumById(album);
		chanson.setAlbum(al);
		
		Genre g = genreManager.getGenreById(genre);
		chanson.setGenre(g);
		
		chanson.setUtilisateurId(id);
		
		em.persist(chanson);
		em.flush();
	}
	
	@Override
	public void addChanson(String titre, float duree, String url, Artiste artiste, Album album, Genre genre, int utilisateurId) {
		Chanson chanson = new Chanson();
		chanson.setTitre(titre);
		chanson.setDuree(duree);
		chanson.setUrl(url);
		
		chanson.setArtiste(artiste);
		
		chanson.setAlbum(album);
		
		chanson.setGenre(genre);
		
		chanson.setUtilisateurId(utilisateurId);
		
		em.persist(chanson);
		em.flush();
	}

	@Override
	public Chanson getChanson(int id) {
		return em.find(Chanson.class, id);
	}

	@Override
	public List<Chanson> getChansons(int id) {
		Query q= em.createQuery("FROM Chanson c WHERE c.utilisateurId = :pId");	
	 	q.setParameter("pId",id);
	 	
	 	List <Chanson> liste = (List <Chanson>) q.getResultList();
	 	
		return liste;
	}

	@Override
	public Chanson getChansonByUrl(String url) {
		Query q= em.createQuery("FROM Chanson c WHERE c.url like :pUrl");	
	 	q.setParameter("pUrl",url);
	 	
	 	List <Chanson> liste = (List <Chanson>) q.getResultList();
	 	
	 	if(liste.size() == 0){
	 		return null;
	 	}
	 	else{
	 		return liste.get(0);
	 	}
	}

	public void deleteChanson(int id) {

		Query q= em.createQuery("DELETE FROM Chanson c WHERE c.id = :id");	
	 	q.setParameter("id",id);
	 	q.executeUpdate();
	}
}
