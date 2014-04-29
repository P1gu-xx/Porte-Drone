package ch.emf.portedrone.beans;

import java.io.Serializable;

/**
 * Bean
 * @author ramosdasilm
 */
public class Login implements Serializable{

    public String email, mdp;

    public Login(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }
    
    public Login(Login login) {
        email = login.email;
        mdp = login.mdp;
    }

}
