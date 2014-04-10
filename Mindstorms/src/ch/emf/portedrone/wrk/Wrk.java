/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.DeplacementMindstorms;
import ch.emf.portedrone.beans.Echo;
import ch.emf.portedrone.wrk.reseau.IEcouteurServeur;
import ch.emf.portedrone.wrk.reseau.Serveur;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            //Delay.msDelay(1);
        }
        
        exit();
    }
    
    public void exit() {
        exit = true;
        try {
            serveur.exit();
            serveur.join();
        } catch (InterruptedException ex) {
            System.out.println("Impossible de joindre le thread");
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
        exit();
    }
    
    private Serveur serveur;
    private Moteurs moteurs;
    private Radar radar;
    private boolean exit;
}
