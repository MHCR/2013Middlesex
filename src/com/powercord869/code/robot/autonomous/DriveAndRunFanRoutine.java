/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

import com.powercord869.code.robot.Fan;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author Computer
 */
public class DriveAndRunFanRoutine extends AutonomousRoutine {

    private Fan fan;
    private boolean timerStarted = false;
    private double DISTANCE_TO_GOAL = 2520;
    private double DISTANCE_TO_BACKUP;
    private double DISTANCE_FROM_GOAL_TO_CORNER;
    private double DISTANCE_FROM_GOAL_TO_MIDDLE;
    private boolean droveforward = false;

    public DriveAndRunFanRoutine() {
        setReverseDrive(true);
        setDistanceToTravel(-(EncoderControl.CLICKS_PER_INCH * DriverStation.getInstance().getAnalogIn(1) * 1000));
        fan = getFan();
    }

    public void run() {     
        System.out.println(time.get());
        DISTANCE_FROM_GOAL_TO_MIDDLE = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(2) * 1000;
        DISTANCE_FROM_GOAL_TO_CORNER = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(3) * 1000;
        DISTANCE_TO_BACKUP = EncoderControl.CLICKS_PER_INCH * driverStation.getAnalogIn(4) * 1000;
        if (drive(getDistanceToTravel())) {
            getEncoders().reset();
            droveforward = true;
            setDistanceToTravel(0);
        }
        if (!timerStarted && droveforward) {
            time.start();
            timerStarted = true;
        } else {
            if (droveforward && time.get() < 1.229) {
                fan.moveFan(.7);
                fan.oscillateFan(-1);
            } else {
                fan.oscillateFan(0);
                fan.moveFan(0);               

            }
        }



    }

    public boolean validate() {

        return getDriverStation().getDigitalIn(2);
    }

    public void stop() {
        droveforward = false;
        System.out.println("stop");
        time.reset();
        timerStarted = false;
        setReverseDrive(true);
        fan.oscillateFan(0);
        fan.moveFan(0);
        time.stop();
        time.reset();
    }
}
