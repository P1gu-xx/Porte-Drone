/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ctrl;

import ch.emf.portedrone.beans.Info;
import java.awt.image.BufferedImage;


/**
 *
 * @author PeclatJ
 */
public interface ICtrlWrk {
    
    public void setInfo(Info info);
    public void setControleDrone(boolean controleDrone);
    public void imageRecu(BufferedImage img);
}
