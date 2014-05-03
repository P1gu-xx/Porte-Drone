/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.beans.Login;
import ch.emf.portedrone.beans.drone.DeplacementDrone;
import ch.emf.portedrone.beans.drone.InfoDrone;
import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;
import ch.emf.portedrone.beans.mindstorms.Echo;
import ch.emf.portedrone.beans.mindstorms.InfoMindstorms;
import ch.emf.portedrone.ctrl.ICtrlWrk;
import ch.emf.portedrone.wrk.Drone.Drone;
import ch.emf.portedrone.wrk.Drone.IEcouteurDrone;
import ch.emf.portedrone.wrk.mindstorms.IEcouteurMindstorms;
import ch.emf.portedrone.wrk.mindstorms.Mindstorms;
import ch.emf.portedrone.wrk.http.WrkHttp;
import ch.emf.portedrone.wrk.serveur.IEcouteurServeurControle;
import ch.emf.portedrone.wrk.serveur.IEcouteurServeurVideo;
import ch.emf.portedrone.wrk.serveur.ServeurControle;
import ch.emf.portedrone.wrk.serveur.ServeurVideo;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramosdasilm
 */
public class Wrk implements IWrk, IEcouteurDrone, IEcouteurMindstorms, IEcouteurServeurControle, IEcouteurServeurVideo {

    private ICtrlWrk ctrl;
    private Drone drone;
    private Mindstorms mindstorms;
    private ServeurVideo serveurVideo;
    private ServeurControle serveurControle;
    private WrkHttp wrkHttp;
    private boolean running;
    private Info info;
    private Date dateDecollage;

    public Wrk(ICtrlWrk ctrl) {
        this.ctrl = ctrl;
        running = true;
        wrkHttp = new WrkHttp();
        info = new Info();
        info.infoDrone = new InfoDrone(new DeplacementDrone(0, 0, 0, 0), 0, 0, 0, false, false, false, false);
        info.infoMindstorms = new InfoMindstorms(new DeplacementMindstorms(0, 0), new ArrayList<Echo>(), 0f, 0f);
        drone = new Drone(this);
        mindstorms = new Mindstorms(this);
        serveurControle = new ServeurControle(this);
        try {
            serveurVideo = new ServeurVideo("172.23.87.255", 8888);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean start() {
        mindstorms.start();
        drone.start();
        traiterLesDonnees();
        return true;
    }

    @Override
    public void exit() {
        running = false;
        serveurControle.setRunning(false);
        drone.exit();
        mindstorms.exit();
    }

    public void traiterLesDonnees() {
        while (running) {
            this.info.infoDrone = new InfoDrone(drone.getInfo());

            ctrl.nouvelleInfo(info);
            serveurControle.envoyerInfo(info);

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void droneImageRecu(BufferedImage img) {
        serveurVideo.envoyerDonnee(img);
        ctrl.afficherCameraDrone(img);
    }

    @Override
    public void droneAltitudeRecu(int altitude) {

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

    @Override
    public void faireBougerDrone(DeplacementDrone dd) {

        drone.bouger(dd);
    }

    @Override
    public void faireDecollerDrone() {
        if (!info.infoDrone.enDecollage) {
            if (dateDecollage == null) {
                dateDecollage = new Date();

            } else {
                wrkHttp.enregistrerVol(dateDecollage, new Date());
                dateDecollage = null;
            }
            drone.decoller();
        }
    }

    @Override
    public void changerLaCamera() {
        System.out.println("ordre de changement de camera recu");
        drone.changerCamera();
    }

    @Override
    public void faireBougerMindstorms(DeplacementMindstorms drl) {

        System.out.println("d :" + drl.vitesseRoueDroite + " \t g :" + drl.vitesseRoueGauche);

        mindstorms.ecrireObjet(0, drl);
    }

    @Override
    public void faireUnAtterisageAutomatique() {
        System.out.println("ordre d'atterisagte auto recu");
    }

    @Override
    public void setInfoMindstorms(InfoMindstorms infoMindstorms) {
        info.infoMindstorms = new InfoMindstorms(infoMindstorms);
        System.out.println("" + infoMindstorms.angle);
    }

    @Override
    public boolean controllerLogin(Login l) {
        return wrkHttp.controlleConnexion(l);
    }
}
