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
public class MoveForward implements AutonomousNode {
    RobotDrive drive;
    private static MoveForward move = new MoveForward();
    
    private MoveForward(){
        drive = RobotDrive.getInstance();
    }
    
    public static MoveForward getInstance(){
        return move;
    }

    public boolean validate() {
        return true;
    }

    public void run() {
        double offset = encoders.getLeftDistance() - encoders.getRightDistance();
        if(offset > 2){
            drive.setLeftMotors(.95);
            drive.setRightMotors(1);
        }else if(offset < 2){
           drive.setRightMotors(.95);
           drive.setLeftMotors(1); 
        }else{
           drive.setLeftMotors(1);
           drive.setLeftMotors(1); 
        }        
    }
}
