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

    public double position_x, position_y;

    public Echo(double position_x, double position_y) {
        this.position_x = position_x;
        this.position_y = position_y;
    }

}
