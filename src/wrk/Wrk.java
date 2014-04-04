/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import ctrl.ICtrlWrk;
import wrk.input.IInput;
import wrk.reseau.Client;

/**
 *
 * @author PeclatJ
 */
public class Wrk implements IWrkCtrl{
    
    private ICtrlWrk ctrl;
    
    private Client client;
    private IInput input;
    
}
