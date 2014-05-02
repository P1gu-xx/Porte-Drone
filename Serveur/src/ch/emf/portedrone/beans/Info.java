package ch.emf.portedrone.beans;

import ch.emf.portedrone.beans.drone.InfoDrone;
import ch.emf.portedrone.beans.mindstorms.InfoMindstorms;
import java.io.Serializable;

/**
 * Bean contenant toutes les informations qui peuvent transiter le réseau.
 * Il y a les infos sur le Mindstorms et sur le drone.
 * @author ramosdasilm
 */
public class Info implements Serializable {

    /**
     * Constructeur par defaut, n'initialise à null.
     */
    public Info() {
    }
    
    /**
     * Constructeur, prend les informations du drone et du Mindstorms.
     * @param infoDrone Information sur le drone
     * @param infoMindstorms Information sur le Mindstorms
     */
    public Info(InfoDrone infoDrone, InfoMindstorms infoMindstorms) {
        this.infoDrone = infoDrone;
        this.infoMindstorms = infoMindstorms;
    }
    
    /**
     * Constructeur de copie.
     * @param info l'objet à copier
     */
    public Info(Info info) {
        this.infoDrone = new InfoDrone(info.infoDrone);
        this.infoMindstorms = new InfoMindstorms(info.infoMindstorms);
    }
    
    /**
     * Bean contenant des information sur l'etat du drone.
     */
    public InfoDrone infoDrone;
    
    /**
     * Bean contenant des information sur l'etat du Mindstorms.
     */
    public InfoMindstorms infoMindstorms;
}
