/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.Logins;
import ch.emf.portedrone.beans.Vols;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ramosdasilm
 */
public class Wrk {

    private WrkDB db;

    public Wrk() {
        db = WrkDB.getInstance();

    }

    public Logins controllerLogin(String login, String mdp) {
        System.out.println("controlle de login");
        return db.trouverLogin(login, mdp);
    }

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

    public static String convertDate(Date d) {
        return shortDateFormat.format(d);
    }

    private static DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT
    );

    ;

    public String enregistreVols(Vols vols) {
        db.enregistrerVol(vols);
        return "";
    }

}
