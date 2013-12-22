package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@Table(name="album")
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Integer date;

	private String nom;

	@Lob
	private byte[] pochette;

	//bi-directional many-to-one association to Artiste
	@ManyToOne
	@JoinColumn(name="artisteId")
	private Artiste artiste;

	//bi-directional many-to-one association to Chanson
	@OneToMany(mappedBy="album")
	private List<Chanson> chansons;

	public Album() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getDate() {
		return this.date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public byte[] getPochette() {
		return this.pochette;
	}

	public void setPochette(byte[] pochette) {
		this.pochette = pochette;
	}

	public Artiste getArtiste() {
		return this.artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public List<Chanson> getChansons() {
		return this.chansons;
	}

	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}

	public Chanson addChanson(Chanson chanson) {
		getChansons().add(chanson);
		chanson.setAlbum(this);

		return chanson;
	}

	public Chanson removeChanson(Chanson chanson) {
		getChansons().remove(chanson);
		chanson.setAlbum(null);

		return chanson;
	}

}