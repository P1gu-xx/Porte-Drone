/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.beans;

import ch.emf.portedrone.beans.drone.InfoDrone;
import ch.emf.portedrone.beans.mindstorms.InfoMindstorms;
import java.io.Serializable;

/**
 *
 * @author ramosdasilm
 */
public class Info implements Serializable {
    
    public Info() {
    }

    public Info(InfoDrone infoDrone, InfoMindstorms infoMindstorms) {
        this.infoDrone = infoDrone;
        this.infoMindstorms = infoMindstorms;
    }
    
    public Info(Info info) {
        this.infoDrone = new InfoDrone(info.infoDrone);
        this.infoMindstorms = new InfoMindstorms(info.infoMindstorms);
    }
    
    public InfoDrone infoDrone;
    public InfoMindstorms infoMindstorms;
}
