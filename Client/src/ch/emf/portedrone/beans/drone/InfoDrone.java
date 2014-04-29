/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public DeplacementDrone deplacementDrone;
    public int hauteur;
    public int niveauDeBattrie;
    public long reseauWifi;
    public boolean enVol;
    public boolean enDecollage;
    public boolean connecter;

}
