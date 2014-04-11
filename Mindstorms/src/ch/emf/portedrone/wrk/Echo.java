/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

/**
 *
 * @author PeclatJ
 */
public class Echo extends ch.emf.portedrone.beans.Echo {

    public Echo(double distance, double angle) {
        super(distance, angle);
        
        ttl = System.currentTimeMillis();
    }
    
    public boolean isDead() {
        return System.currentTimeMillis() - ttl > TTL;
    }
    
    public static final int TTL = 5000;
    private long ttl;
    
}
