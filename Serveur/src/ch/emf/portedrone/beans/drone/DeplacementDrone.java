package ch.emf.portedrone.beans.drone;

import java.io.Serializable;

/**
 * Bean contenant des infos sur le déplacement du drone.
 * @author ramosdasilm
 */
public class DeplacementDrone implements Serializable{

    /**
     * Constucteur de l'objet.
     * @param vitesseX La vitesse de déplacement en X.
     * @param vitesseY La vitesse de déplacement en Y.
     * @param vitesseZ La vitesse de déplacement en Z.
     * @param spin La vitesse de rotation.
     */
    public DeplacementDrone(int vitesseX, int vitesseY, int vitesseZ, int spin) {
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.vitesseZ = vitesseZ;
        this.spin = spin;
    }
    
    /**
     * Constructeur de copie.
     * @param deplacementDrone l'objet à copier.
     */
    public DeplacementDrone(DeplacementDrone deplacementDrone) {
        vitesseX = deplacementDrone.vitesseX;
        vitesseY = deplacementDrone.vitesseY;
        vitesseZ = deplacementDrone.vitesseZ;
        spin = deplacementDrone.spin;
    }

    /**
     * La vitesse de déplacement en X.
     */
    public int vitesseX;
    
    /**
     * La vitesse de déplacement en Y.
     */
    public int vitesseY;
    
    /**
     * La vitesse de déplacement en Z.
     */
    public int vitesseZ;
    
    /**
     * La vitesse de rotation.
     */
    public int spin;
}
