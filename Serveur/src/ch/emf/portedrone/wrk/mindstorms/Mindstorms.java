package ch.emf.portedrone.wrk.mindstorms;

import ch.emf.portedrone.beans.mindstorms.InfoMindstorms;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe réseau qui communique avec le Mindstorms.
 * @author ramosdasilm
 */
public class Mindstorms extends Thread {

    /**
     * Constructeur de la classe.
     * @param ecouteur L'écouteur du réseau.
     */
    public Mindstorms(IEcouteurMindstorms ecouteur) {
        this.ecouteur = ecouteur;
        exit = false;
        connexion = false;
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                socket = new Socket(ADRESSE_IP, PORT);
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());
                connexion = true;
                while (connexion) {
                    int choix = in.readInt();
                    System.out.println(choix);
                    switch (choix) {
                        case 0:
                            try {
                                ecouteur.setInfoMindstorms((InfoMindstorms) in.readObject());
                                System.out.println("Message reçu");
                            } catch (ClassNotFoundException ex) {
                                System.out.println(ex.getMessage());
                                System.out.println("Le message n'est pas coherant.");
                            }
                            break;
                        default:
                            System.out.println("Le message à été mal envoyé.");
                    }
                }
            } catch (UnknownHostException ex) {
                System.out.println("Adresse du Mindsotrms inconnue.");
            } catch (IOException ex) {
                System.out.println("Impossible de se connecter au Mindstorms.");
                System.out.println(ex);
            }
            connexion = false;
        }
    }
    
    public void ecrireObjet(int type, Object objet) {
        if (!exit && connexion) {
            try {
                out.writeInt(type);
                out.writeObject(objet);
                out.flush();
            } catch (IOException ex) {
                System.out.println(ex);
                connexion = false;
            }
        }
    }

    /**
     * Indique au programme qu'il doit couper les connexions.
     */
    public void exit() {
        exit = true;
        try {
            if(socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Impossible de fermer le serveur.");
        }
    }
    
    /**
     * Adresse IP du Mindstorms.
     */
    public static final String ADRESSE_IP = "10.0.1.1";
    
    /**
     * Port de communication avec le Mindstorms.
     */
    public static final int PORT = 4000;
    private Socket socket;
    private IEcouteurMindstorms ecouteur;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean exit;
    private boolean connexion;
}
