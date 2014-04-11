/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.reseau;

import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;

/**
 *
 * @author PeclatJ
 */
public interface IEcouteurServeur {
    
    public void reseauDeplacementMindstorms(DeplacementMindstorms deplacement);
    
}
