package ch.emf.portedrone.wrk.input.manette;

/**
 * Classe de manette spécifique à la manette PS3.
 * Contient une liste de constantes avec les indexes de tous les composants.
 * @author PeclatJ
 */
public class ManetteDualshock3 extends Manette {

    public static final int TRIANGLE = 0;
    public static final int ROND = 1;
    public static final int CROIX = 2;
    public static final int CARRE = 3;
    
    public static final int L1 = 4;
    public static final int R1 = 5;
    public static final int L2 = 6;
    public static final int R2 = 7;
    
    public static final int SELECT = 8;
    public static final int L3 = 9;
    public static final int R3 = 10;
    public static final int START = 11;
    public static final int PLAYSTATION = 12;
    
    public static final int HAT_SWITCH = 20;
    
    public static final int GYRO_Y = 24;
    public static final int GYRO_X = 25;
    
    public static final int ANALOG_RIGHT_Y = 23;
    public static final int ANALOG_RIGHT_X = 26;
    public static final int ANALOG_Z = 29;
    public static final int ANALOG_LEFT_Y = 27;
    public static final int ANALOG_LEFT_X = 28;
    
    public static final float CENTER = 0.0f;
    public static final float DOWN = 0.75f;
    public static final float DOWN_LEFT = 0.875f;
    public static final float DOWN_RIGHT = 0.625f;
    public static final float LEFT = 1.0f;
    public static final float RIGHT = 0.5f;
    public static final float UP = 0.25f;
    public static final float UP_LEFT = 0.125f;
    public static final float UP_RIGHT = 0.375f;
    
    /**
     * Constructeur de la classe.
     */
    public ManetteDualshock3() {
        super();
    }
}