/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ihm;

import ch.emf.portedrone.beans.Info;


/**
 *
 * @author PeclatJ
 */
public interface IIhmCtrl {
    
    public void setVisible(boolean visible);
    public void afficherMessage(String titre, String message, int type);
    public void setInfo(Info info);
    public void exit();
}
