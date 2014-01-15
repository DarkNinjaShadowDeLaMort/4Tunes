package manager.chanson;

import java.util.List;

import javax.ejb.Local;

import model.Chanson;

@Local
public interface ChansonManager {
	public void addChanson(String titre, float duree, String url, String artiste, String album, String genre, int id);
	public List <Chanson> getChansons(int id);
	public Chanson getChansonByUrl(String url);
	public Chanson getChanson(int id);
}
