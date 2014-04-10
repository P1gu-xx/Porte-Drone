/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portdrone.wrk.reseau;

import ch.emf.portdrone.beans.DeplacementMindstorms;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author PeclatJ
 */
public class Serveur extends Thread {

    public Serveur(IEcouteurServeur ecouteur) {
        super();
        this.ecouteur = ecouteur;
        connexion = false;
        try {
            serveur = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println("Impossible d'initialiser le serveur.");
            exit = true;
        }
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                client = serveur.accept();
                try {
                    out = new ObjectOutputStream(client.getOutputStream());
                    in = new ObjectInputStream(client.getInputStream());
                    connexion = true;
                    while (!connexion) {
                        try {
                            switch (in.readInt()) {
                                case 0:
                                    ecouteur.reseauDeplacementMindstorms((DeplacementMindstorms) in.readObject());
                                    break;
                                default:
                                    System.out.println("Le message à été mal envoyé.");
                            }
                        } catch (IOException ex) {
                            System.out.println("Le flux entrant TCP a été interrompu.");
                            connexion = false;
                        } catch (ClassNotFoundException ex) {
                            System.out.println("Le message n'est pas coherant.");
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Impossible de récuperer le flux sortant.");
                    connexion = false;
                }
            } catch (IOException ex) {
                System.out.println("Impossible d'accepter des clients.");
                exit = true;
                connexion = false;
            }
        }
    }

    public void exit() {
        exit = true;
        try {
            serveur.close();
        } catch (IOException ex) {
            System.out.println("Impossible de fermer le serveur.");
        }
    }

    public void ecrireObjet(int type, Object objet) {
        if (!exit && connexion) {
            try {
                out.writeInt(type);
                out.writeObject(objet);
                out.flush();
            } catch (IOException ex) {
                connexion = false;
            }
        }
    }
    
    private static final int PORT = 4000;
    
    private IEcouteurServeur ecouteur;
    private ServerSocket serveur;
    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean exit;
    private boolean connexion;
}
