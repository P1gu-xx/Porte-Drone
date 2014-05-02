package ch.emf.portedrone.beans.mindstorms;

import java.io.Serializable;

/**
 * Bean contenant des informations sur le deplacement du mindstorms.
 * @author ramosdasilm
 */
public class DeplacementMindstorms implements Serializable{

    /**
     * Constucteur de l'objet
     * @param vitesseRoueDroite Vitesse de la roue droite en degré par seconde.
     * @param vitesseRoueGauche Vitesse de la roue gauche en degré par seconde.
     */
    public DeplacementMindstorms(int vitesseRoueDroite, int vitesseRoueGauche) {
        this.vitesseRoueDroite = vitesseRoueDroite;
        this.vitesseRoueGauche = vitesseRoueGauche;
    }
    
    /**
     * Constructeur de copie.
     * @param deplacementMindstorms l'objet à copier. 
     */
    public DeplacementMindstorms(DeplacementMindstorms deplacementMindstorms) {
        vitesseRoueDroite = deplacementMindstorms.vitesseRoueDroite;
        vitesseRoueGauche = deplacementMindstorms.vitesseRoueGauche;
    }

    /**
     * La vitesse de la roue droite en degré par seconde
     */
    public int vitesseRoueDroite;
    
    /**
     * La vitesse de la roue gauche en degré par seconde
     */
    public int vitesseRoueGauche;
}
