/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

/**
 *
 * @author Kevin
 */
public interface RobotControllable {

    //PWMs
    public static final int FAN_CONTROL = 10;
    public static final int FAN_BLADES = 9;
    
    //limits
    public static final int LIFTER_SOLENOID_FORWARD = 1;
    public static final int LIFTER_SOLENOID_BACK = 2;
    
    //Human interface devices
    public static final int LEFT_STICK = 1;
    public static final int RIGHT_STICK = 2;
    public static final int CONTROLLER = 3;
    
    //DIO
    public static final int PRESSURE_SWITCH = 1;
    //Spike
    public static final int COMPRESSOR_SWITCH = 1;
    //Analog
    public static final int POTENTIOMETER = 1;
    
    //PWMs
    public static final int LEFT_MOTOR_1 = 1;
    public static final int LEFT_MOTOR_2 = 2;
    public static final int LEFT_MOTOR_3 = 3;
    public static final int RIGHT_MOTOR_1 = 4;
    public static final int RIGHT_MOTOR_2 = 5;
    public static final int RIGHT_MOTOR_3 = 6;
    

    
    
   void control();

    void stop();
}
