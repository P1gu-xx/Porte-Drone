/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.exception.ConnexionException;


/**
 *
 * @author PeclatJ
 */
public interface IWrkCtrl {
    
    public void connexion(String adresse) throws ConnexionException;
    
    /**
     *
     * @param login
     * @param motDePasse
     * @return
     * @throws ConnexionException
     */
    public void authentification(String login, String motDePasse) throws ConnexionException;

    public void update();
    
}
