package manager;

import java.util.List;

import javax.ejb.Local;

import model.Album;
import model.Artiste;
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
}
