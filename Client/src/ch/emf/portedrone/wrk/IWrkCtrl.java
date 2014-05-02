package ch.emf.portedrone.wrk;

import ch.emf.portedrone.exception.ConnexionException;


/**
 * Interface de communication du Worker pour le contrôleur.
 * @author PeclatJ
 */
public interface IWrkCtrl {
    
    /**
     * Connect le client au serveur.
     * @param adresse L'adresse IP du serveur,
     * @throws ConnexionException Le message d'erreur en cas d'exception.
     */
    public void connexion(String adresse) throws ConnexionException;
    
    /**
     * Permet au client de s'authentifier sur le serveur. Nécessaire pour le que serveur lui parle.
     * @param login Le nom d'utilisateur de l'utilisateur.
     * @param motDePasse Le mot de passe de l'utilisateur.
     * @throws ConnexionException Le message d'erreur en cas d'exception.
     */
    public void authentification(String login, String motDePasse) throws ConnexionException;

    /**
     * Lance la boucle principale de l'application.
     */
    public void update();
    
}
