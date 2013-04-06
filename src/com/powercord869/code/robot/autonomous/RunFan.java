/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.Fan;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Computer
 */
public class RunFan implements AutonomousNode {

    private Timer time;
    private Fan fan;
    private boolean timerStarted = false;
    private static RunFan run = new RunFan();
    
    private RunFan(){
       time = new Timer(); 
       fan = Fan.getInstance();
    }
    public static RunFan getInstance(){
        return run;
    }

    public void run() {
        if (!timerStarted) {
            time.start();
            timerStarted = true;
        }
        while (time.get() < 1.3) {
            fan.moveFan(1);
        }
        while (time.get() > 1.3 && time.get() < 3) {
            fan.oscillateFan(1);
        }
    }

    public boolean validate() {

        return encoders.getLeftDistance() >= DISTANCE_TO_TRAVEL && time.get() < 3;
    }
}
