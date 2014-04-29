/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.beans.Login;
import ch.emf.portedrone.beans.drone.DeplacementDrone;
import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;
import ch.emf.portedrone.ctrl.ICtrlWrk;
import ch.emf.portedrone.exception.ConnexionException;
import ch.emf.portedrone.wrk.input.IInput;
import ch.emf.portedrone.wrk.input.manette.ManetteDualshock3;
import ch.emf.portedrone.wrk.reseau.Client;
import ch.emf.portedrone.wrk.reseau.IEcouteurReseau;

/**
 *
 * @author PeclatJ
 */
public class Wrk implements IWrkCtrl, IEcouteurReseau {

    public Wrk() {
        client = new Client();
        exit = false;
        manette = new ManetteDualshock3();
        droneSelectionne = true;
    }

    @Override
    public void update() {
        while (!exit) {
            if (manette.isReady() && client.isConnexion()) {
                manette.poll();
                
                if(manette.isPressed(ManetteDualshock3.R1) && !manette.isPressed(ManetteDualshock3.L1)) {
                    droneSelectionne = true;
                    System.out.println("Drone");
                }
                
                if(manette.isPressed(ManetteDualshock3.L1) && !manette.isPressed(ManetteDualshock3.R1)) {
                    droneSelectionne = false;
                    System.out.println("Mindstorms");
                }

                if (droneSelectionne) { // Commande pour le drone
                    if (manette.isPressed(ManetteDualshock3.CROIX)) {
                        client.ecrireInt(0);
                    }
                    client.ecrireInt(1);
                    client.ecrireObjet(new DeplacementDrone(
                            (int) (-manette.getValue(ManetteDualshock3.ANALOG_LEFT_Y) * 100),
                            (int) (-manette.getValue(ManetteDualshock3.ANALOG_LEFT_X) * 100),
                            (int) (-manette.getValue(ManetteDualshock3.ANALOG_RIGHT_X) * 100),
                            (int) (manette.getValue(ManetteDualshock3.ANALOG_RIGHT_Y) * 100)));
                    if (manette.isPressed(ManetteDualshock3.CARRE)) {
                        client.ecrireInt(2);
                    }
                } else { // Commande pour le Mindstorms
                    client.ecrireInt(4);
                    client.ecrireObjet(new DeplacementMindstorms(
                            (int) (manette.getValue(ManetteDualshock3.ANALOG_RIGHT_Y) * 100),
                            (int) (manette.getValue(ManetteDualshock3.ANALOG_LEFT_Y) * 100)));
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("Interruption");
                }
            }
        }
    }

    @Override
    public void connexion(String adresse) throws ConnexionException {
        client.setEcouteurReseau(this);
        client.connexion(adresse);

    }

    @Override
    public void authentification(String email, String motDePasse) throws ConnexionException {
        login = new Login(email, motDePasse);
        client.authentification(login);

    }

    @Override
    public void reconnexion(String adresse) {
        try {
            //  client.exit();
            System.out.println("Reconnexion avec " + adresse);
            client.connexion(adresse);
            client.authentification(login);
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
    private Login login;
    private ManetteDualshock3 manette;
    private boolean droneSelectionne;
    private boolean exit;
}
