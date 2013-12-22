package manager;

import javax.ejb.Local;

@Local
public interface UtilisateurManager {
	public void addUtilisateur(String username, String password, String email);
	public int login(String username, String password);
	boolean existUtilisateur(String login);
}
