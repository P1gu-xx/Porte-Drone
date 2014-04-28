/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.serveur;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.beans.Login;
import ch.emf.portedrone.beans.drone.DeplacementDrone;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramosdasilm
 */
public class ServeurControle extends Serveur {

    private final int PORT = 55584;

    private IEcouteurServeurControle ecouteur;
    private ServerSocket ss;
    private Socket s;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean loger;

    public ServeurControle() {
        super();
        loger = false;
        try {
            ss = new ServerSocket(PORT);
            start();
        } catch (IOException ex) {
            Logger.getLogger(ServeurControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void envoyerInfo(Info info) {
        if (oos != null && loger) {
            try {
                Info copy = new Info(info);
                oos.writeObject(copy);
                oos.flush();

            } catch (IOException ex) {

            }
        }
    }

    @Override
    public void run() {
        super.run();
        attendreConnexion();
        while (running) {
            try {
                switch (ois.readInt()) {
                    case 0:
                        ecouteur.faireDecollerDrone();
                        break;
                    case 1:
                        ecouteur.faireBougerDrone((DeplacementDrone)ois.readObject());
                        break;
                    case 2:
                        ecouteur.changerLaCamera();
                        break;
                    case 3:
                        ecouteur.faireUnAtterisageAutomatique();
                        break;
                    case 4:

                        break;
                    case 5:

                        break;

                    default:
                        throw new AssertionError();
                }

            } catch (IOException ex) {
                loger = false;
                attendreConnexion();
            } catch (ClassNotFoundException ex) {
                System.out.println("impossible de convertir l'objet");
            }
        }
    }

    public void attendreConnexion() {
        try {
            s = ss.accept();
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());
            authentification();
        } catch (IOException ex) {
            attendreConnexion();
        }
    }

    public void authentification() {
        try {
            System.out.println("authentification");
            Login login = (Login) ois.readObject();
            System.out.println("authentification :  donnee recu");
            if ("admin".equals(login.email) && "admin".equals(login.mdp)) {
                System.out.println("connexion ok");
                loger = true;
                oos.writeBoolean(true);
                System.out.println("confirme");
                oos.flush();
            } else {
                oos.writeBoolean(false);
                authentification();
                oos.flush();
            }

        } catch (IOException ex) {
            attendreConnexion();
        } catch (ClassNotFoundException ex) {
            System.out.println("impossible de convertir l'objet");
            attendreConnexion();
        }

    }

    @Override
    public void setRunning(boolean running) {
        try {
            this.running = running;
            if (s != null) {
                s.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServeurControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
