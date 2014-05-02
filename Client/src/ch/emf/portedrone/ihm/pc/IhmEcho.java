package ch.emf.portedrone.ihm.pc;

import java.awt.Graphics;

/**
 * Représente un echo sur l'écran.
 * @author Jonathan
 */
public class IhmEcho {

    /**
     * La position x en pixel sur le caneva de l'echo
     */
    private double position_x;
    
    /**
     * La position y en pixel sur le caneva de l'echo
     */
    private double position_y;

    /**
     * Constructeur de l'echo
     * @param distance La distance en mètre depuis le radar.
     * @param angle L'angle de l'écho par rapport au radar.
     */
    public IhmEcho(double distance, double angle) {
        position_x = Math.cos(angle) * distance;
        position_y = Math.sin(angle) * distance;
    }

    /**
     * Permet de mettre à jour l'écho sur l'écran.
     * @param g Le compostant graphique.
     * @param weight La largeur en pixel du caneva.
     * @param height La hauteur en pixel du caneva.
     */
    public void update(Graphics g, int weight, int height) {
        g.fillArc((int) (weight / 2 + position_x * weight / 2) - 3, (int) (height / 2 + position_y * height / 2) - 3, 6, 6, 0, 360);
    }
}
