/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.reseau;

/**
 *
 * @author PeclatJ
 */
public interface IClient {

    public void connexionInterrompue();

    public boolean isConnexion();

    public String getAdresse();
}
