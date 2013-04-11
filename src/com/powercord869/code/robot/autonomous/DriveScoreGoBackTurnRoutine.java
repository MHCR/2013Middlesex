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
    private double DISTANCE_TO_GOAL;
    private double DISTANCE_TO_BACKUP;
    private double DISTANCE_FROM_GOAL_TO_CORNER;
    private double DISTANCE_FROM_GOAL_TO_MIDDLE;

    public DriveScoreGoBackTurnRoutine() {
        timer = getTimer();
        setDistanceToTravel(EncoderControl.CLICKS_PER_INCH * DISTANCE_TO_GOAL * 1000);
        setRoutineNumber(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT);
        fan = getFan();
    }

//DIO 4 - drive to corner
//DIO 3 - drive to middle    
    public void run() {
        DISTANCE_TO_GOAL = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(1) * 1000;
        DISTANCE_FROM_GOAL_TO_MIDDLE = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(2) * 1000;
        DISTANCE_FROM_GOAL_TO_CORNER = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(3) * 1000;
        DISTANCE_TO_BACKUP = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(4) * 1000;
        if(!timerStarted){
        setDistanceToTravel(EncoderControl.CLICKS_PER_INCH * DISTANCE_TO_GOAL * 1000);
        }
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
        if (time.get() < driverStation.getAnalogIn(4) && timerStarted) {
            fan.moveFan(.7);
            fan.oscillateFan(-1);
        } else if (getDistanceTraveled() >= getDistanceToTravel()) {
            if (!resetTravel) {
                setDistanceToTravel(getDistanceTraveled() - DISTANCE_TO_BACKUP);//back up a little
                reverse = true;
                resetTravel = true;
            } else if (turn(driverStation.getDigitalIn(4) ? 90 : 180) && !changed) {
                if (driverStation.getDigitalIn(4)) {
                    setDistanceToTravel(getDistanceTraveled() + DISTANCE_FROM_GOAL_TO_CORNER);                    
                } else {
                    setDistanceToTravel(getDistanceTraveled() + DISTANCE_FROM_GOAL_TO_MIDDLE);
                }
                reverse = false;
                //drive to line or other goal
                changed = true;
            }else{
                stop();
            }


        }

    }

    public boolean validate() {
        return getDriverStation().getDigitalIn(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT) || getDriverStation().getDigitalIn(4);
    }

    public void stop() {
        System.out.println("stopping");
        changed = false;
        timer.stop();
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
