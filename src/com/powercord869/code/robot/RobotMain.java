/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import com.powercord869.code.robot.autonomous.AutonomousNode;
import com.powercord869.code.robot.autonomous.DriveAndTurnRoutine;
import com.powercord869.code.robot.autonomous.DriveAndRunFanRoutine;
import edu.wpi.first.wpilibj.Compressor;
import java.util.Vector;

public class RobotMain extends IterativeRobot {

    private Vector controllables = new Vector();
    private Vector autonomousNodes = new Vector();
    private Compressor comp;
    private AutonomousNode o = null;

    public void robotInit() {
        comp = new Compressor(1, 1);
        controllables.addElement(RobotDrive.getInstance());
        controllables.addElement(Fan.getInstance());
        controllables.addElement(Climber.getInstance());
        autonomousNodes.addElement(DriveAndRunFanRoutine.getInstance());
        autonomousNodes.addElement(DriveAndTurnRoutine.getInstance());
        comp.start();
    }

    public void disabledInit() {
    }

    public void autonomousInit() {
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
        if (autonomousNodes.size() >= 1) {
            for (int i = 0; i < autonomousNodes.size(); i++) {
                AutonomousNode temp = (AutonomousNode)autonomousNodes.elementAt(i);
                if (o == null || (o.getRoutineNumber() != temp.getRoutineNumber() && temp.validate())) {
                    o = temp;
                }
                if(o.validate()){
                    o.run();
                }
            }
        }
        LCD.print("Auto: " + this.getStopwatchTime() + " sec");
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
