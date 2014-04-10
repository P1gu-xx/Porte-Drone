/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.beans;

import java.io.Serializable;

/**
 *
 * @author ramosdasilm
 */
public class Login implements Serializable{

    public String email, mdp;

    public Login(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

}
