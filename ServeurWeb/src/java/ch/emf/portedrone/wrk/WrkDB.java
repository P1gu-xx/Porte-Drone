/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.Logins;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ramosdasilm
 */
public class WrkDB {

    private EntityManagerFactory emf;
    private EntityManager em;

    public WrkDB() {
        emf = Persistence.createEntityManagerFactory("ServeurWebPU");
        em = emf.createEntityManager();
    }

    public Logins trouverLogin(String email, String mdp) {
        Query query = em.createQuery("select p from Logins p where p.login='" + email + "' and p.password='" + mdp + "'");
        Logins login = null;
        try {
            login = (Logins) query.getSingleResult();
        } catch (NoResultException e) {
            
        }catch(Exception e){
            
        }
        return login;
    }
    
    

}
