/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.Fan;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Kevin
 */
public class DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine extends AutonomousRoutine {  
    private boolean timerStarted = false;
    private Timer timer;
    private Fan fan;//s

    public DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine() {
        timer = getTimer();
        setDistanceToTravel(EncoderControl.CLICKS_PER_INCH * DriverStation.getInstance().getAnalogIn(1) * 1000);
        setRoutineNumber(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT);
        fan = getFan();
    }

    public void run() {
            drive(getDistanceToTravel());
            if (!timerStarted && getDistanceTraveled() >= getDistanceToTravel()) {
                timer.start();
                timerStarted = true;
            }
            if (timer.get() < 1.3) {
                fan.moveFan(1);
            } else if (timer.get() > 1.3 && timer.get() < 3) {
                fan.oscillateFan(1);
            } else {
                fan.oscillateFan(0);
                stop();
            }

        }
    

    public boolean validate() {
        return getDriverStation().getDigitalIn(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT);
    }

    public void stop() {
        fan.oscillateFan(0);
        fan.moveFan(0);
        drive.setLeftMotors(0);
        drive.setRightMotors(0);
        timer.stop();
        timer.reset();
    }
}
