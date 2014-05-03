package ch.emf.portedrone.wrk.reseau;

import ch.emf.portedrone.beans.Login;
import ch.emf.portedrone.exception.ConnexionException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe réseau de l'application Cliente.
 * @author PeclatJ
 */
public class Client implements IClient {

    /**
     * Constructeur de la classe.
     */
    public Client() {
        communicationControle = new CommunicationControle();
        communicationVideo = new CommunicationVideo();

        communicationControle.setClient(this);
        communicationVideo.setClient(this);

        threadTCP = new Thread(communicationControle);
        threadUDP = new Thread(communicationVideo);

        threadTCP.start();
    }

    /**
     * Lance une connexion vers le serveur.
     * @param adresse L'adresse IP du serveur.
     * @throws ConnexionException Une erreur en cas d'échec.
     */
    public void connexion(String adresse) throws ConnexionException {
        this.adresse = adresse;
        try {
            Socket s = new Socket(adresse, PORT_TCP);

            inTCP = new ObjectInputStream(s.getInputStream());
            socketUDP = new DatagramSocket(8888);
            outTCP = new ObjectOutputStream(s.getOutputStream());

        } catch (IOException ex) {
            connexion = false;
            throw new ConnexionException("erreur", "de connexion au serveur");
        }
    }

    /**
     * Permet au client de s'authentifier sur le serveur.
     * @param login Le bean avec le login de l'utilisateur.
     * @return L'autorisation du serveur. true = authentification réussie. false = échec.
     * @throws ConnexionException En cas d'erreur d'authentification.
     */
    public boolean authentification(Login login) throws ConnexionException {
        try {
            ecrireObjet(login);
            boolean ok = inTCP.readBoolean();

            if (ok) {
                communicationControle.setIn(inTCP);
                communicationVideo.setDatagramSocket(socketUDP);
                connexion = true;
                threadUDP.start();
                return true;
            }
        } catch (IOException ex) {
            throw new ConnexionException("Erreur", "erreur de connexion");
        }
        return false;
    }

    /**
     * Permet au client d'envoyer un int au serveur.
     * @param i La valeur à envoyer au serveur.
     */
    public void ecrireInt(int i) {
        try {
            outTCP.writeInt(i);
            outTCP.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permet au client d'envoyer un objet serialisé au serveur.
     * @param objet l'objet à envoyer au serveur.
     */
    public void ecrireObjet(Object objet) {
        try {
            outTCP.writeObject(objet);
            outTCP.flush();
            System.out.println("objet envoye");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permet au client d'envoyer un message par UDP au serveur.
     * @param msg Le message à envoyer.
     * @throws ConnexionException L'erreur en cas d'échec.
     */
    public void ecrireUDP(String msg) throws ConnexionException {
        DatagramPacket donnees = null;
        try {
            donnees = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName(adresse), PORT_UDP);
        } catch (UnknownHostException ex) {
            throw new ConnexionException("Erreur de connexion", "Nom d'hôte du serveur inconnu.");
        }
        try {
            socketUDP.send(donnees);
        } catch (IOException ex) {
            System.out.println("L'écriture du flux UDP a été interrompue.");
            connexionInterrompue();
        }
    }

    /**
     * Indique au client qu'il doit couper les connexions.
     */
    public void exit() {
        communicationControle.exit();
        communicationVideo.exit();
        try {
            threadTCP.join();
            threadUDP.join();
        } catch (InterruptedException ex) {
            System.out.println("Impossible de joindre les threads.");
        }
    }

    @Override
    public void connexionInterrompue() {
        connexion = false;
    }

    @Override
    public boolean isConnexion() {
        return connexion;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    /**
     * Défini l'écouteur du client pour recevoir des information par réseau.
     * @param ecouteur L'écouteur.
     */
    public void setEcouteurReseau(IEcouteurReseau ecouteur) {
        communicationControle.setEcouteurReseau(ecouteur);
        communicationVideo.setEcouteurReseau(ecouteur);
    }

    /**
     * 
     */
    public static final int PORT_TCP = 55584;
    
    /**
     * 
     */
    public static final int PORT_UDP = 55584;
    
    /**
     * 
     */
    private Thread threadTCP;
    
    /**
     * 
     */
    private Thread threadUDP;
    
    /**
     * 
     */
    private CommunicationControle communicationControle;
    
    /**
     * 
     */
    private CommunicationVideo communicationVideo;
    private ObjectOutputStream outTCP;
    private ObjectInputStream inTCP;
    private String adresse;
    private boolean connexion;
    private Socket socketTCP;
    private DatagramSocket socketUDP;
}
