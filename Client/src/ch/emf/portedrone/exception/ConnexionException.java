/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.exception;

/**
 *
 * @author PeclatJ
 */
public class ConnexionException extends Exception{
    
    public ConnexionException(String titre, String message) {
        this.titre = titre;
        this.message = message;
    }
    
    public String titre;
    public String message;
}
