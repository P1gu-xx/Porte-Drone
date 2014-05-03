package ch.emf.portedrone.ihm;

import ch.emf.portedrone.beans.Info;
import java.awt.image.BufferedImage;


/**
 * Interface de communication du l'IHM pour le contrôleur.
 * @author PeclatJ
 */
public interface IIhmCtrl {
    
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
     * Met à jour les informations afficher sur l'IHM.
     * @param info le bean contenant les inforamtions affichées sur l'IHM.
     */
    public void setInfo(Info info);
    public void setControleDrone(boolean controleDrone);
    
    /**
     * Permet de terminer le programme.
     */
    public void exit();
    
    /**
     * Met à jour l'image de la camera du drone sur l'IHM.
     * @param img L'image de la camera du drone.
     */
    public void afficherImageDrone(BufferedImage img);
}
