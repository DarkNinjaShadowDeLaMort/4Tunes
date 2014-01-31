package manager.artiste;

import java.util.List;

import javax.ejb.Local;

import model.Artiste;

@Local
public interface ArtisteManager {
	public boolean artisteExist(String nom);
	public void addArtiste(String nom);
	public List <Artiste> getArtistes();
	public List<Artiste> getArtistesByIdUser(int idUser);
	public Artiste getArtisteByName(String nom);
	public Artiste getArtisteById(int id);
}
