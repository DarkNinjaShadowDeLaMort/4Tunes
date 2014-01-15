package manager.genre;

import java.util.List;

import javax.ejb.Local;

import model.Genre;

@Local
public interface GenreManager {
	public List <Genre> getGenres();
	public Genre getGenreByName(String nom);
}
