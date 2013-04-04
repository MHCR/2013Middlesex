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
public class DriveAndTurnRoutine extends AutonomousRoutine {
    private static int TURN_90_OFFSET = 0;
    private double DISTANCE = 0;//I see the redundancy, but myehh
    private double DISTANCE_2 = 0;
    private double encoderOffset = 0;
    private double fullDistance = 0;
    
    public DriveAndTurnRoutine(){
        encoderOffset = getEncoderOffset();
        setDistanceToTravel(DISTANCE); //change
        fullDistance = getDistanceToTravel() + DISTANCE_2;
    }
    

    public boolean validate() {
        return getDriverStation().getDigitalIn(1);
    }

    public void run() {
      
        drive(getDistanceToTravel());
       if(encoderOffset < TURN_90_OFFSET && distanceTraveled >= getDistanceToTravel()){
            turn(90);
        }else if(encoderOffset >= TURN_90_OFFSET && distanceTraveled <= fullDistance){
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
