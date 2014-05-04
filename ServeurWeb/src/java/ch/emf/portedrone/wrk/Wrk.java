/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.Logins;
import ch.emf.portedrone.beans.Vols;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ramosdasilm
 */
public class Wrk {

    /**
     * reference vers le wrkbd.
     */
    private WrkDB db;

    /**
     * objet qui permet de convertir des dates.
     */
    private static DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT
    );

    /**
     * Constructeur
     */
    public Wrk() {
        db = WrkDB.getInstance();
    }

    /**
     * Permet de d'aller controller le login dans la bd.
     *
     * @param login le login de l'utilisateur
     * @param mdp le mdp de l'utilisateur
     * @return retourne un login si il y a une entré sinon un null.
     */
    public Logins controllerLogin(String login, String mdp) {
        System.out.println("controlle de login");
        return db.trouverLogin(login, mdp);
    }

    /**
     * Permet de recuperer la list des 20 dernier vols
     *
     * @return un string sous forme de json
     */
    public String donnerListDesVols() {
        List<Vols> lesVols = db.donnerLesVols();
        if (lesVols == null) {
            lesVols = new ArrayList<>();
        }
        String msg = "[";
        for (Vols vols : lesVols) {
            msg += vols.toJson() + ",";
        }
        msg = msg.substring(0, msg.length() - 1);
        msg += "]";
        return msg;
    }

    /**
     * permet de convertir une date.
     *
     * @param d la date a convertir
     * @return un string avec la date donnée en parametre
     */
    public static String convertDate(Date d) {
        return shortDateFormat.format(d);
    }

    /**
     * Permet d'enregistrer un vol.
     *
     * @param vols un vol.
     * @return le vols avec la pk si le vol a été enregistrer.
     */
    public Vols enregistreVols(Vols vols) {
        return db.enregistrerVol(vols);
    }

}
