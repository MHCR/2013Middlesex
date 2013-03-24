/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Kevin
 */
public class RobotDrive implements RobotControllable {

    private Victor jag1, jag2, jag3, jag4, jag5, jag6;
    private Joystick joy1, joy2;
    private static RobotDrive drive = new RobotDrive();
    private double precRight = 1;
    private double precLeft = 1;

    private RobotDrive() {
        joy1 = new Joystick(LEFT_STICK);
        joy2 = new Joystick(RIGHT_STICK);
        jag1 = new Victor(1);
        jag2 = new Victor(2);
        jag3 = new Victor(3);
        jag4 = new Victor(4);
        jag5 = new Victor(5);
        jag6 = new Victor(6);
    }

    public void control() {
        tankDrive(joy1.getY(), joy2.getY());
    }

    private void tankDrive(double right, double left) {
        jag1.set(right * precRight);
        jag2.set(right * precRight);
        jag3.set(right * precRight);
        jag4.set(-left * precLeft);
        jag5.set(-left * precLeft);
        jag6.set(-left * precLeft);
    }

    public void setPrecisionValues(double right, double left) {
        precRight = right;
        precLeft = left;
    }

    public static RobotDrive getInstance() {
        return drive;
    }


    public void stop() {
        jag1.set(0);
        jag2.set(0);
        jag3.set(0);
        jag4.set(0);
        jag5.set(0);
        jag6.set(0);
    }    
}
