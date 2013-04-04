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
public class DriveAndRunFanRoutine extends AutonomousRoutine {

    private double DISTANCE = 0;
    private Fan fan;
    private boolean timerStarted = false;

    public DriveAndRunFanRoutine() {
        setDistanceToTravel(DISTANCE);
        fan = getFan();
    }


    public void run() {
        drive(getDistanceToTravel());
        if (distanceTraveled >= getDistanceToTravel()) {
            if (!timerStarted) {
                time.start();
                timerStarted = true;
            }
            if (time.get() < 1.3) {
                fan.moveFan(1);
            } else if (time.get() > 1.3 && time.get() < 3) {
                fan.oscillateFan(1);
            } else {
                fan.oscillateFan(0);
            }

        }

    }

    public boolean validate() {

        return getDriverStation().getDigitalIn(2);
    }

    public void stop() {
        fan.oscillateFan(0);
        fan.moveFan(0);
        time.stop();
        time.reset();
    }

  
}
