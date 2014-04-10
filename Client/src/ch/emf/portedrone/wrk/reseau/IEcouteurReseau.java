/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.reseau;

import ch.emf.portedrone.beans.Info;


/**
 *
 * @author PeclatJ
 */
public interface IEcouteurReseau {
    
    public void setInfo(Info info);
    public void reconnexion(String adresse);
}
