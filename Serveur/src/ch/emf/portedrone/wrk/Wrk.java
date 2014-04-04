/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.ctrl.ICtrlWrk;
import ch.emf.portedrone.wrk.Drone.Drone;
import ch.emf.portedrone.wrk.Drone.IEcouteurDrone;
import ch.emf.portedrone.wrk.RobotLego.IEcouteurRobotLego;
import ch.emf.portedrone.wrk.RobotLego.RobotLego;
import ch.emf.portedrone.wrk.http.WrkHttp;
import ch.emf.portedrone.wrk.serveur.IEcouteurServeurControle;
import ch.emf.portedrone.wrk.serveur.IEcouteurServeurVideo;
import ch.emf.portedrone.wrk.serveur.ServeurControle;
import ch.emf.portedrone.wrk.serveur.ServeurVideo;

/**
 *
 * @author ramosdasilm
 */
public class Wrk implements IWrk, IEcouteurDrone, IEcouteurRobotLego, IEcouteurServeurControle, IEcouteurServeurVideo {

    private ICtrlWrk Ctrl;
    private Drone drone;
    private RobotLego robotLego;
    private ServeurVideo serveurVideo;
    private ServeurControle serveurControle;
    private WrkHttp wrkHttp;

}
