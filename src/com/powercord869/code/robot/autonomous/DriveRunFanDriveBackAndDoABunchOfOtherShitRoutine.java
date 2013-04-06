/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.Fan;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Kevin
 */
public class DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine extends AutonomousRoutine {  
    private boolean timerStarted = false;
    private double DISTANCE = 0;
    private double DISTANCE_2 = 0;
    private static int ROUTINE_NUMBER = 3;
    private Timer timer;
    private Fan fan;//s

    public DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine() {
        timer = getTimer();
        setDistanceToTravel(DISTANCE);
        setRoutineNumber(ROUTINE_NUMBER);
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
        return getDriverStation().getDigitalIn(ROUTINE_NUMBER);
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
