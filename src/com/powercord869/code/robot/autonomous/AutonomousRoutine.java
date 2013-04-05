/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.Fan;
import com.powercord869.code.robot.RobotDrive;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Kevin
 */
public abstract class AutonomousRoutine {

    private static int DRIVE_AND_TURN_ROUTINE = 1;
    private static int DRIVE_AND_RUN_FAN_ROUTINE = 2;
    private static int DRIVE_RUN_FAN_DRIVE_BACK_AND_SHIT = 3;
    private static EncoderControl encoders;
    private Fan fan;
    private double distanceToTravel = 0;//change 
    private static DriverStation driver;
    private int routineNumber;
    RobotDrive drive;
    protected Timer time;
    protected static double distanceTraveled = 0;
    private double turnDistance = 0;
    public static double THE_MAGIC_NUMBER = 1;

    public AutonomousRoutine() {
        time = new Timer();
        fan = Fan.getInstance();
        encoders = EncoderControl.getInstance();
        drive = RobotDrive.getInstance();
        driver = DriverStation.getInstance();
    }

    protected void setRoutineNumber(int number) {
        this.routineNumber = number;
    }

    protected void setDistanceToTravel(double travel) {
        distanceToTravel = travel;
    }

    protected Timer getTimer() {
        return time;
    }

    public RobotDrive getDrive() {
        return drive;
    }

    public double getDistanceToTravel() {
        return encoders.getLeftDistance() + encoders.getRightDistance() / 2;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    protected DriverStation getDriverStation() {
        return driver;
    }

    protected Fan getFan() {
        return fan;
    }

    public int getRoutineNumber() {
        return routineNumber;
    }

    protected EncoderControl getEncoders() {
        return encoders;
    }

    public double getEncoderOffset() {
        return encoders.getLeftDistance() - encoders.getRightDistance();
    }

    protected void drive(double distance) {
        double offset = encoders.getLeftDistance() - encoders.getRightDistance();
        if (encoders.getLeftDistance() < distance) {
            if (offset > 2) {
                drive.setLeftMotors(.95);
                drive.setRightMotors(1);
            } else if (offset < -2) {
                drive.setRightMotors(.95);
                drive.setLeftMotors(1);
            } else {
                drive.setLeftMotors(1);
                drive.setLeftMotors(1);
            }
        } else {
            drive.setLeftMotors(0);
            drive.setRightMotors(0);

        }

    }
    //im working on it! i just wrote some random stuff to get my mind going, ill get it though6

    protected void turn(int degrees) {
        turnDistance = degrees * THE_MAGIC_NUMBER;
        if (getEncoderOffset() < turnDistance) {
            if (degrees > 0) {
                drive.setLeftMotors(-.5);
                drive.setRightMotors(.5);
            } else {
                drive.setLeftMotors(.5);
                drive.setRightMotors(-.5);
            }
        }
    }

    public abstract void run();

    public abstract boolean validate(); //i think this is pointless now, not sure yet though. I should be though :/

    public abstract void stop();
}
