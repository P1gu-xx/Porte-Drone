/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portdrone.wrk;

import ch.emf.portdrone.beans.DeplacementMindstorms;
import ch.emf.portdrone.beans.Echo;
import ch.emf.portdrone.wrk.reseau.IEcouteurServeur;
import ch.emf.portdrone.wrk.reseau.Serveur;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.utility.Delay;

/**
 *
 * @author PeclatJ
 */
public class Wrk implements IEcouteurServeur, KeyListener {

    public Wrk() {
        serveur = new Serveur(this);
        moteurs = new Moteurs();
        radar = new Radar();
        Button.ENTER.addKeyListener(this);

        exit = false;
    }

    public void start() {
        serveur.start();

        while (!exit) {
            if (radar.isEchoFound()) {
                if (radar.getEchoAngle() > -15 && radar.getEchoAngle() < 15) {
                    serveur.ecrireObjet(0, new Echo(radar.getEchoDistance(), Math.toRadians(radar.getEchoAngle())));
                    // Debug
                    //System.out.println("Echo a " + echoDistance + "m. " + echoAngle);
                }
            } else {
                // Debug
                //System.out.println("Pas de balise dans la zone.");
            }

            radar.update();
            serveur.ecrireObjet(1, Math.toRadians(radar.getRotation()));
            
            Delay.msDelay(10);
        }
    }

    @Override
    public void reseauDeplacementMindstorms(DeplacementMindstorms deplacement) {
        moteurs.update(deplacement);
    }

    @Override
    public void keyPressed(Key key) {
    }

    @Override
    public void keyReleased(Key key) {
        exit = true;
    }
    
    private Serveur serveur;
    private Moteurs moteurs;
    private Radar radar;
    private boolean exit;
}
