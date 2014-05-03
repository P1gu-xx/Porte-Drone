package ch.emf.portedrone.ctrl;

import ch.emf.portedrone.beans.Info;
import java.awt.image.BufferedImage;


/**
 * Interface de communication du Contrôleur pour le Worker.
 * @author PeclatJ
 */
public interface ICtrlWrk {
    
    /**
     * Permet de mettre à jour les information affichées sur l'IHM.
     * @param info Le bean Info, contenant les informations à afficher sur l'écran.
     */
    public void setInfo(Info info);
    public void setControleDrone(boolean controleDrone);
       
    /**
     * Permet de mettre à jour l'image de la camera du drone à l'écran.
     * @param img L'image.
     */
    public void imageRecu(BufferedImage img);
}
