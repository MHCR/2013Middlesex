/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.Fan;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author Computer
 */
public class DriveAndRunFanRoutine extends AutonomousRoutine {
    
    private Fan fan;
    private boolean timerStarted = false;

    public DriveAndRunFanRoutine() {
        setDistanceToTravel(-(EncoderControl.CLICKS_PER_INCH * DriverStation.getInstance().getAnalogIn(1) * 1000));
        fan = getFan();
    }

    public void run() {
       
        drive(getDistanceToTravel());
        if ((getDistanceTraveled() >= getDistanceToTravel() && !reverse) || (getDistanceTraveled() <= getDistanceToTravel() && reverse)) {
            if (!timerStarted) {
                time.start();
                timerStarted = true;
            } else {
                if (time.get() < driverStation.getAnalogIn(4)) {
                    fan.moveFan(.7);
                    fan.oscillateFan(-1);
                } else {
                    fan.oscillateFan(0);
                    fan.moveFan(0);
                }
            }
        }
         System.out.println(time.get());

    }

    public boolean validate() {

        return getDriverStation().getDigitalIn(2);
    }

    public void stop() {
        time.reset();
        timerStarted = false;
        fan.oscillateFan(0);
        fan.moveFan(0);
        time.stop();
        time.reset();
    }
}
