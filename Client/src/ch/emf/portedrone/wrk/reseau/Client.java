/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.reseau;

import ch.emf.portedrone.beans.Login;
import ch.emf.portedrone.exception.ConnexionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author PeclatJ
 */
public class Client {

    public Client() {
        communicationControle = new CommunicationControle();
        communicationVideo = new CommunicationVideo();
    }

    public void connexion(String adresse) throws ConnexionException {
        connexion = false;
        this.adresse = adresse;
        try {
            socketTCP = new Socket(InetAddress.getByName(adresse), PORT_TCP);
            System.out.println("Connecté");
            socketUDP = new DatagramSocket();


            outTCP = new ObjectOutputStream(socketTCP.getOutputStream());
            inTCP = new ObjectInputStream(socketTCP.getInputStream());




        } catch (UnknownHostException ex) {
            throw new ConnexionException("Erreur de connexion", "Nom d'hôte du serveur inconnu.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new ConnexionException("Erreur de connexion", "Impossible de se connecter au serveur.");

        }
    }

    public void startEcouteur() {

        communicationControle.setClient(this);
        communicationControle.setInputStream(inTCP);
        
        threadTCP = new Thread(communicationControle, "Connexion TCP");
        threadUDP = new Thread(communicationVideo, "Connexion UDP");

        communicationVideo.setClient(this);
        communicationVideo.setDatagramSocket(socketUDP);
        
        threadTCP.start();
        threadUDP.start();

        connexion = true;
    }

    public boolean authentification(Login login) throws ConnexionException {
        ecrireObjet(login);
        try {
            return inTCP.readBoolean();
        } catch (IOException ex) {
            throw new ConnexionException("Erreur d'authentifiaction", "Impossible de s'authentifier.");
        }
    }

    public void ecrireObjet(Object objet) {
        try {
            outTCP.writeObject(objet);
            outTCP.flush();
        } catch (IOException ex) {
            System.out.println("L'écriture du flux TCP a été interrompue.");
            connexionInterrompue();
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

    public void connexionInterrompue() {
        connexion = false;
    }

    public boolean isConnexion() {
        return connexion;
    }

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
