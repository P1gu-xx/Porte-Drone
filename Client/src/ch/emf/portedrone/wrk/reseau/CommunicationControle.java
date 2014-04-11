/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.reseau;


import ch.emf.portedrone.beans.Info;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PeclatJ
 */
public class CommunicationControle implements Runnable{
    
    public CommunicationControle() {
        exit = false;
    }
    
    @Override
    public void run() {
        while(!exit) {
            if(client.isConnexion()) {
                try {
                    ecouteur.setInfo((Info)in.readObject());
                } catch (ClassNotFoundException ex) {
                    System.out.println("Le message n'est pas un objet Info.");
                } catch (IOException ex) {
                    System.out.println("La lecture du flux TCP a été interrompue.");
                    ecouteur.reconnexion(client.getAdresse());
                } 
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CommunicationControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void exit() {
        exit = true;
        try {
            in.close();
        } catch (IOException ex) {
            System.out.println("Impossible de fermer le flux TCP entrant.");
        }
    }
    
    public void setClient(IClient client) {
        this.client = client;
    }
    
    
    public void setEcouteurReseau(IEcouteurReseau ecouteur) {
        this.ecouteur = ecouteur;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
    
    private ObjectInputStream in;
    private boolean exit;
    private IClient client;
    private IEcouteurReseau ecouteur;
    
}
