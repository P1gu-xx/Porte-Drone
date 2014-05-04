/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.serveur;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.imageio.ImageIO;

/**
 * Objet qui permet de streamer des flux video.
 *
 * @author ramosdasilm
 */
public class ServeurVideo extends Serveur {

    private IEcouteurServeurVideo ecouteur;
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private BufferedOutputStream bos;
    private ByteArrayOutputStream baStream;

    /**
     * Constructeur.
     *
     * @param ipDestination ip ou le flux video doit etre envoyé.
     * @param port le port ou le flux doit etre envoyé.
     * @throws UnknownHostException
     */
    public ServeurVideo(String ipDestination, int port) throws UnknownHostException {
        super();
        this.port = port;
        address = InetAddress.getByName(ipDestination);
        creerDatagramSocket();
        baStream = new ByteArrayOutputStream();
        bos = new BufferedOutputStream(baStream);
    }

    /**
     * Permet de creer un datagramSocket avec un timeout de 0 (pas de timeout).
     */
    public void creerDatagramSocket() {
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(0);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'envoyer un bufferdimage en udp.
     *
     * @param img l'image a envoyer.
     */
    public void envoyerDonnee(BufferedImage img) {
        System.out.println(img.getType());
        try {
            baStream.reset();
            ImageIO.write(img, "jpg", bos);
        } catch (IOException ex) {
            System.out.println("erreur ");
        }
        byte[] packet = baStream.toByteArray();
        System.out.println(packet.length);
        try {
            socket.send(new DatagramPacket(packet, packet.length, address, port));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
