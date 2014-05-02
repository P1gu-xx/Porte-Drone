package ch.emf.portedrone.beans.mindstorms;

import java.io.Serializable;

/**
 * Bean contenant des informations sur une echo du radar.
 * @author ramosdasilm
 */
public class Echo implements Serializable {
    
    /**
     * Constructeur de l'objet
     * @param distance La distance en mètre entre l'echo et le radar. 
     * @param angle L'angle sur l'ecran en radian.
     */
    public Echo(double distance, double angle) {
        this.distance = distance;
        this.angle = angle;
    }
    
    /**
     * Constructeur de copie de l'objet.
     * @param echo l'objet à copier.
     */
    public Echo(Echo echo) {
        distance = echo.distance;
        angle = echo.angle;
    }

    /**
     * La distance en mètre entre l'echo et le radar.
     */
    public double distance;
    
    /**
     * L'angle sur l'ecran en radian.
     */
    public double angle;
}
