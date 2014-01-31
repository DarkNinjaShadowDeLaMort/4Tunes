package manager.chanson;

import java.util.List;

import javax.ejb.Local;

import model.Album;
import model.Artiste;
import model.Chanson;
import model.Genre;

@Local
public interface ChansonManager {
	public void addChanson(String titre, float duree, String url, int artiste, int album, int genre, int id);
	public void addChanson(String titre, float duree, String url, Artiste artiste, Album album, Genre genre, int utilisateurId);
	public Chanson getChansonByUrl(String url);
	public Chanson getChanson(int id);
	public void deleteChanson(int id);

	public List <Chanson> getChansons(int idUser);
	public List<Chanson> getChansonsByAlbum(int idUser, int idAlbum);
	public List<Chanson> getChansonsByArtiste(int idUser, int idArtiste);
	public List<Chanson> getChansonsByGenre(int idUser, int idGenre);
}
