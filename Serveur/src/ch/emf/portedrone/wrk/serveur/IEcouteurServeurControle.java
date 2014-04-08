/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.emf.portedrone.wrk.serveur;

import ch.emf.portedrone.beans.drone.DeplacementDrone;
import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;

/**
 *
 * @author ramosdasilm
 */
public interface IEcouteurServeurControle {
    void faireBougerDrone(DeplacementDrone dd);
    void faireDecollerDrone();
    void changerLaCamera();
    void faireBougerRobotLego(DeplacementMindstorms drl);
    void faireUnAtterisageAutaumatique();
}
