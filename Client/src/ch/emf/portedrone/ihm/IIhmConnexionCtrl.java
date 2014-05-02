package ch.emf.portedrone.ihm;

/**
 * Interface de communication du l'IHM Connexion pour le contrôleur.
 * @author PeclatJ
 */
public interface IIhmConnexionCtrl {
    
    /**
     * Permet de rendre l'IHM Connexion visible ou invisible.
     * @param visible Rendre visible ou invisible l'IHM Connexion. True = Visible.
     */
    public void setVisible(boolean visible);
    
    /**
     * Permet d'afficher un message.
     * @param titre Le titre de la fenêtre.
     * @param message Le message dans la fenetre.
     * @param type Le type de fenêtre,
     */
    public void afficherMessage(String titre, String message, int type);
    
    /**
     * Permet de terminer le programme.
     */
    public void exit();
}
