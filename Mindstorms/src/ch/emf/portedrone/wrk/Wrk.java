/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.DeplacementMindstorms;
import ch.emf.portedrone.beans.InfoMindstorms;
import ch.emf.portedrone.wrk.reseau.IEcouteurServeur;
import ch.emf.portedrone.wrk.reseau.Serveur;
import java.util.ArrayList;
import java.util.LinkedList;
import lejos.hardware.Battery;
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
        listeEcho = new LinkedList<>();
        Button.ENTER.addKeyListener(this);

        exit = false;
    }

    public void start() {
        serveur.start();

        InfoMindstorms info = new InfoMindstorms();
        info.deplacementMindstorms = new DeplacementMindstorms(0, 0);

        compteur = 0;

        while (!exit) {
            radar.update();
            if (radar.isEchoFound()) {
                if (radar.getEchoAngle() > -15 && radar.getEchoAngle() < 15) {
                    listeEcho.add(new Echo(radar.getEchoDistance(), Math.toRadians(radar.getEchoAngle())));
                }
            }

            for (int i = 0; i < listeEcho.size(); i++) {
                if (listeEcho.getFirst().isDead()) {
                    listeEcho.removeFirst();
                    continue;
                }
            }

            // Une fois sur 100
            if (compteur % 100 == 0) {
                info.angle = (float) Math.toRadians(radar.getRotation());
                info.batterie = Battery.getBatteryCurrent();
                info.echo = new ArrayList(listeEcho);
                info.deplacementMindstorms.vitesseRoueDroite = moteurs.getSpeedWheelRight();
                info.deplacementMindstorms.vitesseRoueGauche = moteurs.getSpeedWheelLeft();
                serveur.ecrireObjet(0, info);
            }

            Delay.msDelay(10);
            compteur++;
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
    private LinkedList<Echo> listeEcho;
    private int compteur;
}
