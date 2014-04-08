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
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

    private IEcouteurServeurControle ecouteur;
    private ServerSocket ss;
    private Socket s;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

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
        if (oos != null) {
            try {
                oos.writeObject(info);
            } catch (IOException ex) {
                attendreConnexion();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        attendreConnexion();
        while (running) {
            try {
                ois.readObject();
            } catch (IOException ex) {
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

            Login login = (Login) ois.readObject();

            if ("admin".equals(login.email) && "admin".equals(login.mdp)) {
                System.out.println("connexion ok");
            }
        } catch (IOException ex) {
            attendreConnexion();
        } catch (ClassNotFoundException ex) {
            System.out.println("impossible de convertir l'objet");
            attendreConnexion();
        }

    }

}
