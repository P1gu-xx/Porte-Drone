/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import ctrl.Ctrl;
import ihm.pc.IhmPC;
import wrk.Wrk;

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
        Wrk wrk = new Wrk();
    }
}
