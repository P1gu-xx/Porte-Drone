package ch.emf.portedrone.wrk.reseau;

import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;

/**
 * Interface écouteur pour le serveur TCP.
 * @author PeclatJ
 */
public interface IEcouteurServeur {
    
    /**
     * Indique que le serveur à reçu des informations déplacement.
     * @param deplacement Les informations de déplacement.
     */
    public void reseauDeplacementMindstorms(DeplacementMindstorms deplacement);
    
}
