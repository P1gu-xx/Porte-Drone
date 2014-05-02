package ch.emf.portedrone.ctrl;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.exception.ConnexionException;
import ch.emf.portedrone.ihm.IIhmConnexionCtrl;
import ch.emf.portedrone.ihm.IIhmCtrl;
import ch.emf.portedrone.wrk.IWrkCtrl;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 * Classe contrôleur de l'application. Elle gère les échanges entre le Worker et l'IHM.
 * @author PeclatJ
 */
public class Ctrl implements ICtrlIhm, ICtrlWrk{
    
    /**
     * Constructeur de la classe.
     */
    public Ctrl() {
        
    }
    
    /**
     * Lance l'application. Demarre la plus part des Threads et ouvre l'interface graphique.
     */
    public void start() {
        try {
            wrk.connexion("172.23.86.237");
            ihmConnexion.setVisible(true);
            wrk.update();
        } catch (ConnexionException ce) {
            System.out.println("Erreur : " + ce.titre + " : " + ce.message);
            ihmConnexion.exit();
            ihm.exit();
        }
    }
   
    @Override
    public void imageRecu(BufferedImage img) {
        ihm.afficherImageDrone(img);
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
    
    /**
     * Défini l'IHM principale à contrôler.
     * @param ihm la réference vers l'IHM principale
     */
    public void setIhm(IIhmCtrl ihm) {
        this.ihm = ihm;
    }
    
    /**
     * Défini l'IHM pour la connexion à contrôler.
     * @param ihmConnexion la référence vers l'IHM de la connexion.
     */
    public void setIhmConnexion(IIhmConnexionCtrl ihmConnexion) {
        this.ihmConnexion = ihmConnexion;
    }

    /**
     * Défini le Worker à contrôler.
     * @param wrk la référence vers le worker à contrôler.
     */
    public void setWrk(IWrkCtrl wrk) {
        this.wrk = wrk;
    }
    
    /**
     * La référence vers l'ihm principale.
     */
    private IIhmCtrl ihm;
    
    /**
     * La référence vers l'ihm de la connexion.
     */
    private IIhmConnexionCtrl ihmConnexion;
    
    /**
     * La référence vers le Worker.
     */
    private IWrkCtrl wrk;

}
