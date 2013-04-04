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
public abstract class AutonomousNode {

    private static EncoderControl encoders;
    private Fan fan;
    private double distanceToTravel = 0;//change 
    private static DriverStation driver;
    private int routineNumber;
    RobotDrive drive;
    protected Timer time;
    protected static double distanceTraveled = 0;

    public AutonomousNode() {
        time = new Timer();
        fan = Fan.getInstance();
        encoders = EncoderControl.getInstance();
        drive = RobotDrive.getInstance();
    }

    protected void setRoutineNumber(int number) {
        this.routineNumber = number;
    }

    protected void setDistanceToTravel(double travel) {
        distanceToTravel = travel;
    }
    
    protected Timer getTimer(){
        return time;
    }

    protected RobotDrive getDrive() {
        return drive;
    }

    protected double getDistanceToTravel() {
        return distanceToTravel;
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

    protected double getEncoderOffset() {
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
            drive.setLeftMotors(0);

        }
        distanceTraveled = encoders.getLeftDistance();
    }

    protected void turn(int degrees) {
        //its too late, thinking is hard.
    }

    public abstract void run();

    public abstract boolean validate();

    public abstract void stop();
}
