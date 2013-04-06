/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author Kevin
 */
public interface AutonomousNode { 
    static EncoderControl encoders = EncoderControl.getInstance();
    static double DISTANCE_TO_TRAVEL = 0;//change 
    public void run();
    public boolean validate();
}
