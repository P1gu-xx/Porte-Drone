package ch.emf.portedrone.wrk.reseau;

import java.awt.image.BufferedImage;

/**
 * Interface de communication depuis le Worker vers le réseau client.
 * @author PeclatJ
 */
public interface IClient {

    /**
     * Donne l'ordre au client de couper la connexion.
     */
    public void connexionInterrompue();

    /**
     * Permet de savoir si le client est connecté.
     * @return true si le client est connecté et false si il ne l'est pas.
     */
    public boolean isConnexion();

    /**
     * Permet d'obtenir l'adresse du serveur au quel on est connecté.
     * @return L'adresse du serveur.
     */
    public String getAdresse();

}
