/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.reseau;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.imageio.ImageIO;

/**
 *
 * @author PeclatJ
 */
public class CommunicationVideo implements Runnable {

    public CommunicationVideo() {
        exit = false;
        donnees = new DatagramPacket(new byte[1024], 1024);
    }

    @Override
    public void run() {
        while (!exit) {
            if (client.isConnexion()) {
                checkForImage();
            }
        }
    }
    byte[] buffer = new byte[65536];
    boolean available;
    BufferedImage img;

    void checkForImage() {
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        try {
            System.out.println("debut");
            in.receive(p);
            System.out.println("packet recu");
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = p.getData();
        System.out.println("donner recuperer" + data.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(data);

        try {
            BufferedImage bimg = ImageIO.read(bais);
            ecouteur.imageRecu(bimg);
        } catch (Exception e) {
            e.printStackTrace();
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
