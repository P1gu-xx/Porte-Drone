/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.Drone;

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

/**
 *
 * @author ramosdasilm
 */
public class Drone implements ImageListener, AltitudeListener, BatteryListener, WifiListener, AcceleroListener, IExceptionListener {

    private IEcouteurDrone ecouteurDrone;
    private IARDrone drone;
    private BufferedImage image;
    private boolean decoller;
    private boolean enVol;
    private boolean enDecollage;
    private final int vitesse = 20;
    private TimerTask task;
    private Timer timer;

    public Drone(IEcouteurDrone ecouteurDrone) {
        this.ecouteurDrone = ecouteurDrone;
        decoller = false;
        enVol = false;
        enDecollage = false;

        task = new TimerTask() {
            @Override
            public void run() {
                if (decoller) {
                    enVol = true;
                }
                enDecollage = false;
            }
        };

        timer = new Timer();
    }

    /**
     * va se connecter au drone et mettre tout les ecouteurs dont il a besoin.
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
     * @param x le pourcentage a la quelle le drone doit bouger en x (-100% a
     * 100%)
     * @param y le pourcentage a la quelle le drone doit bouger en y (-100% a
     * 100%)
     * @param z le pourcentage a la quelle le drone doit bouger en z (-100% a
     * 100%)
     * @param spin le pourcentage a la quelle le drone doit faire une rotation
     * (-100% a 100%)
     */
    public boolean bouger(int x, int y, int z, int spin) {
        boolean ok = false;
        if (enVol) {
            drone.getCommandManager().move(x, y, z, spin);
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
        if (drone != null && !enDecollage) {
            if (decoller) {
                enVol = false;
                drone.getCommandManager().landing();
            } else {
                enVol = false;
                drone.getCommandManager().takeOff();
            }
            decoller = !decoller;
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
        ecouteurDrone.droneAltitudeRecu(i);
    }

    @Override
    public void receivedExtendedAltitude(Altitude altd) {
    }

    @Override
    public void batteryLevelChanged(int i) {
        ecouteurDrone.droneNiveauDeBattrieRecu(i);
    }

    @Override
    public void voltageChanged(int i) {
    }

    @Override
    public void received(long l) {
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

}
