/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.serveur;

/**
 *
 * @author ramosdasilm
 */
public abstract class Serveur extends Thread {

    protected int portEcoute;
    protected boolean running;

    public Serveur() {
        running = true;
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
