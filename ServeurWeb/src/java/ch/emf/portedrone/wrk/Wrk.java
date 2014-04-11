/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

/**
 *
 * @author ramosdasilm
 */
public class Wrk {

    private WrkDB db;

    public Wrk() {
        db = new WrkDB();
    }

    public boolean controllerLogin(String email, String mdp) {
        if (db.trouverLogin(email, mdp) != null) {
            return true;
        }
        return false;
    }
}
