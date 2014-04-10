/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.beans.drone.DeplacementDrone;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramosdasilm
 */
public class Wrk implements IWrk, IEcouteurDrone, IEcouteurMindstorms, IEcouteurServeurControle, IEcouteurServeurVideo {

    private final int portEcoute = 55584;
    private ICtrlWrk ctrl;
    private Drone drone;
    private Mindstorms mindstorms;
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
        serveurControle.setRunning(false);
        drone.stop();
    }

    public void traiterLesDonnees() {

        float i = 0;
        while (running) {
            InfoMindstorms infoMindstorms = new InfoMindstorms();
            infoMindstorms.batterie = 2.5f;
            infoMindstorms.angle = 90;
            infoMindstorms.echo.add(new Echo(0.0, 20.0));
            infoMindstorms.echo.add(new Echo(0.25, 20.0));
            infoMindstorms.echo.add(new Echo(0.5, 20.0));
            infoMindstorms.echo.add(new Echo(0.75, 20.0));
            infoMindstorms.echo.add(new Echo(1, 20.0));
            infoMindstorms.deplacementMindstorms = new DeplacementMindstorms(80, 95);
            i = i + 0.01f;
            infoMindstorms.angle = i;
            info = new Info();
            info.infoMindstorms = infoMindstorms;
            info.infoDrone = drone.getInfo();
            ctrl.nouvelleInfo(info);

            serveurControle.envoyerInfo(info);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    @Override
    public void faireBougerDrone(DeplacementDrone dd) {

    }

    @Override
    public void faireDecollerDrone() {

    }

    @Override
    public void changerLaCamera() {

    }

    @Override
    public void faireBougerRobotLego(DeplacementMindstorms drl) {

    }

    @Override
    public void faireUnAtterisageAutaumatique() {

    }

}
