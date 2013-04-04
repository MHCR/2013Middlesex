/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.RobotDrive;

/**
 *
 * @author Computer
 */
public class DriveAndTurnRoutine extends AutonomousNode {
    private static int TURN_90_OFFSET;
    private static DriveAndTurnRoutine move = new DriveAndTurnRoutine();
    private double DISTANCE = 0;//I see the redundancy, but myehh
    private double DISTANCE_2 = 0;
    
    private DriveAndTurnRoutine(){
        setDistanceToTravel(DISTANCE); //change
    }
    
    public static DriveAndTurnRoutine getInstance(){
        return move;
    }

    public boolean validate() {
        return getDriverStation().getDigitalIn(1);
    }

    public void run() {
        if(distanceTraveled < getDistanceToTravel()){
        drive(getDistanceToTravel());
        }else if(getEncoderOffset() < TURN_90_OFFSET){
            turn(90);
        }else if(getEncoderOffset() >= TURN_90_OFFSET && distanceTraveled <= getDistanceToTravel() + DISTANCE_2){
            drive(DISTANCE_2);
        }else{
            stop();
        }
    }

    public void stop() {
        drive.setLeftMotors(0);
        drive.setRightMotors(0);
        
    }
}
