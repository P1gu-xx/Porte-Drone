package ch.emf.portedrone.beans.drone;

import java.io.Serializable;

/**
 * Bean qui contient des infos sur le drone.
 * @author ramosdasilm
 */
public class InfoDrone implements Serializable{

    /**
     * Constructeur de base
     * @param deplacementDrone Les infos sur le déplacement du drone.
     * @param hauteur L'altitude du drone.
     * @param niveauDeBattrie Le niveau de la batterie du drone.
     * @param reseauWifi La puissance du signale wifi.
     * @param enVol Le drone est en vol.
     * @param enDecollage Le drone est en décollage.
     * @param connecter Le drone est connecté.
     */
    public InfoDrone(DeplacementDrone deplacementDrone, int hauteur, int niveauDeBattrie, long reseauWifi, boolean enVol, boolean enDecollage, boolean connecter, boolean decoller) {
        this.deplacementDrone = deplacementDrone;
        this.hauteur = hauteur;
        this.niveauDeBattrie = niveauDeBattrie;
        this.reseauWifi = reseauWifi;
        this.enVol = enVol;
        this.enDecollage = enDecollage;
        this.connecter = connecter;
    }
    
    /**
     * Constructeur de copie
     * @param infoDrone L'objet à copier
     */
    public InfoDrone(InfoDrone infoDrone) {
        deplacementDrone = new DeplacementDrone(infoDrone.deplacementDrone);
        hauteur = infoDrone.hauteur;
        niveauDeBattrie = infoDrone.niveauDeBattrie;
        reseauWifi = infoDrone.reseauWifi;
        enVol = infoDrone.enVol;
        enDecollage = infoDrone.enDecollage;
        connecter = infoDrone.connecter;
    }
    
    /**
     * beans de déplacment du drone.
     */
    public DeplacementDrone deplacementDrone;
    
    /**
     * L'altitude du drone.
     */
    public int hauteur;
    
    /**
     * Le niveau de batterie du drone.
     */
    public int niveauDeBattrie;
    
    /**
     * La puissance du signal wifi du drone.
     */
    public long reseauWifi;
    
    /**
     * Indique si le drone est en vol.
     */
    public boolean enVol;
    
    /**
     * Indique si le drone est en train de decoller.
     */
    public boolean enDecollage;
    
    /**
     * Indique si l'on est connecté au drone.
     */
    public boolean connecter;

}
