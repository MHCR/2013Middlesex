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
public class DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine extends AutonomousNode {

    private static DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine yea = new DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine();
    private boolean timerStarted = false;
    private double DISTANCE = 0;
    private double DISTANCE_2 = 0;
    private static int ROUTINE_NUMBER = 3;
    private Timer time;
    private Fan fan;//s

    private DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine() {
        time = getTimer();
        setDistanceToTravel(DISTANCE);
        setRoutineNumber(ROUTINE_NUMBER);
        fan = getFan();
    }

    public static DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine getInstance() {
        return yea;
    }

    public void run() {
        if (distanceTraveled < getDistanceToTravel()) {
            drive(getDistanceToTravel());
        } else {
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
        return getDriverStation().getDigitalIn(ROUTINE_NUMBER);
    }

    public void stop() {
        fan.oscillateFan(0);
        fan.moveFan(0);
        drive.setLeftMotors(0);
        drive.setRightMotors(0);
        time.stop();
        time.reset();
    }
}
