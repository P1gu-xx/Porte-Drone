/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.beans.mindstorms;

import java.io.Serializable;

/**
 *
 * @author ramosdasilm
 */
public class DeplacementMindstorms implements Serializable{

    public int vitesseRoueDroite, vitesseRoueGauche;

    public DeplacementMindstorms(int vitesseRoueDroite, int vitesseRoueGauche) {
        this.vitesseRoueDroite = vitesseRoueDroite;
        this.vitesseRoueGauche = vitesseRoueGauche;
    }

}
