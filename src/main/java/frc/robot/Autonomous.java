package frc.robot;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

public class Autonomous {
    SpeedControllerGroup leftMotors, rightMotors;
    private CANEncoder leftEncoder, rightEncoder;
    int autonomousStep = 0;
    double FEET = 8.50; // To go one FEET, the robot encoder has to read ~8.50 inches of the wheel
    int leftEncoderOffset = 0;
    int rightEncoderOffset = 0;
    public AHRS ahrs;

    public void autonomousInit() {
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor, Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor, Container.getInstance().backLeftMotor);
        ahrs = new AHRS(SerialPort.Port.kUSB);
		ahrs.reset();
    }

    public double howFarLeft() {
        return Container.getInstance().getLeftInches()+leftEncoderOffset;
    }
    public double howFarRight() {
        return Container.getInstance().getRightInches()+rightEncoderOffset;
    }

    public void moveForward() {
        leftMotors.set(-0.5);
        rightMotors.set(-0.5);
    }
    public void moveBackward() {
        leftMotors.set(0.5);
        rightMotors.set(0.5);
    }
    public void turnLeft() {
        leftMotors.set(0.20);
        rightMotors.set(-0.20);
    }
    public void turnRight() {
        leftMotors.set(-0.20);
        rightMotors.set(0.20);
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
            case 0: {
                turnRight();
                if(howFarRight() + howFarLeft() > 50){
                    autonomousStep = 1; 
                    rightEncoderOffset = -50;
                }
                break;
            }
            case 1: {
                moveStop();
                break;
            }
            case 2: {
                moveBackward();
                break;
            }
            case 3: {
                moveStop();
                break;
            }
            case 4: {
                
            }
        }
        SmartDashboard.putNumber("How Far left", howFarLeft());
        SmartDashboard.putNumber("How Far right", howFarRight());        
    }
}