package ch.emf.portedrone.wrk.reseau;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.imageio.ImageIO;

/**
 * Classe qui gère la commiunication avec le serveur UDP.
 * @author PeclatJ
 */
public class CommunicationVideo implements Runnable {

    /**
     * Constructeur de la classe.
     */
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

    /**
     * Attend et affiche un packet UDP contenant une image.
     */
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

    /**
     * Informe le thread réseau UDP qu'il doit se terminer.
     */
    public void exit() {
        exit = true;
        in.close();
    }

    /**
     * Permet de définir un écouteur vers le client,  
     * @param client L'écouteur.
     */
    public void setClient(IClient client) {
        this.client = client;
    }

    /**
     * Permet de définir le flux entrant UDP.
     * @param in Le flux entrant.
     */
    public void setDatagramSocket(DatagramSocket in) {
        this.in = in;
    }

    /**
     * Permet de définir l'écouteur du controleur vidéo.
     * @param ecouteur L'écouteur vidéo.
     */
    public void setEcouteurReseau(IEcouteurReseau ecouteur) {
        this.ecouteur = ecouteur;
    }

    private DatagramSocket in;
    private boolean exit;
    private IClient client;
    private DatagramPacket donnees;
    private IEcouteurReseau ecouteur;

}
