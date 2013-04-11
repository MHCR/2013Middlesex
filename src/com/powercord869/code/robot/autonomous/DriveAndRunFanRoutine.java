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
        setDistanceToTravel(EncoderControl.CLICKS_PER_INCH * DriverStation.getInstance().getAnalogIn(1) * 1000);
        fan = getFan();
    }

    public void run() {

        drive(getDistanceToTravel());
        if (distanceTraveled >= getDistanceToTravel()) {
            if (!timerStarted) {
                time.start();
                timerStarted = true;
            } else {
                if (time.get() < (driverStation.getAnalogIn(3) * 1000)) {
                    fan.moveFan(1);
                } else if (time.get() > (driverStation.getAnalogIn(3) * 1000) && time.get() < (driverStation.getAnalogIn(3) * 1000) + 1000) {
                    fan.moveFan(0);
                    fan.oscillateFan(1);
                } else {
                    fan.oscillateFan(0);
                    fan.moveFan(0);
                }
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
