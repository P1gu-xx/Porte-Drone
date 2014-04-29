/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ihm;

import ch.emf.portedrone.beans.Info;
import java.awt.image.BufferedImage;

/**
 *
 * @author ramosdasilm
 */
public interface IIhm {

    /**
     * permet de rendre visible ou masquer l'ihm.
     *
     * @param visible
     */
    void afficher(boolean visible);

    void afficherInfoDrone(Info info);
    
    void afficherCameraDrone(BufferedImage img);
}
