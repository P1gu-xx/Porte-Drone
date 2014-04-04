/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.Drone;

import java.awt.image.BufferedImage;

/**
 *
 * @author ramosdasilm
 */
public interface IEcouteurDrone {

    /**
     * fonction qui est appeler lorsque le drone envoye une image.
     * @param img l'image filmer par le drone
     */
    void droneImageRecu(BufferedImage img);

    /**
     * fonction qui est appeler pour annoncer le changement d'altitude.
     * @param alt l'altitude en mm
     */
    void droneAltitudeRecu(int alt);

    /**
     * fonction qui est appeler pour annoncer un changement de niveau de battrie.
     * @param i le niveau actuelle.
     */
    void droneNiveauDeBattrieRecu(int i);

    /**
     * fonction qui est appeler pour annoncer un changement au niveau du wifi.
     * @param l le niveau de reseau
     */
    void droneNiveauDeReseauWifiRecu(long l);

    /**
     * est appeller lors que le drone est déconnecter.
     */
    void droneDeconnecter();
}
