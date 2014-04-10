/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portdrone.wrk;

import ch.emf.portdrone.beans.DeplacementMindstorms;
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
            wheelMotorLeft.setSpeed(deplacement.vitesseRoueGauche);
        } else if(deplacement.vitesseRoueGauche < -5) {
            wheelMotorLeft.backward();
            wheelMotorLeft.setSpeed(deplacement.vitesseRoueGauche);
        }
        
        if(deplacement.vitesseRoueDroite > 5) {
            wheelMotorRight.forward();
            wheelMotorRight.setSpeed(deplacement.vitesseRoueDroite);
        } else if(deplacement.vitesseRoueDroite < -5) {
            wheelMotorRight.backward();
            wheelMotorRight.setSpeed(deplacement.vitesseRoueDroite);
        }
    }
    
    private static final Port MOTOR_PORT_LEFT = MotorPort.B;
    private static final Port MOTOR_PORT_RIGHT = MotorPort.C;
    
    private RegulatedMotor wheelMotorLeft;
    private RegulatedMotor wheelMotorRight;
    
}
