/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.application;

import ch.emf.portedrone.ctrl.Ctrl;
import ch.emf.portedrone.ihm.pc.IhmPC;
import ch.emf.portedrone.ihm.pc.IhmPCConnexion;
import ch.emf.portedrone.wrk.Wrk;



/**
 *
 * @author PeclatJ
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Ctrl ctrl = new Ctrl();
        IhmPC ihm = new IhmPC();
        IhmPCConnexion ihmConnexion = new IhmPCConnexion();
        Wrk wrk = new Wrk();
        
        ctrl.setIhm(ihm);
        ctrl.setIhmConnexion(ihmConnexion);
        ctrl.setWrk(wrk);
        ihm.setCtrl(ctrl);
        ihmConnexion.setCtrl(ctrl);
        wrk.setCtrl(ctrl);
        
        ctrl.start();
    }
}
