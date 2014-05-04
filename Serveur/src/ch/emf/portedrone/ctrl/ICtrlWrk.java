/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ctrl;

import ch.emf.portedrone.beans.Info;
import java.awt.image.BufferedImage;

/**
 *
 * @author ramosdasilm
 */
public interface ICtrlWrk {

    /**
     * permet de donner les info au controlleur.
     * @param info 
     */
    void nouvelleInfo(Info info);
    
    /**
     * permet de donner les images du drone au ctrl.
     * @param img 
     */
    void afficherCameraDrone(BufferedImage img);
    
}
