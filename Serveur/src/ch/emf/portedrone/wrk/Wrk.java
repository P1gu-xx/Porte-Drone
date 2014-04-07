/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.ctrl.ICtrlWrk;
import ch.emf.portedrone.wrk.Drone.Drone;
import ch.emf.portedrone.wrk.Drone.IEcouteurDrone;
import ch.emf.portedrone.wrk.RobotLego.IEcouteurRobotLego;
import ch.emf.portedrone.wrk.RobotLego.RobotLego;
import ch.emf.portedrone.wrk.http.WrkHttp;
import ch.emf.portedrone.wrk.serveur.IEcouteurServeurControle;
import ch.emf.portedrone.wrk.serveur.IEcouteurServeurVideo;
import ch.emf.portedrone.wrk.serveur.ServeurControle;
import ch.emf.portedrone.wrk.serveur.ServeurVideo;
import java.awt.image.BufferedImage;

/**
 *
 * @author ramosdasilm
 */
public class Wrk implements IWrk, IEcouteurDrone, IEcouteurRobotLego, IEcouteurServeurControle, IEcouteurServeurVideo {

    private final int portEcoute = 55584;
    private ICtrlWrk ctrl;
    private Drone drone;
    private RobotLego robotLego;
    private ServeurVideo serveurVideo;
    private ServeurControle serveurControle;
    private WrkHttp wrkHttp;
    private boolean running;
    private Info info;

    public Wrk(ICtrlWrk ctrl) {
        this.ctrl = ctrl;
        info = new Info();
        running = true;

        drone = new Drone(this);
        serveurControle = new ServeurControle(portEcoute);
        drone.connecter();
    }

    public boolean start() {
        traiterLesDonnees();
        return true;
    }

    @Override
    public void stop() {
        running = false;
        drone.stop();
    }

    public void traiterLesDonnees() {
        while (running) {
            info.infoDrone = drone.getInfo();
            ctrl.nouvelleInfo(info);
        }
    }

    @Override
    public void droneImageRecu(BufferedImage img) {

    }

    @Override
    public void droneAltitudeRecu(int alt) {

    }

    @Override
    public void droneNiveauDeBattrieRecu(int i) {

    }

    @Override
    public void droneNiveauDeReseauWifiRecu(long l) {

    }

    @Override
    public void droneDeconnecter() {

    }
}
