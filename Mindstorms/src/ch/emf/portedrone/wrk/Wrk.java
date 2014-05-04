package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;
import ch.emf.portedrone.beans.mindstorms.InfoMindstorms;
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
 * Classe Worker de l'application.
 * @author PeclatJ
 */
public class Wrk implements IEcouteurServeur, KeyListener {

    /**
     * Constructeur de la classe, Met en place le matériel et initialise le serveur.
     */
    public Wrk() {
        serveur = new Serveur(this);
        moteurs = new Moteurs();
        radar = new Radar();
        listeEcho = new LinkedList<>();
        Button.ENTER.addKeyListener(this);

        exit = false;
    }

    /**
     * Lance le serveur, et démarre la boucle principale. 
     */
    public void start() {
        serveur.start();
        InfoMindstorms infoMindstorms = new InfoMindstorms(new DeplacementMindstorms(0, 0), new ArrayList<ch.emf.portedrone.beans.mindstorms.Echo>(), 0f, 0f);
        
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
            if (compteur % 2 == 0) {
                infoMindstorms.angle = (float) Math.toRadians(radar.getRotation());
                infoMindstorms.batterie = Battery.getBatteryCurrent();
                infoMindstorms.echo = new ArrayList();
                for(Echo echo : listeEcho) {
                    infoMindstorms.echo.add(new ch.emf.portedrone.beans.mindstorms.Echo(echo.distance, echo.angle));
                }
                infoMindstorms.deplacementMindstorms.vitesseRoueDroite = moteurs.getSpeedWheelRight();
                infoMindstorms.deplacementMindstorms.vitesseRoueGauche = moteurs.getSpeedWheelLeft();
                
                InfoMindstorms copy = new InfoMindstorms(infoMindstorms);
                serveur.ecrireObjet(0, copy);
            }

            Delay.msDelay(10);
            compteur++;
        }
    }

    /**
     * Indique au programme qu'il doit se terminer.
     */
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
