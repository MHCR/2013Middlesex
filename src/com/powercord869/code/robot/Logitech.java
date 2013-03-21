/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Kevin
 */
public class Logitech extends Joystick {

    private static Logitech controller = new Logitech(3);

    private Logitech(int joy) {
        super(joy, 7, 13);
    }

    public static Logitech getInstance() {
        return controller;
    }

    public boolean get1() {
        return getRawButton(1);
    }

    public boolean get2() {
        return getRawButton(2);
    }

    public boolean get3() {
        return getRawButton(3);
    }

    public boolean get4() {
        return getRawButton(4);
    }

    public boolean getL1() {
        return getRawButton(5);
    }

    public boolean getR1() {
        return getRawButton(6);
    }

    public boolean getL2() {
        return getRawButton(7);
    }

    public boolean getR2() {
        return getRawButton(8);
    }

    public boolean getSelectButton() {
        return getRawButton(9);
    }

    public boolean getStartButton() {
        return getRawButton(10);
    }

    public double getLeftStickX() {
        // Left: negative Right: positive
        return getRawAxis(1);
    }

    public double getLeftStickY() {
        // Up: negative Down: positive
        return getRawAxis(2);
    }

    public double getRightStickX() {
        // Left: negative Right: positive
        return getRawAxis(3);
    }

    public double getRightStickY() {
        // Up: negative Down: positive
        return getRawAxis(4);
    }

    public double getDpadX() {
        // Left: negative Right: positive
        return getRawAxis(5);
    }

    public double getDpadY() {
        // Up: negative Down: positive
        return getRawAxis(6);
    }
}
