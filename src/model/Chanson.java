package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chanson database table.
 * 
 */
@Entity
@Table(name="chanson")
@NamedQuery(name="Chanson.findAll", query="SELECT c FROM Chanson c")
public class Chanson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float duree;

	private String titre;

	private String url;
	
	private int utilisateurId;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="utilisateurId", insertable=false, updatable=false)
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to Artiste
	@ManyToOne
	@JoinColumn(name="artisteId")
	private Artiste artiste;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="albumId")
	private Album album;

	//bi-directional many-to-one association to Genre
	@ManyToOne
	@JoinColumn(name="idGenre")
	private Genre genre;

	public Chanson() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getDuree() {
		return this.duree;
	}

	public void setDuree(float duree) {
		this.duree = duree;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Artiste getArtiste() {
		return this.artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setUtilisateurId(int id) {
		this.utilisateurId = id;
	}

}