/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ihm;

/**
 *
 * @author PeclatJ
 */
public interface IIhmConnexionCtrl {
    
    public void setVisible(boolean visible);
    public void afficherMessage(String titre, String message, int type);
    public void exit();
}
