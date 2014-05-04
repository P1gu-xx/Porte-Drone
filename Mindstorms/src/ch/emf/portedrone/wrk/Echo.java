package ch.emf.portedrone.wrk;

/**
 * Sous-classe du bean echo, mais avec un TTL ajouté.
 * @author PeclatJ
 */
public class Echo extends ch.emf.portedrone.beans.mindstorms.Echo {

    /**
     * Constructeur de la classe
     * @param distance La distance entre l'écho et le radar.
     * @param angle Angle en radian que forme l'écho.
     */
    public Echo(double distance, double angle) {
        super(distance, angle);
        
        ttl = System.currentTimeMillis();
    }
    
    /**
     * Permet de savoir si le TTL de l'écho est à 0.
     * @return true si l'écho est mort.
     */
    public boolean isDead() {
        return System.currentTimeMillis() - ttl > TTL;
    }
    
    public static final int TTL = 5000;
    private long ttl;
    
}
