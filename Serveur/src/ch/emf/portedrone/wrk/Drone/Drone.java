/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.Drone;

import ch.emf.portedrone.beans.drone.DeplacementDrone;
import ch.emf.portedrone.beans.drone.InfoDrone;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.LEDAnimation;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.CommandException;
import de.yadrone.base.exception.ConfigurationException;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.exception.NavDataException;
import de.yadrone.base.exception.VideoException;
import de.yadrone.base.navdata.AcceleroListener;
import de.yadrone.base.navdata.AcceleroPhysData;
import de.yadrone.base.navdata.AcceleroRawData;
import de.yadrone.base.navdata.Altitude;
import de.yadrone.base.navdata.AltitudeListener;
import de.yadrone.base.navdata.BatteryListener;
import de.yadrone.base.navdata.WifiListener;
import de.yadrone.base.video.ImageListener;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramosdasilm
 */
public class Drone implements ImageListener, AltitudeListener, BatteryListener, WifiListener, AcceleroListener, IExceptionListener {

    private IEcouteurDrone ecouteurDrone;
    private IARDrone drone;
    private BufferedImage image;
    private final int vitesse = 20;
    private TimerTask task;
    private Timer timer;
    private InfoDrone info;

    public Drone(IEcouteurDrone ecouteurDrone) {
        this.ecouteurDrone = ecouteurDrone;
        info = new InfoDrone(new DeplacementDrone(0, 0, 0, 0), 0, 0, 0, false, false, false, false);

        task = new TimerTask() {
            @Override
            public void run() {
                if (info.decoller) {
                    info.enVol = true;
                }
                info.enDecollage = false;
            }
        };

        timer = new Timer();
    }

    /**
     * va se connecter au drone et mettre tout les ecouteurs dont il a besoin.
     *
     * @return si il n'y a pas eu d'erreur retourn true sinon false.
     */
    public boolean connecter() {
        try {
            drone = new ARDrone();
            drone.start();
            drone.getCommandManager().setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
            drone.addExceptionListener(this);
            drone.getVideoManager().addImageListener(this);
            drone.getNavDataManager().addAltitudeListener(this);
            drone.getNavDataManager().addAcceleroListener(this);
            drone.getNavDataManager().addBatteryListener(this);
            drone.getNavDataManager().addWifiListener(this);

        } catch (Exception exc) {
            System.out.println("erreur lors de la creation du drone");
            return false;
        }
        return true;
    }

    /**
     * permet de changer de camera sur le drone.
     *
     * @return false si le drone n'est pas connecter sinon true.
     */
    public boolean changerCamera() {
        boolean ok = false;
        if (drone != null) {
            drone.toggleCamera();
            ok = true;
        }
        return ok;
    }

    /**
     * Permet de choisir la vitesse de déplacement en x,y,z et le spin. Une fois
     * une ordre donner le drone va l'appliquer jusqu'au prochain ordre.
     *
     * @param dd un bean avec les info de deplacement en x,y,z et spin
     */
    public boolean bouger(DeplacementDrone dd) {
        boolean ok = false;
        if (info.enVol) {
            info.deplacementDrone = dd;
            drone.getCommandManager().move(dd.vitesseX, dd.vitesseY, dd.vitesseZ, dd.spin);
            ok = true;
        }
        return ok;
    }

    /**
     * va donner l'ordre de decoller ou d'atterire. apres 4 secondes va
     * debloquer ou bloquer les commandes de deplacement puis réactiver le
     * decollage ou l'atterisage
     *
     * @return si il est en train de decoller ou d'atterir va renvoyer false
     * sinon si l'ordre a ete envoye retourne true.
     */
    public boolean decoller() {
        boolean ok = false;
        if (drone != null && !info.enDecollage) {
            if (info.decoller) {
                info.enVol = false;
                drone.getCommandManager().landing();
            } else {
                info.enVol = false;
                drone.getCommandManager().takeOff();
            }
            info.decoller = !info.decoller;
            timer.schedule(task, new Date(System.currentTimeMillis() + 4000));
            ok = true;
        }
        return ok;
    }

    @Override
    public void imageUpdated(BufferedImage bi) {
        image = bi;
        ecouteurDrone.droneImageRecu(image);
    }

    @Override
    public void receivedAltitude(int i) {
        info.hauteur = i;
        ecouteurDrone.droneAltitudeRecu(i);
    }

    @Override
    public void receivedExtendedAltitude(Altitude altd) {
    }

    @Override
    public void batteryLevelChanged(int i) {
        info.niveauDeBattrie = i;
        ecouteurDrone.droneNiveauDeBattrieRecu(i);
    }

    @Override
    public void voltageChanged(int i) {
    }

    @Override
    public void received(long l) {
        info.reseauWifi = l;
        ecouteurDrone.droneNiveauDeReseauWifiRecu(l);
    }

    @Override
    public void receivedRawData(AcceleroRawData ard) {
    }

    @Override
    public void receivedPhysData(AcceleroPhysData apd) {
    }

    @Override
    public void exeptionOccurred(ARDroneException exc) {
        if (exc instanceof ConfigurationException) {

        } else if (exc instanceof CommandException) {

        } else if (exc instanceof NavDataException) {

        } else if (exc instanceof VideoException) {
            drone.getVideoManager().reinitialize();
        }
    }

    public void stop() {
        if (drone != null) {
            drone.getCommandManager().landing();
            drone.stop();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
        }
    }

}
