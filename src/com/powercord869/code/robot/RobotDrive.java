/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Kevin
 */
public class RobotDrive implements RobotControllable {

    private Jaguar jag1_left, jag2_left, jag3_left, jag1_right, jag2_right, jag3_right;
    private Joystick leftJoyStick, rightJoyStick;
    private static RobotDrive drive = new RobotDrive();
    private double precRight = 1;
    private double precLeft = 1;
    
    public RobotDrive() {
        leftJoyStick = new Joystick(LEFT_STICK);
        rightJoyStick = new Joystick(RIGHT_STICK);
        jag1_left = new Jaguar(LEFT_MOTOR_1);
        jag2_left = new Jaguar(LEFT_MOTOR_2);
        jag3_right = new Jaguar(LEFT_MOTOR_3);
        jag1_right = new Jaguar(RIGHT_MOTOR_1);
        jag2_right = new Jaguar(RIGHT_MOTOR_2);
        jag3_left = new Jaguar(RIGHT_MOTOR_3);
        
    }
    
    public double getRightSpeed(){
        return jag1_right.getSpeed() + jag2_right.getSpeed() + jag3_right.getSpeed() / 3;
    }
    
     public double getLeftSpeed(){
        return jag1_left.getSpeed() + jag2_left.getSpeed() + jag3_left.getSpeed() / 3;
    }

    public void control() {       
        tankDrive(leftJoyStick.getY(), rightJoyStick.getY());
    }

    private void tankDrive(double right, double left) {
        jag1_left.set(right * precRight);
        jag2_left.set(right * precRight);
        jag3_right.set(right * precRight);
        jag1_right.set(-left * precLeft);
        jag2_right.set(-left * precLeft);
        jag3_left.set(-left * precLeft);
    }

    public void setPrecisionValues(double right, double left) {
        precRight = right;
        precLeft = left;
    }

    public static RobotDrive getInstance() {
        return drive;
    }
    
    public void setRightMotors(double intensity){
        jag1_left.set(intensity);
        jag2_left.set(intensity);
        jag3_right.set(intensity);
    }
    
     public void setLeftMotors(double intensity){
        jag1_right.set(intensity);
        jag2_right.set(intensity);
        jag3_left.set(intensity);
    }


    public void stop(){
        jag1_left.set(0);
        jag2_left.set(0);
        jag3_right.set(0);
        jag1_right.set(0);
        jag2_right.set(0);
        jag3_left.set(0);
    }    
}
