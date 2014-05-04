/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.serveur;

import ch.emf.portedrone.beans.Info;
import ch.emf.portedrone.beans.Login;
import ch.emf.portedrone.beans.drone.DeplacementDrone;
import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Objet qui permet d'écouter les ordre du client et de lui envoyer des infos.
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

    /**
     * Permet de creer un serveur de controle.
     *
     * @param ecouteur
     */
    public ServeurControle(IEcouteurServeurControle ecouteur) {
        super();
        running = true;
        loger = false;
        this.ecouteur = ecouteur;
        try {
            ss = new ServerSocket(PORT);
            start();
        } catch (IOException ex) {
            Logger.getLogger(ServeurControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * permet d'envoyer un beans info qui contient tout les infos a disposition
     * par le serveur au client.
     *
     * @param info le beans a envoyé
     */
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

    /**
     * fonction executer par le thread.
     */
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
                        ecouteur.faireBougerDrone((DeplacementDrone) ois.readObject());
                        break;
                    case 2:
                        ecouteur.changerLaCamera();
                        break;
                    case 3:
                        ecouteur.faireUnAtterisageAutomatique();
                        break;
                    case 4:
                        System.out.println("message de demande de deplacement recu");
                        ecouteur.faireBougerMindstorms((DeplacementMindstorms) ois.readObject());
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

    /**
     * fonction qui est appeler dans la boucle principal du thread qui va
     * attendre la connexion d'un client.
     */
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

    /**
     * fonction appeler apres qu'un client se connecte va controller les logins
     * de l'utilisateur. si le login et correcte va lui renvoyer true puis
     * passer en mode ecoute de commande sinon repart sur une authentification.
     * en cas d'exeption repart en mode attendre client.
     */
    public void authentification() {
        try {
            System.out.println("authentification");
            Login login = (Login) ois.readObject();
            System.out.println("authentification :  donnee recu");
            if (ecouteur.controllerLogin(login)) {
                System.out.println("connexion ok");
                loger = true;
                oos.writeBoolean(true);
                System.out.println("confirme");
                oos.flush();
            } else {
                oos.writeBoolean(false);
                oos.flush();
                authentification();
            }

        } catch (IOException ex) {
            System.out.println(ex);
            attendreConnexion();
        } catch (ClassNotFoundException ex) {
            System.out.println("impossible de convertir l'objet");
            attendreConnexion();
        }

    }

    /**
     * permet d'arreter le thread. va permetre de quitter la boucle infini.
     *
     * @param running
     */
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
