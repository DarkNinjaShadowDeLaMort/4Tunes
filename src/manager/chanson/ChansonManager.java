package manager.chanson;

import java.util.List;

import javax.ejb.Local;

import model.Album;
import model.Artiste;
import model.Chanson;
import model.Genre;
import model.Utilisateur;

@Local
public interface ChansonManager {
	public void addChanson(String titre, float duree, String url, String artiste, String album, String genre, int id);
	public void addChanson(String titre, float duree, String url, Artiste artiste, Album album, Genre genre, int utilisateurId);
	public List <Chanson> getChansons(int id);
	public Chanson getChansonByUrl(String url);
	public Chanson getChanson(int id);
}
