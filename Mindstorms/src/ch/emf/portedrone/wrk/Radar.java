package ch.emf.portedrone.wrk;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

/**
 * Classe qui gère le capteur infrarouge ainsi que le moteur du radar.
 * @author PeclatJ
 */
public class Radar {

    /**
     * Constructeur de la classe, initialise le matériel.
     */
    public Radar() {
        // Senseur
        irSensorPort = LocalEV3.get().getPort(IRSENSORPORT);
        irSensorModes = new EV3IRSensor(irSensorPort);
        irSensorProvider = irSensorModes.getMode("Seek");
        irSensorSample = new float[irSensorProvider.sampleSize()];

        // Moteur
        radarMotor = new EV3MediumRegulatedMotor(MOTOR_PORT);
        radarMotor.setSpeed(350);
        radarMotor.setAcceleration(30);
    }

    /**
     * Met à jour les informations lue par le radar et modifie sa position grâce au moteur.
     */
    public void update() {
        // On met a jour les donnees
        fetch();

        // Bouge le moteur.
        if (echoFound) {
            //radarMotor.rotateTo((Math.abs(radarRotation + (int)echoAngle + MIN_ANGLE) - MIN_ANGLE) % MAX_ANGLE, true);
            if (Math.abs(echoAngle) > 10) {
                radarMotor.rotate((int) echoAngle, true);
                System.out.println("echo angle:" + echoAngle);
            } else {
                radarMotor.flt(true);
            }
        } else {
            radarMotor.flt(true);
        }
    }

    /**
     * Met à jour les information captées par le capteur infrarouge.
     */
    private void fetch() {
        irSensorProvider.fetchSample(irSensorSample, 0);

        radarRotation = Math.abs(radarMotor.getTachoCount()) - 90;
        echoFound = !(irSensorSample[4] == 0 && irSensorSample[5] >= 100.0);
        echoDistance = irSensorSample[5] * 2 / 100;
        echoAngle = irSensorSample[4] * 9;
    }

    /**
     * Permet d'obtenir la distance en mètre qu'il y a entre l'écho et le radar.
     * @return La distance en mètre.
     */
    public float getEchoDistance() {
        return echoDistance;
    }

    /**
     * Permet d'obtenir l'angle en degré formé par l'écho.
     * @return L'angle en dergé.
     */
    public float getEchoAngle() {
        return echoAngle;
    }

    /**
     * Permet de savor si un écho est détecté.
     * @return true si un écho est détecté.
     */
    public boolean isEchoFound() {
        return echoFound;
    }

    /**
     * Permet d'obtenir l'angle en degré défini par l'orientation du moteur du radar. 
     * @return L'angle en degré.
     */
    public int getRotation() {
        return radarRotation;
    }

    private static final String IRSENSORPORT = "S1";
    private static final Port MOTOR_PORT = MotorPort.A;
    private static final int MIN_ANGLE = 180;
    private static final int MAX_ANGLE = -180;

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
