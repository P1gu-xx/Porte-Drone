/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portdrone.application;

import ch.emf.portdrone.wrk.Wrk;

/**
 *
 * @author PeclatJ
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Wrk wrk = new Wrk();
        wrk.start();
    }
}
