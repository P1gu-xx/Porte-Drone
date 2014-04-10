package ch.emf.portdrone.wrk;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PeclatJ
 */
public class Radar {
    
    public Radar() {
        // Senseur
        irSensorPort = LocalEV3.get().getPort(IRSENSORPORT);
        irSensorModes = new EV3IRSensor(irSensorPort);
        irSensorProvider = irSensorModes.getMode("Seek");
        irSensorSample = new float[irSensorProvider.sampleSize()];
        
        // Moteur
        radarMotor = new EV3MediumRegulatedMotor(MOTOR_PORT);
        radarMotor.setSpeed(100);
        radarMotor.setAcceleration(200);
    }
    
    public void update() {
        // On met à jour les données
        fetch();
        
        // Bouge le moteur.
        radarMotor.rotateTo((Math.abs(radarRotation + (int)echoAngle + MIN_ANGLE) - MIN_ANGLE) % MAX_ANGLE, true);
    }
    
    private void fetch() {
        irSensorProvider.fetchSample(irSensorSample, 0);
        
        radarRotation = Math.abs(radarMotor.getTachoCount()) - 90;
        echoFound = !(irSensorSample[4] == 0 && irSensorSample[5] >= 100.0);
        echoDistance = irSensorSample[5] * 2 / 100;
        echoAngle = irSensorSample[4] * 9;
    }

    public float getEchoDistance() {
        return echoDistance;
    }

    public float getEchoAngle() {
        return echoAngle;
    }

    public boolean isEchoFound() {
        return echoFound;
    }
    
    public int getRotation() {
        return radarRotation;
    }
    
    private static final String IRSENSORPORT = "S1";
    private static final Port MOTOR_PORT = MotorPort.A;
    private static final int MIN_ANGLE = 0;
    private static final int MAX_ANGLE = 360;

    private Port irSensorPort;
    private SensorModes irSensorModes;
    private SampleProvider irSensorProvider;
    private float[] irSensorSample;

    private float echoDistance;
    private float echoAngle;
    private boolean echoFound;
    private int radarRotation;
    
    private RegulatedMotor radarMotor;
    private int positionMotor;
    
}
