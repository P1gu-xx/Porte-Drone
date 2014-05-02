package ch.emf.portedrone.wrk.input.manette;

import net.java.games.input.*;

/**
 * 
 * @author PeclatJ
 */
public class Manette {

    private Controller controller;
    protected Component[] components;
    
    private boolean ready;

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
    
    public boolean isPressed(int index) {
        return getValue(index) == 1.0f;
    }
    
    public float getValue(int index) {
        if(ready) {
            if(index < components.length) {
                return components[index].getPollData();
            }
            return Float.NaN;
        }
        return Float.NaN;
    }
    
    public void poll() {
        if(ready) {
            controller.poll();
        }
    }
    
    public boolean isReady() {
        return ready;
    }
    
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
