/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portdrone.wrk.reseau;

import ch.emf.portdrone.beans.DeplacementMindstorms;

/**
 *
 * @author PeclatJ
 */
public interface IEcouteurServeur {
    
    public void reseauDeplacementMindstorms(DeplacementMindstorms deplacement);
    
}
