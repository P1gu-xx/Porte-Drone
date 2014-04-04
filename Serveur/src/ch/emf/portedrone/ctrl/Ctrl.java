/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.emf.portedrone.ctrl;

import ch.emf.portedrone.ihm.IIhm;
import ch.emf.portedrone.wrk.IWrk;

/**
 *
 * @author ramosdasilm
 */
public class Ctrl implements ICtrlWrk, ICtrlIhm {

    private IWrk wrk;
    private IIhm ihm;
}
