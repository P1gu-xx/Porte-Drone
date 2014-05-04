package ch.emf.portedrone.wrk.reseau;

import ch.emf.portedrone.beans.Info;
import java.awt.image.BufferedImage;


/**
 * Ecouteur du réseau.
 * @author PeclatJ
 */
public interface IEcouteurReseau {
    
    /**
     * Permet de définir les informations sur l'écran.
     * @param info Les informations à définir.
     */
    public void setInfo(Info info);
    
    /**
     * Donne l'ordre au client de se reconnecter.
     * @param adresse L'adresse IP du serveur.
     */
    public void reconnexion(String adresse);
    
    /**
     * Permet de mettre à jour l'image de la camera.
     * @param img l'image de la camera.
     */
    public void imageRecu(BufferedImage img);
}
