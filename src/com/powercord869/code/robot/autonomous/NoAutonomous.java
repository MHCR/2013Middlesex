/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powercord869.code.robot.autonomous;

public class NoAutonomous extends AutonomousRoutine {
    public NoAutonomous() {
        
    }
    public void run() {
        //will never run
    }
    public boolean validate() {
        //always stop!
        return false;
    }
    public void stop() {
        //stop everything here!
    }  
}
