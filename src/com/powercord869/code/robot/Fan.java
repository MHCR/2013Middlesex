/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Kevin
 */
public class Fan implements RobotControllable {
    private Victor fanControl;
    private Victor fanBlades;
    private Logitech controller;
    private AnalogChannel pot;
    public static double[] positions = {0, 0, 0};
    
    private Fan() {
        pot = new AnalogChannel(POTENTIOMETER);
        this.controller = Logitech.getInstance();
        fanControl = new Victor(FAN_CONTROL);
        fanBlades = new Victor(FAN_BLADES);
    }

    public void control() {
       double move = controller.getLeftStickY() < -.9 ? 1.0 : controller.getLeftStickY() > .9 ? -1.0 : 0;
       double spin = controller.getL2() ? -1.0 : controller.getL2() && controller.getL1() ? 1.0 : 0;
       oscillateFan(spin);
       moveFan(move);
    }
    
    public void changePosition(int position) 
{
	double dif = pot.getVoltage() - positions[position];
	if (dif < .02 ) {
		fanControl.set(1 * dif + .7);
	} else if (dif > .02) {
		fanControl.set(-1 * dif - .7);
	} else {
		fanControl.set(0);
	}

}
    
    public void oscillateFan(double intensity){
        fanBlades.set(intensity);
    }
    
    public void moveFan(double intensity){
        fanBlades.set(intensity);
    }

    public void stop() {
        fanControl.set(0);
        fanBlades.set(0);
    }
}
