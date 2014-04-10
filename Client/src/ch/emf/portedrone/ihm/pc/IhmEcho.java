/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ihm.pc;

import java.awt.Graphics;

/**
 *
 * @author Jonathan
 */
public class IhmEcho {

    private double position_x;
    private double position_y;

    public IhmEcho(double distance, double angle) {
        position_x = Math.cos(angle) * distance;
        position_y = Math.sin(angle) * distance;
    }

    public void update(Graphics g, int weight, int height) {
        g.fillArc((int) (weight / 2 + position_x * weight / 2) - 3, (int) (height / 2 + position_y * height / 2) - 3, 6, 6, 0, 360);
    }
}
