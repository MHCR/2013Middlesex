/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Kevin
 */
public class Climber implements RobotControllable {
    DoubleSolenoid lifter;
    Logitech controller;
    Climber climber = new Climber();
    
    private Climber(){
        lifter = new DoubleSolenoid(LIFTER_SOLENOID_FORWARD, LIFTER_SOLENOID_BACK);
        controller = Logitech.getInstance();
    }
    
    public void control() {
        if(controller.getR2()){
            lifter.set(DoubleSolenoid.Value.kReverse);
        }else{
            lifter.set(DoubleSolenoid.Value.kForward);
        }
    }

    public void stop() {
        
    }

  
    
}
