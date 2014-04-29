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

    public ServeurVideo(String ipDestination, int port) throws UnknownHostException {
        super();
        this.portEcoute = port;
        address = InetAddress.getByName(ipDestination);
        creerDatagramSocket();
        baStream = new ByteArrayOutputStream();
        bos = new BufferedOutputStream(baStream);
    }

    public void creerDatagramSocket() {
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(0);
            this.port = port;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

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
