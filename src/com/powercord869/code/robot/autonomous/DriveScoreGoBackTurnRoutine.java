/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.Fan;
import com.powercord869.code.robot.LCD;

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
    private int DRIVE_TO_GOAL_STATE = 1;
    private int RUN_FAN_STATE = 2;
    private int BACK_UP_STATE = 3;
    private int TURN_STATE = 4;
    private int DRIVE_TO_CORNER_STATE = 5;
    private int STOP_STATE = 0;
    private int degrees = 45;
    private boolean right;
    private int state = 0;
    private double finalDistance;

    public DriveScoreGoBackTurnRoutine() {
        timer = getTimer();
        setReverseDrive(true);
        setDistanceToTravel(DISTANCE_TO_GOAL);
        setRoutineNumber(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT);
        fan = getFan();
        state = DRIVE_TO_GOAL_STATE;
    }

//DIO 4 - drive to corner
//DIO 3 - drive to middle    
    public void run() {
        LCD.print(3, "state: " + state);
        System.out.println(timer.get()); 
        System.out.println(this.reverse);
        DISTANCE_TO_GOAL = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(1) * 1000;
        DISTANCE_FROM_GOAL_TO_MIDDLE = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(2) * 1000;
        DISTANCE_FROM_GOAL_TO_CORNER = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(3) * 1000;
        DISTANCE_TO_BACKUP = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(4) * 1000;
        if(driverStation.getDigitalIn(3)) {
            right = true;
            finalDistance = DISTANCE_FROM_GOAL_TO_CORNER;
        } else {
            right = false;
            finalDistance = DISTANCE_FROM_GOAL_TO_MIDDLE;
        }
        if (state != TURN_STATE && drive(getDistanceToTravel())) {
            if (BACK_UP_STATE == state) {
                state = TURN_STATE;
            } else if (DRIVE_TO_GOAL_STATE == state) {
                state = RUN_FAN_STATE;                
            } else if (DRIVE_TO_CORNER_STATE == state) {
                state = STOP_STATE;
            }
            getEncoders().reset();
            setDistanceToTravel(0);
        }
        if (state >= RUN_FAN_STATE) {
            if(time.get() == 0){
                time.start();
            }
            
            if (time.get() < 1.229) {
                fan.moveFan(.7);
                fan.oscillateFan(-1);
            } else {

                fan.oscillateFan(0);
                fan.moveFan(0);
                if(RUN_FAN_STATE == state){
                    setDistanceToTravel(DISTANCE_TO_BACKUP);
                    setReverseDrive(false);
                    state = BACK_UP_STATE;
                } else if (TURN_STATE == state) {
                    System.out.println("1");
                    if (turn(degrees, right)) {
                        System.out.println("2");
                        state = DRIVE_TO_CORNER_STATE;
                        getEncoders().reset();                        
                        setDistanceToTravel(finalDistance);
                    } 
                }

            }
        }
    }

    public boolean validate() {
        return getDriverStation().getDigitalIn(DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT) || getDriverStation().getDigitalIn(4);
    }

    public void stop() {
        this.setReverseDrive(true);
        setDistanceToTravel(DISTANCE_TO_GOAL);
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
