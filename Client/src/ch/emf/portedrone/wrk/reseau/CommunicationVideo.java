/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author PeclatJ
 */
public class CommunicationVideo implements Runnable{

    public CommunicationVideo() {
        exit = false;
        donnees = new DatagramPacket(new byte[1024], 1024);
    }
    
    @Override
    public void run() {
        while(!exit) {
            if(client.isConnexion()) {
                try {
                    in.receive(donnees);
                    System.out.println(new String(donnees.getData()));
                } catch (IOException ex) {
                    System.out.println("La lecture du flux UDP a été interrompue.");
                    client.connexionInterrompue();
                }
            }
        }
    }
    
    public void exit() {
        exit = true;
        in.close();
    }
    
    public void setClient(IClient client) {
        this.client = client;
    }
    
    public void setDatagramSocket(DatagramSocket in) {
        this.in = in;
    }
    
    public void setEcouteurReseau(IEcouteurReseau ecouteur) {
        this.ecouteur = ecouteur;
    }
    
    private DatagramSocket in;
    private boolean exit;
    private IClient client;
    private DatagramPacket donnees;
    private IEcouteurReseau ecouteur;
    
}
