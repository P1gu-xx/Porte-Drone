/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.beans.mindstorms;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ramosdasilm
 */
public class InfoMindstorms implements Serializable {

    public DeplacementMindstorms deplacementMindstorms;
    public ArrayList<Echo> echo;
    public float angle,batterie;

    public InfoMindstorms(DeplacementMindstorms deplacementMindstorms, ArrayList<Echo> echo, float angle, float batterie) {
        this.deplacementMindstorms = deplacementMindstorms;
        this.echo = echo;
        this.angle = angle;
        this.batterie = batterie;
    }
    
    public InfoMindstorms(InfoMindstorms infoMindstorms) {
       deplacementMindstorms = new DeplacementMindstorms(infoMindstorms.deplacementMindstorms);
       echo = new ArrayList<>();
        for (Echo echo : infoMindstorms.echo) {
            infoMindstorms.echo.add(new Echo(echo));
        }
       angle = infoMindstorms.angle;
       batterie = infoMindstorms.batterie;
    }

}
