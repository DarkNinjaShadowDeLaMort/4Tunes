package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@Table(name="genre")
@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nom;

	//bi-directional many-to-one association to Chanson
	@OneToMany(mappedBy="genre")
	private List<Chanson> chansons;

	//bi-directional many-to-one association to Genre
	@ManyToOne
	@JoinColumn(name="genreId")
	private Genre genre;

	//bi-directional many-to-one association to Genre
	@OneToMany(mappedBy="genre")
	private List<Genre> genres;

	public Genre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Chanson> getChansons() {
		return this.chansons;
	}

	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}

	public Chanson addChanson(Chanson chanson) {
		getChansons().add(chanson);
		chanson.setGenre(this);

		return chanson;
	}

	public Chanson removeChanson(Chanson chanson) {
		getChansons().remove(chanson);
		chanson.setGenre(null);

		return chanson;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Genre addGenre(Genre genre) {
		getGenres().add(genre);
		genre.setGenre(this);

		return genre;
	}

	public Genre removeGenre(Genre genre) {
		getGenres().remove(genre);
		genre.setGenre(null);

		return genre;
	}

}