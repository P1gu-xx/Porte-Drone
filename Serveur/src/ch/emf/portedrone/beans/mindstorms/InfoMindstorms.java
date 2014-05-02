package ch.emf.portedrone.beans.mindstorms;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Bean conteant des infos sur le Mindstorms
 * @author ramosdasilm
 */
public class InfoMindstorms implements Serializable {

    /**
     * Constructeur de l'objet.
     * @param deplacementMindstorms Bean qui contient des infos sur le deplacement.
     * @param echo Liste des echos.
     * @param angle Angle du radar sur l'ecran.
     * @param batterie Niveuau de batterie du Mindsotrms.
     */
    public InfoMindstorms(DeplacementMindstorms deplacementMindstorms, ArrayList<Echo> echo, float angle, float batterie) {
        this.deplacementMindstorms = deplacementMindstorms;
        this.echo = echo;
        this.angle = angle;
        this.batterie = batterie;
    }
    
    /**
     * Constructeur de copie.
     * @param infoMindstorms l'objet à copier.
     */
    public InfoMindstorms(InfoMindstorms infoMindstorms) {
       deplacementMindstorms = new DeplacementMindstorms(infoMindstorms.deplacementMindstorms);
       echo = new ArrayList<>();
        for (Echo echo : infoMindstorms.echo) {
            infoMindstorms.echo.add(new Echo(echo));
        }
       angle = infoMindstorms.angle;
       batterie = infoMindstorms.batterie;
    }

    /**
     * Bean qui contient des infos sur le deplacement.
     */
    public DeplacementMindstorms deplacementMindstorms;
    
    /**
     * Liste des echos.
     */
    public ArrayList<Echo> echo;
    
    /**
     * Angle du radar sur l'ecran.
     */
    public float angle;
    
    /**
     * Niveau de batterie.
     */
    public float batterie;
    
}
