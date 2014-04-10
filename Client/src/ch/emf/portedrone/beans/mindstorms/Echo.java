/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.beans.mindstorms;

import java.io.Serializable;

/**
 *
 * @author ramosdasilm
 */
public class Echo implements Serializable {

    public double distance, angle;

    public Echo(double distance, double angle) {
        this.distance = distance;
        this.angle = angle;
    }

}
