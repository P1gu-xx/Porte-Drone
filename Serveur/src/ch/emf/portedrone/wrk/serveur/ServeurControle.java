/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.serveur;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.beans.drone.DeplacementDrone;
import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramosdasilm
 */
public class ServeurControle extends Serveur {

    private IEcouteurServeurControle ecouteur;
    private ServerSocket ss;
    private Socket s;
    private DataOutputStream dos;
    private DataInputStream dis;

    public ServeurControle(int portEcoute) {
        super();
        this.portEcoute = portEcoute;
        try {
            ss = new ServerSocket(portEcoute);
            start();
        } catch (IOException ex) {
            Logger.getLogger(ServeurControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void envoyerInfo(Info info) {
        
    }

    @Override
    void messageRecu(int i) {
        try {
            switch (i) {
                case 0://faire decoller ou atterire le drone
                    ecouteur.faireDecollerDrone();
                    break;
                case 1://faire bouger le drone
                    ecouteur.faireBougerDrone(new DeplacementDrone(dis.readInt(), dis.readInt(), dis.readInt(), dis.readInt()));

                    break;
                case 2://changer la camera du drone
                    ecouteur.changerLaCamera();
                    break;
                case 3://faire bouger le robot lego
                    ecouteur.faireBougerRobotLego(new DeplacementMindstorms(dis.readInt(), dis.readInt()));
                    break;
                case 4://demander atterisage automatique
                    ecouteur.faireUnAtterisageAutaumatique();
                    break;
                default:
            }
        } catch (IOException ex) {
            attendreConnexion();
        }

    }

    @Override
    public void run() {
        super.run();
        attendreConnexion();
        while (running) {
            try {
                messageRecu(dis.readInt());
            } catch (IOException ex) {
                attendreConnexion();
            }
        }
    }

    public void attendreConnexion() {
        try {
            s = ss.accept();
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            authentification();
        } catch (IOException ex) {
            attendreConnexion();
        }
    }

    public void authentification() {
        try {
            String email = dis.readUTF();
            System.out.println(email);
            String mdp = dis.readUTF();
            if ("admin".equals(email) && "admin".equals(mdp)) {
                System.out.println("connexion ok");
            }
        } catch (IOException ex) {
            attendreConnexion();
        }

    }

}
