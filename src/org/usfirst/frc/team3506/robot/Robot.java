package org.usfirst.frc.team3506.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Joystick leftJoystick;
	Joystick rightJoystick;
	RobotDrive driveTrain;
	boolean previousButtonDown;
	long stopTime;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		leftJoystick = new Joystick(4);
		rightJoystick = new Joystick(1);
		driveTrain = new RobotDrive(0, 3, 1, 2);

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		double leftY = leftJoystick.getY();
		double rightY = rightJoystick.getY();
		boolean buttonDown = leftJoystick.getRawButton(8);
		long now = System.currentTimeMillis();

		leftY *= 0.7;
		rightY *= 0.7;

		driveTrain.tankDrive(-leftY, -rightY);
		
		if (buttonDown == true && previousButtonDown == false) {
			stopTime = now;
			stopTime += 2000;
			
		}
		
		if (stopTime != 0 && stopTime >= now) {
			driveTrain.tankDrive(0.7, -0.7);
			
		} else {
			stopTime = 0;
		}
		
		previousButtonDown = buttonDown;

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
