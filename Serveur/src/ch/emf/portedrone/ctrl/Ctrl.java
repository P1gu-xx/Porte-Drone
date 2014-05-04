/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ctrl;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.ihm.IIhm;
import ch.emf.portedrone.wrk.IWrk;
import ch.emf.portedrone.wrk.Wrk;
import java.awt.image.BufferedImage;

/**
 *
 * @author ramosdasilm
 */
public class Ctrl implements ICtrlWrk, ICtrlIhm {
    
    private IWrk wrk;
    private IIhm ihm;
    
    public Ctrl(IIhm ihm) {
        this.ihm = ihm;
        this.wrk = new Wrk(this);
    }
    
    /**
     * permet de démarer le wrk et de rendre visible l'ihm.
     */
    public void start() {
        ihm.afficher(true);
        boolean start = wrk.start();
    }
    
    @Override
    public void exit() {
        wrk.exit();
    }
    
    @Override
    public void nouvelleInfo(Info info) {
        ihm.afficherInfoDrone(info);
    }

    @Override
    public void afficherCameraDrone(BufferedImage img) {
        ihm.afficherCameraDrone(img);
    }
    
    
    
}
