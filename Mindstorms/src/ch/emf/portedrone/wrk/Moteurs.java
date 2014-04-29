/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.wrk;

import ch.emf.portedrone.beans.mindstorms.DeplacementMindstorms;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

/**
 *
 * @author PeclatJ
 */
public class Moteurs {
    
    public Moteurs() {
        wheelMotorLeft = new EV3LargeRegulatedMotor(MOTOR_PORT_LEFT);
        wheelMotorRight = new EV3LargeRegulatedMotor(MOTOR_PORT_RIGHT);
    }
    
    public void update(DeplacementMindstorms deplacement) {
        if(deplacement.vitesseRoueGauche > 5) {
            wheelMotorLeft.forward();
            wheelMotorLeft.setSpeed(deplacement.vitesseRoueGauche / 100 * VITESSE_MAX);
        } else if(deplacement.vitesseRoueGauche < -5) {
            wheelMotorLeft.backward();
            wheelMotorLeft.setSpeed(deplacement.vitesseRoueGauche / 100 * VITESSE_MIN);
        } else {
            wheelMotorLeft.stop(true);
            wheelMotorLeft.setSpeed(0);
        }
        
        if(deplacement.vitesseRoueDroite > 5) {
            wheelMotorRight.forward();
            wheelMotorRight.setSpeed(deplacement.vitesseRoueDroite / 100 * VITESSE_MAX);
        } else if(deplacement.vitesseRoueDroite < -5) {
            wheelMotorRight.backward();
            wheelMotorRight.setSpeed(deplacement.vitesseRoueDroite / 100 * VITESSE_MIN);
        } else {
            wheelMotorRight.stop(true);
            wheelMotorRight.setSpeed(0);
        }
    }
    
    public int getSpeedWheelLeft() {
        return wheelMotorLeft.getSpeed();
    }
    
    public int getSpeedWheelRight() {
        return wheelMotorRight.getSpeed();
    }
    
    private static final Port MOTOR_PORT_LEFT = MotorPort.B;
    private static final Port MOTOR_PORT_RIGHT = MotorPort.C;
    private static final int VITESSE_MAX = 720;
    private static final int VITESSE_MIN = -720;
    
    private RegulatedMotor wheelMotorLeft;
    private RegulatedMotor wheelMotorRight;
    
}
