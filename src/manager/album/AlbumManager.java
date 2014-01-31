package manager.album;

import java.util.List;

import javax.ejb.Local;

import model.Album;
import model.Artiste;

@Local
public interface AlbumManager {
	public void addAlbum(String nom, byte[] pochette, Integer date, String artiste);
	public List <Album> getAlbums();
	public Album getAlbumByName(String nom);
	public Album getAlbumById(int id);
	public List<Album> getAlbumsByIdUser(int idUser);
	public List<Album> getAlbumsByArtisteAndUser(int idArtiste, int idUser);
}
