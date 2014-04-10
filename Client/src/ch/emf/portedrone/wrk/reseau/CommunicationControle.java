/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.reseau;


import ch.emf.portedrone.beans.Info;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

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
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public void setInputStream(InputStream in) {
        try {
            this.in = new ObjectInputStream(in);
        } catch (IOException ex) {
            System.out.println("Impossible de créer le lecteur d'objet.");
        }
    }
    
    public void setEcouteurReseau(IEcouteurReseau ecouteur) {
        this.ecouteur = ecouteur;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
    
    

    private ObjectInputStream in;
    private boolean exit;
    private Client client;
    private IEcouteurReseau ecouteur;
    
}
