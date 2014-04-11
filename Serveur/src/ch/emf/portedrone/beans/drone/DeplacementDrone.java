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
public class DeplacementDrone implements Serializable{

    public int vitesseX, vitesseY, vitesseZ, spin;

    public DeplacementDrone(int vitesseX, int vitesseY, int vitesseZ, int spin) {
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.vitesseZ = vitesseZ;
        this.spin = spin;
    }
    
    public DeplacementDrone(DeplacementDrone deplacementDrone) {
        vitesseX = deplacementDrone.vitesseX;
        vitesseY = deplacementDrone.vitesseY;
        vitesseZ = deplacementDrone.vitesseZ;
        spin = deplacementDrone.spin;
    }

}
