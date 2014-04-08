/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.beans.drone;

import java.io.Serializable;

/**
 *
 * @author ramosdasilm
 */
public class InfoDrone implements Serializable{

    public DeplacementDrone deplacementDrone;
    public int hauteur, niveauDeBattrie;
    public long reseauWifi;
    public boolean enVol, enDecollage, connecter, decoller;

    public InfoDrone(DeplacementDrone deplacementDrone, int hauteur, int niveauDeBattrie, long reseauWifi, boolean enVol, boolean enDecollage, boolean connecter, boolean decoller) {
        this.deplacementDrone = deplacementDrone;
        this.hauteur = hauteur;
        this.niveauDeBattrie = niveauDeBattrie;
        this.reseauWifi = reseauWifi;
        this.enVol = enVol;
        this.enDecollage = enDecollage;
        this.connecter = connecter;
        this.decoller = decoller;
    }

}
