package ch.emf.portedrone.application;

import ch.emf.portedrone.ctrl.Ctrl;
import ch.emf.portedrone.ihm.Ihm;

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
        Ihm ihm = new Ihm();
        Ctrl ctrl = new Ctrl(ihm);
        ihm.setCtrl(ctrl);
        ctrl.start();
    }
}
