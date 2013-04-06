/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot;

import com.powercord869.code.robot.autonomous.AutonomousNode;
import com.powercord869.code.robot.autonomous.MoveForward;
import com.powercord869.code.robot.autonomous.RunFan;
import edu.wpi.first.wpilibj.Compressor;
import java.util.Vector;

public class RobotMain extends IterativeRobot {

    private Vector controllables = new Vector();
    private Vector autonomousNodes = new Vector();
    private Compressor comp;

    public void robotInit() {
        comp = new Compressor(1,1);
        controllables.addElement(RobotDrive.getInstance());
        controllables.addElement(Fan.getInstance());
        controllables.addElement(Climber.getInstance());
        autonomousNodes.addElement(RunFan.getInstance());
        autonomousNodes.addElement(MoveForward.getInstance());
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

    public void autonomousPeriodic() {
        if (autonomousNodes.size() >= 1) {
            for (int i = 0; i < autonomousNodes.size(); i++) {
                AutonomousNode o = (AutonomousNode) autonomousNodes.elementAt(i);
                if (o.validate()) {
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
