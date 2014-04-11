/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.dao.JpaDao;
import ch.emf.dao.filtering.Search;
import ch.emf.portedrone.beans.Logins;
import ch.emf.portedrone.beans.Vols;
import com.google.gson.Gson;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ramosdasilm
 */
public class WrkDB {

    private static WrkDB bd;
    private ch.emf.dao.JpaDao jpaDao;
    private Gson gson;

    private WrkDB() {
        try {
            jpaDao = new JpaDao();
            jpaDao.open("ServeurWebPU");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Permet de recuperer l'instance de la bd. si elle n'est pas creer va la
     * creer tout seul.
     *
     * @return
     */
    public static WrkDB getInstance() {
        if (bd == null) {
            bd = new WrkDB();
        }
        return bd;
    }

    public Logins trouverLogin(String email, String mdp) {
        Search s = new Search(Logins.class);
        s.addFilterEqual("login", email);
        s.addFilterEqual("password", mdp);
        Logins login = null;
        try {
            login = (Logins) jpaDao.getSingleResult(s);
        } catch (NoResultException e) {

        } catch (Exception e) {

        }
        return login;
    }

    public List<Vols> donnerLesVols() {
        List<Vols> lesVols = null;

        try {
            lesVols = jpaDao.getList(Vols.class);
            System.out.println(lesVols);
        } catch (NoResultException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return lesVols;
    }

}
