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

    private static int LEFT_ENCODER_1 = 1;
    private static int LEFT_ENCODER_2 = 2;
    private static int RIGHT_ENCODER_1 = 3;
    private static int RIGHT_ENCODER_2 = 4;
    private static Encoder right, left;
    private static EncoderControl encoderC = new EncoderControl();

    private EncoderControl() {
        left = new Encoder(LEFT_ENCODER_1, LEFT_ENCODER_2);
        right = new Encoder(RIGHT_ENCODER_1, RIGHT_ENCODER_2);
    }

    public static EncoderControl getInstance() {
        return encoderC;
    }
    //in case we want to change how we get the distance
    public double getLeftDistance() {
        return left.getDistance();
    }

    public double getRightDistance() {
        return right.getDistance();
    }
}
