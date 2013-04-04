/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import com.powercord869.code.robot.autonomous.AutonomousRoutine;
import com.powercord869.code.robot.autonomous.DriveAndTurnRoutine;
import com.powercord869.code.robot.autonomous.DriveAndRunFanRoutine;
import com.powercord869.code.robot.autonomous.DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import java.util.Vector;

public class RobotMain extends IterativeRobot {

    private Vector controllables = new Vector();
    private Vector autonomousRoutines = new Vector();
    private Compressor comp;
    private AutonomousRoutine routine = null;

    public void robotInit() {
        comp = new Compressor(1, 1);
        controllables.addElement(RobotDrive.getInstance());
        controllables.addElement(Fan.getInstance());
        controllables.addElement(Climber.getInstance());
        autonomousRoutines.addElement(new DriveAndRunFanRoutine());
        autonomousRoutines.addElement(new DriveAndTurnRoutine());
        autonomousRoutines.addElement(new DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine());
        comp.start();
    }

    public void disabledInit() {
    }

    public void autonomousInit() {
        if(DriverStation.getInstance().getDigitalIn(1)){
            routine = new DriveAndTurnRoutine();
        }else if(DriverStation.getInstance().getDigitalIn(2)){
             routine = new DriveAndRunFanRoutine();
        }else if(DriverStation.getInstance().getDigitalIn(3)){                 
            routine = new DriveRunFanDriveBackAndDoABunchOfOtherShitRoutine();  
        }
    }

    public void teleopInit() {
    }

    public void testInit() {
    }

    public void disabledPeriodic() {
        LCD.print("Disabled: " + this.getStopwatchTime() + " sec");
        for (int i = 0; i < controllables.size(); i++) {
            RobotControllable o = (RobotControllable) controllables.elementAt(i);
            o.stop();
        }
    }

    /*I noticed alot of potential problems with constantly creating new objects even if the previous validated 
     object is the same as the current one, so I changed this so I dont need to use so many static variables in the routines*/
    public void autonomousPeriodic() {      
        AutonomousRoutine.THE_MAGIC_NUMBER = DriverStation.getInstance().getAnalogIn(1) / 5.0;
        if(routine.validate()){
            routine.run();
        }else{
            routine.stop();
        }       
        LCD.print("Auto: " + this.getStopwatchTime() + " sec");
        LCD.print(2, "Distance: " + routine.getDistanceTraveled());
        LCD.print(3, "Encoder Offset: " + routine.getEncoderOffset());
        LCD.print(4, "Right Motors: " + routine.getDrive().getRightSpeed() + " Left Motors: " + routine.getDrive().getLeftSpeed());
    }

    public void teleopPeriodic() {
        LCD.print("Teleop: " + this.getStopwatchTime() + " sec");
        for (int i = 0; i < controllables.size(); i++) {
            RobotControllable o = (RobotControllable) controllables.elementAt(i);
            o.control();
        }
    }

    public void testPeriodic() {
        LCD.print("Test: " + this.getStopwatchTime() + " sec");
    }
}
