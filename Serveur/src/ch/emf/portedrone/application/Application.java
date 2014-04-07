/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.application;

import ch.emf.portedrone.ctrl.Ctrl;
import ch.emf.portedrone.ihm.Ihm;

/**
 *
 * @author PeclatJ
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        Ctrl ctrl = new Ctrl(ihm);
        ihm.setCtrl(ctrl);
        ctrl.start();
    }
}
