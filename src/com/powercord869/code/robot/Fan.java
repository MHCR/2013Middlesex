/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Kevin
 */
public class Fan implements RobotControllable {
    private Victor fanControl;
    private Victor fanBlades;
    private Logitech controller;
    private static Fan fan = new Fan();
    public static double[] positions = {0, 0, 0};
     private double move;
     private double spin;
     private Timer fanTimer;
    private Fan() {
        fanTimer = new Timer();
        this.controller = Logitech.getInstance();
        fanControl = new Victor(FAN_CONTROL);
        fanBlades = new Victor(FAN_BLADES);
    }
    
    public static Fan getInstance(){
        return fan;
    }

    public void control() {
        // I like my ternary operators :(
       move = controller.getLeftStickY() < -.9 ? 1.0 : controller.getLeftStickY() > .9 ? -1.0 : 0;
       spin = controller.getR2() ? -1.0 : 0;
       oscillateFan(spin);
       moveFan(move);
    }
    
  
   
    public void oscillateFan(double intensity){
     fanBlades.set(intensity);
    }
    
    public void moveFan(double intensity){
        fanControl.set(intensity);
       
    }

    public void stop() {
        fanControl.set(0);
        fanBlades.set(0);
    }
}
