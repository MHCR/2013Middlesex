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
    private double DISTANCE_TO_GOAL = -2520;
    private double DISTANCE_TO_BACKUP;
    private double DISTANCE_FROM_GOAL_TO_CORNER;
    private double DISTANCE_FROM_GOAL_TO_MIDDLE;
    private boolean turning = false;
    private boolean lastFunction = false;
    private boolean droveforward = false;
    private boolean backedUp = false;

    public DriveScoreGoBackTurnRoutine() {
        timer = getTimer();
        setReverseDrive(true);
        setDistanceToTravel(DISTANCE_TO_GOAL);      
        setRoutineNumber(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT);
        fan = getFan();
    }

//DIO 4 - drive to corner
//DIO 3 - drive to middle    


    public void run() { 
      System.out.println(timer.get());
      System.out.println(this.reverse);
        DISTANCE_FROM_GOAL_TO_MIDDLE = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(2) * 1000;
        DISTANCE_FROM_GOAL_TO_CORNER = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(3) * 1000;
        DISTANCE_TO_BACKUP = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(4) * 1000;
        if (drive(getDistanceToTravel()) && !turning) {
            if(getDistanceToTravel() == DISTANCE_TO_BACKUP){
                backedUp = true;
            }
            droveforward = true;
            getEncoders().reset();
            setDistanceToTravel(0);
        }
        if(droveforward) {
            if (!timerStarted) {
                time.start();
                timerStarted = true;
            } else {
                if (time.get() < 1.229) {
                    fan.moveFan(.7);
                    fan.oscillateFan(-1);
                } else {
                    fan.oscillateFan(0);
                    fan.moveFan(0);
                    if (reverse) {
                        setDistanceToTravel(DISTANCE_TO_BACKUP);
                        setReverseDrive(false);
                    }else if (backedUp && !lastFunction) {                    
                        System.out.println("1");
                        if(turn(45)){
                            System.out.println("2");
                            turning = false;
                            getEncoders().reset();
                            setDistanceToTravel(DISTANCE_FROM_GOAL_TO_CORNER);
                            lastFunction = true;
                        }else{
                            turning = true;
                        }
                        System.out.println("HAI");
                    }

                }
            }
        }

    }

    public boolean validate() {
        return getDriverStation().getDigitalIn(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT);
    }

    public void stop() {
        this.setReverseDrive(true);
        setDistanceToTravel(DISTANCE_TO_GOAL);
        droveforward = false;
        System.out.println("stopping");
        changed = false;
        timer.stop();
        timer.reset();
        timerStarted = false;
        resetTravel = false;
        backedUp = false;
        lastFunction = false;
        fan.oscillateFan(0);
        fan.moveFan(0);
        drive.setLeftMotors(0);
        drive.setRightMotors(0);
        timer.stop();
        timer.reset();
    }
}
