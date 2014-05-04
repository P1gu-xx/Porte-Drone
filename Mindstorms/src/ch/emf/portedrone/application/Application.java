package ch.emf.portedrone.application;

import ch.emf.portedrone.wrk.Wrk;

/**
 * Classe principale de l'application.
 * @author PeclatJ
 */
public class Application {

    /**
     * Démarrage de l'application.
     * @param args useless
     */
    public static void main(String[] args) {
        
        Wrk wrk = new Wrk();
        wrk.start();
    }
}
