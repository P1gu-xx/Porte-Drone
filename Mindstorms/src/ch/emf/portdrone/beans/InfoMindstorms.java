/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portdrone.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ramosdasilm
 */
public class InfoMindstorms implements Serializable {

    public DeplacementMindstorms deplacementMindstorms;
    public ArrayList<Echo> echo;
    public float angle,batterie;

    public InfoMindstorms() {
        echo = new ArrayList<>();
    }

}
