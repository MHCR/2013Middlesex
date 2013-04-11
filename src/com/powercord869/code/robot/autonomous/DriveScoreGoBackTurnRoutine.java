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
public class DriveScoreGoBackTurnRoutine extends AutonomousRoutine {

    private boolean timerStarted = false;
    private Timer timer;
    private Fan fan;//s
    private boolean changed = false;
    private boolean resetTravel = false;

    public DriveScoreGoBackTurnRoutine() {
        timer = getTimer();
        setDistanceToTravel(EncoderControl.CLICKS_PER_INCH * DriverStation.getInstance().getAnalogIn(1) * 1000);
        setRoutineNumber(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT);
        fan = getFan();
    }
    //1 is the initial distance
    //analog 4 is the distance back
    //analog 3 is the time in miliseconds for fan to turn
    // 5 is the time in milliseconds for fan to spin

    public void run() {
        drive(getDistanceToTravel());
        if (changed && driverStation.getDigitalIn(3) && getDistanceToTravel() <= getDistanceTraveled()) {
            if (turn(90)) {
                stop();
            }
        }
        if (!timerStarted && getDistanceTraveled() >= getDistanceToTravel()) {
            timer.start();
            timerStarted = true;
        }
        if (time.get() < (driverStation.getAnalogIn(3) * 1000000) && timerStarted) {
            fan.moveFan(.3);
        } else if (time.get() > (driverStation.getAnalogIn(3) * 1000000) && time.get() < (driverStation.getAnalogIn(3) * 1000) + driverStation.getAnalogIn(5) * 1000) {
            fan.moveFan(0);
            fan.oscillateFan(1);
        } else if (getDistanceTraveled() >= getDistanceToTravel()) {
            if (!resetTravel) {
                setDistanceToTravel(getDistanceTraveled() + 210);
                resetTravel = true;
            } else if (turn(driverStation.getDigitalIn(4) ? 90 : 180)) {
                setDistanceToTravel(getDistanceTraveled() + (EncoderControl.CLICKS_PER_INCH * getDriverStation().getAnalogIn(4) * 1000000));
                changed = true;
            }


        } else {
            fan.oscillateFan(0);
            timer.stop();
            stop();
        }

    }

    public boolean validate() {
        return getDriverStation().getDigitalIn(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT) || getDriverStation().getDigitalIn(4);
    }

    public void stop() {
        System.out.println("stopping");
        changed = false;
        timer.reset();
        timerStarted = false;
        resetTravel = false;
        fan.oscillateFan(0);
        fan.moveFan(0);
        drive.setLeftMotors(0);
        drive.setRightMotors(0);
        timer.stop();
        timer.reset();
    }
}
