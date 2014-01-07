package manager;

import java.util.List;

import javax.ejb.Local;

import model.Album;
import model.Artiste;
import model.Chanson;
import model.Genre;

@Local
public interface InfoManager {
	public List <Genre> getGenres();
	public void addGenre(String nom, String pere);
	public Genre getGenreByName(String nom);
	
	public boolean artisteExist(String nom);
	public void addArtiste(String nom);
	public List <Artiste> getArtistes();
	public Artiste getArtisteByName(String nom);
	
	public void addAlbum(String nom, byte[] pochette, Integer date, String artiste);
	public List <Album> getAlbums();
	public Album getAlbumByName(String nom);
	
	public void addChanson(String titre, float duree, String url, String artiste, String album, String genre, int id);
	public List <Chanson> getChansons(int id);
	public Chanson getChansonByUrl(String url);
	public Chanson getChanson(int id);
}
