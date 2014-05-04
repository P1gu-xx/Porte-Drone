/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk.http;

import ch.emf.portedrone.beans.Login;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 *
 * @author ramosdasilm
 */
public class WrkHttp {

    public WrkHttp() {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    /**
     * permet de faire une requette http au serveur glasfish pour qu'il
     * controlle le login recu du client.
     *
     * @param login le login du client.
     * @return si le login est correcte et une session et démaré entre le
     * serveur et le glassfish.
     */
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

    /**
     * permet d'envoyer une requette d'enregistrement de vol. fonctionne
     * seulement si on est logé sur le serveur glassfish.
     *
     * @param dateDecolage la date du décollage.
     * @param dateAtterisage la date d'atterisage.
     */
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
