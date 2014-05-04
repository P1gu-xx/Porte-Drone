/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.dao.JpaDao;
import ch.emf.dao.filtering.Search;
import ch.emf.dao.filtering.Search2;
import ch.emf.portedrone.beans.Logins;
import ch.emf.portedrone.beans.Vols;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ramosdasilm
 */
public class WrkDB {

    private static WrkDB bd;
    private ch.emf.dao.JpaDao jpaDao;

    /**
     * constructeur de la bd.
     */
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
     * @return un WrkBD.
     */
    public static WrkDB getInstance() {
        if (bd == null) {
            bd = new WrkDB();
        }
        return bd;
    }

    /**
     * Va faire une recherche dans la bd pour trouver le login.
     *
     * @param login le login rechercher.
     * @param mdp le mdp du login rechercher.
     * @return le login si les paramettre coressponde a une entré dans la bd
     * sinon un null.
     */
    public Logins trouverLogin(String login, String mdp) {
        Search2 s = new Search2("select p from Logins p");
        s.addFilterEqual("p.login", "" + login);
        s.addFilterEqual("p.password", "" + mdp);
        Logins l = null;
        try {
            l = (Logins) jpaDao.getSingleResult(s.getJpql(), s.getParams());
            System.out.println(l);
        } catch (NoResultException e) {
        } catch (Exception e) {
        }
        return l;
    }

    /**
     * Donne les 20 dernier vols.
     *
     * @return une list des 20 dernier vols.
     */
    public List<Vols> donnerLesVols() {
        List<Vols> lesVols = null;
        Search s = new Search(Vols.class);
        jpaDao.clear();

        s.setMaxResults(20);
        s.addSortDesc("tempsDepart");
        try {
            lesVols = jpaDao.getList(s);
            System.out.println(lesVols);
        } catch (NoResultException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return lesVols;
    }

    /**
     * va enregistrer le vol dans la bd.
     *
     * @param vol le vol a enregistrer.
     * @return le vol enregistrer.
     */
    public Vols enregistrerVol(Vols vol) {
        Vols create = jpaDao.create(vol);
        jpaDao.flush();
        return create;
    }

}
