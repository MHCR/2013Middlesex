/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.RobotDrive;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author Computer
 */
public class DriveAndTurnRoutine extends AutonomousRoutine {

    private static int TURN_90_OFFSET = 0;
    private double DISTANCE = 0;//I see the redundancy, but myehh
    private double DISTANCE_2 = 0;
    private double encoderOffset = 0;
    private double fullDistance = 0;

    public DriveAndTurnRoutine() {
        setDistanceToTravel(EncoderControl.CLICKS_PER_INCH * DriverStation.getInstance().getAnalogIn(1) * 1000);
        encoderOffset = getEncoderOffset();
        fullDistance = getDistanceToTravel() + DISTANCE_2;
    }

    public boolean validate() {
        return getDriverStation().getDigitalIn(1);
    }

    public void run() {
        drive(getDistanceToTravel());
        if (getDistanceTraveled() >= getDistanceToTravel()) {
            stop();
        }
    }

    public void stop() {
        drive.setLeftMotors(0);
        drive.setRightMotors(0);

    }
}
