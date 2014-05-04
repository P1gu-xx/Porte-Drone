package ch.emf.portedrone.wrk.reseau;


import ch.emf.portedrone.beans.Info;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui gère la communication avec le serveur TCP.
 * @author PeclatJ
 */
public class CommunicationControle implements Runnable{
    
    /**
     * Constructeur de la classe.
     */
    public CommunicationControle() {
        exit = false;
    }
    
    @Override
    public void run() {
        while(!exit) {
            if(client.isConnexion()) {
                try {
                    System.out.println("Attend des messages");
                    ecouteur.setInfo((Info)in.readObject());
                    System.out.println("Message recu");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Le message n'est pas un objet Info.");
                } catch (IOException ex) {
                    System.out.println("La lecture du flux TCP a été interrompue.");
                    System.out.println(ex);
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
    
    /**
     * Informe le thread réseau UDP qu'il doit se terminer.
     */
    public void exit() {
        exit = true;
        try {
            in.close();
        } catch (IOException ex) {
            System.out.println("Impossible de fermer le flux TCP entrant.");
        }
    }
    
    /**
     * Permet de définir un écouteur vers le client,  
     * @param client L'écouteur.
     */
    public void setClient(IClient client) {
        this.client = client;
    }
    
    /**
     * Permet de définir l'écouteur du controleur vidéo.
     * @param ecouteur L'écouteur vidéo.
     */
    public void setEcouteurReseau(IEcouteurReseau ecouteur) {
        this.ecouteur = ecouteur;
    }

    /**
     * Permet de définir le flux entrant TCP.
     * @param in Le flux entrant.
     */
    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
    
    private ObjectInputStream in;
    private boolean exit;
    private IClient client;
    private IEcouteurReseau ecouteur;
    
}
