package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@Table(name="utilisateur")
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String login;

	private String password;

	//bi-directional many-to-one association to Chanson
	@OneToMany(mappedBy="utilisateur")
	private List<Chanson> chansons;

	public Utilisateur() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Chanson> getChansons() {
		return this.chansons;
	}

	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}

	public Chanson addChanson(Chanson chanson) {
		getChansons().add(chanson);
		chanson.setUtilisateur(this);

		return chanson;
	}

	public Chanson removeChanson(Chanson chanson) {
		getChansons().remove(chanson);
		chanson.setUtilisateur(null);

		return chanson;
	}

}