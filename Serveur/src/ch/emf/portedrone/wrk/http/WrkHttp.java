/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.http;

import ch.emf.portedrone.beans.Login;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramosdasilm
 */
public class WrkHttp {

    public WrkHttp() {
        // First set the default cookie manager.
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    public boolean controlleConnexion(Login login) {
        try {
            String url = "http://localhost:8080/ServeurWeb/Index?action=connexion&login=" + login.email + "&mdp=" + login.mdp + "";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response);
            in.close();
            if (response.toString().equals("{connexion:true}")) {
                enregistrerVol(new Date(), new Date());
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public void enregistrerVol(Date dateDecolage, Date dateAtterisage) {
        try {
            String url = "http://localhost:8080/ServeurWeb/Index?action=enregistrerVols&dateDecollage=" + dateDecolage.getTime() + "&dateAtterisage=" + dateAtterisage.getTime();
            System.out.println("URL :" + url);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.getInputStream();
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

}
