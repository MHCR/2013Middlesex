/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import com.powercord869.code.robot.autonomous.AutonomousRoutine;
import com.powercord869.code.robot.autonomous.DriveAndTurnRoutine;
import com.powercord869.code.robot.autonomous.DriveAndRunFanRoutine;
import com.powercord869.code.robot.autonomous.DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine;
import com.powercord869.code.robot.autonomous.NoAutonomous;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import java.util.Vector;

public class RobotMain extends IterativeRobot {

    private Vector controllables = new Vector();
    private Compressor comp;
    private AutonomousRoutine routine = null;
    private AutonomousRoutine noAutonomous = null;

    public void robotInit() {
        comp = new Compressor(1, 1);
        controllables.addElement(RobotDrive.getInstance());
        controllables.addElement(Fan.getInstance());
        controllables.addElement(Climber.getInstance());
        //create a common autonomous routine that does nothing basically as a backup
        noAutonomous = new NoAutonomous();
        routine = noAutonomous;
    }

    public void disabledInit() {
    }

    public void autonomousInit() {
      //  AutonomousRoutine.OFFSET = DriverStation.getInstance().getAnalogIn(2) / 5;
        if (routine != null) {
            routine.getEncoders().reset();
        }
        if (DriverStation.getInstance().getDigitalIn(1)) {
            routine = new DriveAndTurnRoutine();
        } else if (DriverStation.getInstance().getDigitalIn(2)) {
            routine = new DriveAndRunFanRoutine();
        } else if (DriverStation.getInstance().getDigitalIn(3)) {
            routine = new DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine();
        } else {
            //to make sure that we dont run an old autonomous by accident
            routine = noAutonomous;
        }
    }

    public void teleopInit() {
    
        comp.start();
    }

    public void testInit() {
    }

    public void disabledPeriodic() {
        LCD.print("Disabled: " + this.getStopwatchTime() + " sec");
        for (int i = 0; i < controllables.size(); i++) {
            RobotControllable o = (RobotControllable) controllables.elementAt(i);
            o.stop();
        }
        comp.stop(); //to pass inspection this should not run in disabled mode
    }

    //any periodic BUT disabled will call this
    public void commonPeriodic() {
        
        if (routine != null) {
            LCD.print("Auto: " + this.getStopwatchTime() + " sec");
            LCD.print(2, "Distance: " + routine.getDistanceTraveled());
            LCD.print(3, "Encoder Off: " + routine.getEncoderOffset());
            LCD.print(4,"Destination: " + routine.getDistanceToTravel());
            LCD.print(5, "RightE: " + routine.getEncoders().getRightDistance());
            LCD.print(6, " LeftE " + routine.getEncoders().getLeftDistance());

        }
    }

    /*I noticed alot of potential problems with constantly creating new objects even if the previous validated 
     object is the same as the current one, so I changed this so I dont need to use so many static variables in the routines*/
    public void autonomousPeriodic() {
        //you only get so many characters

        commonPeriodic();
        if (routine.validate()) {
            routine.run();
        } else {
            routine.stop();
        }
    }

    public void teleopPeriodic() {
        LCD.print("Teleop: " + this.getStopwatchTime() + " sec");
        commonPeriodic();
        for (int i = 0; i < controllables.size(); i++) {
            RobotControllable o = (RobotControllable) controllables.elementAt(i);
            o.control();
        }
    }

    public void testPeriodic() {
        LCD.print("Test: " + this.getStopwatchTime() + " sec");
        commonPeriodic();
    }
}
