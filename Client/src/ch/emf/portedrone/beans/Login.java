package ch.emf.portedrone.beans;

import java.io.Serializable;

/**
 * Bean contenant des infos sur le login, lors d'une connexion réseau.
 * @author ramosdasilm
 */
public class Login implements Serializable{

    /**
     * Constructeur pour le login.
     * @param email l'adresse e-mail de l'utilisateur.
     * @param mdp le mot de passe de l'utilisateur.
     */
    public Login(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }
    
    /**
     * Constructeur de copie.
     * @param login L'objet à copier.
     */
    public Login(Login login) {
        email = login.email;
        mdp = login.mdp;
    }
    
    /**
     * L'adresse e-mail de l'utilisateur.
     */
    public String email;
    
    /*
     * Le mot de passe de l'utilisateur.
     */
    public String mdp;

}
