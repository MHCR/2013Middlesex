/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author Computer
 */
public class EncoderControl {

    private static int LEFT_ENCODER_1 = 4;
    private static int LEFT_ENCODER_2 = 5;
    private static int RIGHT_ENCODER_1 = 2;
    private static int RIGHT_ENCODER_2 = 3;
    private static Encoder right, left;
    private static EncoderControl encoderC;
    public static double CLICKS_PER_INCH = 21;

    private EncoderControl() {
        left = new Encoder(1,LEFT_ENCODER_1,1, LEFT_ENCODER_2, true);
        right = new Encoder(1,RIGHT_ENCODER_1,1, RIGHT_ENCODER_2, false);
        left.start();
        right.start();
    }

    public static EncoderControl getInstance() {
        if(encoderC==null) {
            encoderC = new EncoderControl();
        }
        return encoderC;
    }
    //in case we want to change how we get the distance
    public double getLeftDistance() {
        return left.get();
    }
    
    public void reset(){
        left.reset();
        right.reset();
    }

    public double getRightDistance() {
        return right.get();
    }
    
    public Encoder getLeft() {
        return left;
    }

    public Encoder getRight() {
        return right;
    }
}
