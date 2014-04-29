/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author PeclatJ
 */
public class Client implements IClient {

    public Client() {
        communicationControle = new CommunicationControle();
        communicationVideo = new CommunicationVideo();

        communicationControle.setClient(this);
        communicationVideo.setClient(this);

        threadTCP = new Thread(communicationControle);
        threadUDP = new Thread(communicationVideo);

        threadTCP.start();
        threadUDP.start();
    }

    public void connexion(String adresse) throws ConnexionException {
        this.adresse = adresse;
        try {
            Socket s = new Socket(adresse, PORT_TCP);

            inTCP = new ObjectInputStream(s.getInputStream());

            outTCP = new ObjectOutputStream(s.getOutputStream());

        } catch (IOException ex) {
            connexion = false;
            throw new ConnexionException("erreur", "de connexion au serveur");
        }
    }

    public boolean authentification(Login login) throws ConnexionException {
        try {
            ecrireObjet(login);
            boolean ok = inTCP.readBoolean();

            if (ok) {
                communicationControle.setIn(inTCP);
                communicationVideo.setDatagramSocket(socketUDP);
                connexion = true;
                return true;
            }
        } catch (IOException ex) {
            throw new ConnexionException("Erreur", "erreur de connexion");
        }
        return false;
    }

    public void ecrireInt(int i) {
        try {
            outTCP.writeInt(i);
            outTCP.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ecrireObjet(Object objet) {
        try {
            outTCP.writeObject(objet);
            outTCP.flush();
            System.out.println("objet envoye");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public void setEcouteurReseau(IEcouteurReseau ecouteur) {
        communicationControle.setEcouteurReseau(ecouteur);
        communicationVideo.setEcouteurReseau(ecouteur);
    }


    
    
    public static final int PORT_TCP = 55584;
    public static final int PORT_UDP = 55584;
    private Thread threadTCP, threadUDP;
    private CommunicationControle communicationControle;
    private CommunicationVideo communicationVideo;
    private ObjectOutputStream outTCP;
    private ObjectInputStream inTCP;
    private String adresse;
    private boolean connexion;
    private Socket socketTCP;
    private DatagramSocket socketUDP;
}
