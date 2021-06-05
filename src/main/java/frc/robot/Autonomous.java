package frc.robot;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Autonomous {
    SpeedControllerGroup leftMotors, rightMotors;
    private CANEncoder leftEncoder, rightEncoder;
    int autonomousStep = 0;
    double FEET = 8.50; // To go one FEET, the robot encoder has to read ~8.50 inches of the wheel
    int time = 0;
    public void autonomousInit() {
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor, Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor, Container.getInstance().backLeftMotor);
    }

    public void howFarLeft() {

    }
    public void howFarRight() {
        
    }

    public void moveForward() {
        leftMotors.set(0.5);
        rightMotors.set(0.5);
    }
    public void moveBackward() {
        leftMotors.set(-0.5);
        rightMotors.set(-0.5);
    }
    public void turnLeft() {
        leftMotors.set(-0.20);
        rightMotors.set(0.20);
    }
    public void turnRight() {
        leftMotors.set(0.20);
        rightMotors.set(-0.20);
    }
    public void moveStop() {
        leftMotors.set(0);
        rightMotors.set(0);
    }
    public void getAbsoluteDirection() {
        //To do
    }

    public void autonomousPeriodic() {
        // Write the actual auto code here
        switch (autonomousStep) {
            case 0:
                moveForward();
                break;
        }
    }
}