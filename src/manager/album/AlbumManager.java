package manager.album;

import java.util.List;

import javax.ejb.Local;

import model.Album;

@Local
public interface AlbumManager {
	public void addAlbum(String nom, byte[] pochette, Integer date, String artiste);
	public List <Album> getAlbums();
	public Album getAlbumByName(String nom);
}
