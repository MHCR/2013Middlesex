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
 private static Encoder right, left; 
 private static EncoderControl encoderC = new EncoderControl();

 private EncoderControl(){
     left = new Encoder(1, 2);
     right = new Encoder(3, 4);               
 }
 
 public static EncoderControl getInstance(){
     return encoderC;     
 }
 
 public double getLeftDistance(){
     return left.getDistance();
 }
 
 public double getRightDistance(){
     return right.getDistance();
 }
 
}
