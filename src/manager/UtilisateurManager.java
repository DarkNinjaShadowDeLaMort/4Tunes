package manager;

import javax.ejb.Local;

import model.Utilisateur;

@Local
public interface UtilisateurManager {
	public void addUtilisateur(String username, String password, String email);
	public int login(String username, String password);
	boolean existUtilisateur(String login);
	
	public Utilisateur getUtilisateur(int id);
}
