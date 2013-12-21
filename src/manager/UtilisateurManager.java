package manager;


public interface UtilisateurManager {
	public void addUtilisateur(String username, String password, String email);
	public int login(String username, String password);
}
