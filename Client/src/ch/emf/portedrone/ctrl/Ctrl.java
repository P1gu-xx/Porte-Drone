/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ctrl;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.exception.ConnexionException;
import ch.emf.portedrone.ihm.IIhmConnexionCtrl;
import ch.emf.portedrone.ihm.IIhmCtrl;
import ch.emf.portedrone.wrk.IWrkCtrl;
import javax.swing.JOptionPane;

/**
 *
 * @author PeclatJ
 */
public class Ctrl implements ICtrlIhm, ICtrlWrk{
    
    public Ctrl() {
        
    }
    
    public void start() {
        try {
            wrk.connexion("127.0.0.1");
            ihmConnexion.setVisible(true);
            wrk.update();
        } catch (ConnexionException ce) {
            System.out.println("Erreur : " + ce.titre + " : " + ce.message);
            ihmConnexion.exit();
            ihm.exit();
        }
    }
        
    @Override
    public void connexion(String login, String motDePasse) {
        try {
            wrk.authentification(login, motDePasse);
            ihmConnexion.setVisible(false);
            ihm.setVisible(true);
        } catch (ConnexionException ce) {
            ihmConnexion.afficherMessage(ce.titre, ce.message, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void setInfo(Info info) {
        ihm.setInfo(info);
    }
    
    public void setIhm(IIhmCtrl ihm) {
        this.ihm = ihm;
    }
    
    public void setIhmConnexion(IIhmConnexionCtrl ihmConnexion) {
        this.ihmConnexion = ihmConnexion;
    }

    public void setWrk(IWrkCtrl wrk) {
        this.wrk = wrk;
    }
    
    private IIhmCtrl ihm;
    private IIhmConnexionCtrl ihmConnexion;
    private IWrkCtrl wrk;

}
