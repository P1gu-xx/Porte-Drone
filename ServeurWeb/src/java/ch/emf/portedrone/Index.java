/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone;

import ch.emf.portedrone.beans.Logins;
import ch.emf.portedrone.beans.Vols;
import ch.emf.portedrone.wrk.Wrk;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramosdasilm
 */
@WebServlet(name = "Index", urlPatterns = {"/Index"})
public class Index extends HttpServlet {

    private Wrk wrk;

    public Index() {
        wrk = new Wrk();

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String action = request.getParameter("action");

            switch (action) {
                case "connexion":
                    Logins controllerLogin = wrk.controllerLogin(request.getParameter("login"), request.getParameter("mdp"));
                    if (controllerLogin != null) {
                        request.getSession().setAttribute("connecter", controllerLogin.getPkLogin());
                        out.print("{connexion:true}");
                    } else {
                        out.print("{connexion:false}");
                    }
                    break;
                case "deconnexion":

                    request.getSession().setAttribute("connecter", null);

                    break;
                case "lesVols":
                    String msg = wrk.donnerListDesVols();
                    System.out.println(msg);
                    out.print(msg);
                    break;
                case "enregistrerVols":
                    System.out.println("SESSSION : " + request.getSession().getAttribute("connecter"));
                    if (request.getSession().getAttribute("connecter") != null) {
                        Vols reponse = wrk.enregistreVols(
                                new Vols(0,
                                        new Date(
                                                new Long(request.getParameter("dateDecollage"))),
                                        new Date(
                                                new Long(request.getParameter("dateAtterisage"))),
                                        0,
                                        new Logins(
                                                (int) request.getSession().getAttribute("connecter"))));
                        
                        out.print((reponse!=null)?"{enregistrement:true}":"{enregistrement:false}");
                    } else {
                        out.print("erreur");
                    }
                    break;
                default:
                    out.print("erreur");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
