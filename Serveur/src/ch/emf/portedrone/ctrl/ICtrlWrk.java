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

    void nouvelleInfo(Info info);
    void afficherCameraDrone(BufferedImage img);
    
}
