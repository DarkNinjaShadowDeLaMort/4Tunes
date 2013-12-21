package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the artiste database table.
 * 
 */
@Entity
@Table(name="artiste")
@NamedQuery(name="Artiste.findAll", query="SELECT a FROM Artiste a")
public class Artiste implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nom;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="artiste")
	private List<Album> albums;

	//bi-directional many-to-one association to Chanson
	@OneToMany(mappedBy="artiste")
	private List<Chanson> chansons;

	public Artiste() {
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

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setArtiste(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setArtiste(null);

		return album;
	}

	public List<Chanson> getChansons() {
		return this.chansons;
	}

	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}

	public Chanson addChanson(Chanson chanson) {
		getChansons().add(chanson);
		chanson.setArtiste(this);

		return chanson;
	}

	public Chanson removeChanson(Chanson chanson) {
		getChansons().remove(chanson);
		chanson.setArtiste(null);

		return chanson;
	}

}