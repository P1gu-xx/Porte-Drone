/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import wrk.reseau.IEcouteurServeur;
import wrk.reseau.Serveur;

/**
 *
 * @author PeclatJ
 */
public class Wrk implements IEcouteurServeur{
    
    public Wrk() {
        
    }
    
    private Serveur serveur;
    private Moteurs moteur;
    private Radar radar;
    
}
