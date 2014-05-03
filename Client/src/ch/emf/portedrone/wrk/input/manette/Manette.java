package ch.emf.portedrone.wrk.input.manette;

import net.java.games.input.*;

/**
 * Classe qui contrôle les manettes de jeu en générale.
 * @author PeclatJ
 */
public class Manette {

    /**
     * Le contrôleur de la manette
     */
    private Controller controller;
    
    /**
     * La liste des compostants (boutons et sticks analogiques) du contrôleur de jeu.
     */
    protected Component[] components;
    
    /**
     * Défini si la manette est prête à être utilisée.
     */
    private boolean ready;

    /**
     * Constructeur de la classe.
     */
    public Manette() {
        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] cs = ce.getControllers();
        ready = true;

        controller = findGamePad(cs);
        
        if (controller == null) {
            ready = false;
        } else {
            components = controller.getComponents();
        }
    }
    
    /**
     * Permet de savoir si un bouton est pressé.
     * @param index L'index du composant.
     * @return true si le bouton est pressé et false si il n'est pas pressé.
     */
    public boolean isPressed(int index) {
        return getValue(index) == 1.0f;
    }
    
    /**
     * Obetenir la valeur d'un stick analogique.
     * @param index L'index du composant.
     * @return La valeur du composant.
     */
    public float getValue(int index) {
        if(ready) {
            if(index < components.length) {
                return components[index].getPollData();
            }
            return Float.NaN;
        }
        return Float.NaN;
    }
    
    /**
     * Met à jours les valeurs de la manette.
     */
    public void poll() {
        if(ready) {
            controller.poll();
        }
    }
    
    /**
     * Dit si la manette est prête à être utilisée.
     * @return true si la manette peut être utilisée ou false si la manette n'est pas connectée.
     */
    public boolean isReady() {
        return ready;
    }
    
    /**
     * Permet de trouver la manette parmis la liste des contrôleurs données.
     * @param controllers La liste des contrôleurs.
     * @return Le contrôleur (la manette).
     */
    private Controller findGamePad(Controller[] controllers) {
        Controller controllerManette = null;
        
        for(Controller controllerTmp : controllers) {
            Controller.Type type = controllerTmp.getType();
            if ((type == Controller.Type.GAMEPAD) || (type == Controller.Type.STICK)) {
                controllerManette = controllerTmp;
            }
        }
        return controllerManette;
    }
}
