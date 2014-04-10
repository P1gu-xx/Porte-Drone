/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.beans.Login;
import ch.emf.portedrone.ctrl.ICtrlWrk;
import ch.emf.portedrone.exception.ConnexionException;
import ch.emf.portedrone.wrk.input.IInput;
import ch.emf.portedrone.wrk.reseau.Client;
import ch.emf.portedrone.wrk.reseau.IEcouteurReseau;


/**
 *
 * @author PeclatJ
 */
public class Wrk implements IWrkCtrl, IEcouteurReseau{
    
    public Wrk() {
        client = new Client();
    }
    
    @Override
    public void connexion(String adresse) throws ConnexionException {
        client.setEcouteurReseau(this);
        client.connexion(adresse);
    }
    
    @Override
    public void authentification(String login, String motDePasse) throws ConnexionException{
        client.authentification(new Login(login, motDePasse));
        client.startEcouteur();
    }
    
    @Override
    public void reconnexion(String adresse) {
        try {
            client.exit();
            System.out.println("Reconnexion avec " + adresse);
            client.connexion(adresse);
        } catch (ConnexionException ex) {
            reconnexion(adresse);
        }
    }
    
    @Override
    public void setInfo(Info info) {
        ctrl.setInfo(info);
    }

    public void setCtrl(ICtrlWrk ctrl) {
        this.ctrl = ctrl;
    }
    
    private ICtrlWrk ctrl;
    
    private Client client;
    private IInput input;

}
