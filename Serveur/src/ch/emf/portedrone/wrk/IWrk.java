/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

/**
 *
 * @author ramosdasilm
 */
public interface IWrk {

    /**
     * permet de démarer le traitement des donnée par le wrk. va envoyer les
     * info au client, va recuperer les infos sur les diferent appareil, va
     * afficher les infos dans l'ihm du serveur, ...
     *
     * @return un boolean
     */
    boolean start();

    /**
     * permet de demander au wrk de donner l'ordre a tout les thread de
     * s'arreter de faire atterire le drone de couper les communication avec le
     * client,...
     */
    void exit();

}
