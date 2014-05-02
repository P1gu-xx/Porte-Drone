package ch.emf.portedrone.exception;

/**
 * Exception permettant de gérer les erreurs du réseau.
 * @author PeclatJ
 */
public class ConnexionException extends Exception{
    
    /**
     * Constructeur de l'exception.
     * @param titre Le titre de l'erreur.
     * @param message Le message décrivant l'erreur.
     */
    public ConnexionException(String titre, String message) {
        this.titre = titre;
        this.message = message;
    }
    
    /**
     * Le titre de l'erreur.
     */
    public String titre;
    
    /**
     * Le message décrivant l'erreur.
     */
    public String message;
}
