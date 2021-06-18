package frc.robot;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SerialPort;

public class Autonomous {
    SpeedControllerGroup leftMotors, rightMotors;
    private CANEncoder leftEncoder, rightEncoder;
    int autonomousStep = -1;
    double FEET = 8.50; // To go one FEET, the robot encoder has to read ~8.50 inches of the wheel
    double leftEncoderOffset = 0;
    double rightEncoderOffset = 0;
    
    double rotateToAngleRate;
    float directionOffset = 0;
    Vision vision;
    public void autonomousInit() {
        vision = new Vision();
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor, Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor, Container.getInstance().backLeftMotor);
    }

    public double howFarLeft() {
        return Container.getInstance().getLeftInches()-leftEncoderOffset;
    }
    public double howFarRight() {
        return Container.getInstance().getRightInches()-rightEncoderOffset;
    }

    public void moveForward() {
        leftMotors.set(-0.3);
        rightMotors.set(-0.3);
    }
    public void moveBackward() {
        leftMotors.set(0.3);
        rightMotors.set(0.3);
    }
    public void turnLeft() {
        leftMotors.set(0.15);
        rightMotors.set(-0.15);
    }
    public void turnRight() {
        leftMotors.set(-0.15);
        rightMotors.set(0.15);
    }
    public void moveStop() {
        leftMotors.set(0);
        rightMotors.set(0);
    }

    public void resetEncoders() {
        leftEncoderOffset = howFarLeft();
        rightEncoderOffset = howFarRight();
    }

    public void resetNavX() {
        directionOffset = getabsoluteDirection();
    }

    public float getabsoluteDirection() {
        return Container.getInstance().ahrs.getYaw()-directionOffset;
    }

    

    

    

    public void autonomousPeriodic() {
        // Write the actual auto code here
        double[] visionVals = vision.updateVisionVals();
        switch (autonomousStep) {
            case -1: {
                //Setup
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 0: {     
                //Turn to target
                
                //To do: see if a target was found before adding to autonomous step

                if (visionVals[0] > -5 && visionVals[0] < 5){
                    autonomousStep++;
                }else if(visionVals[0] < -5){
                    turnLeft();
                }else{
                    turnRight();
                }

                break;
            }
            case 1: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 2: {
                //Transfer and Shoot (3 balls)
                
                //When done:
                    autonomousStep++;

                break;
            }
            case 3: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 4: {
                //Right 90 degrees
                
                turnRight();
                
                if(getabsoluteDirection() > 75){
                    autonomousStep++; 
                }
                break;
            }
            case 5: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 6: {
                //Forward 5.2'
                moveForward();

                if (howFarRight() > FEET/2){
                    autonomousStep++;
                }
                
                break;
            }
            case 7: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 8: {
                //Right 90 degrees
                turnRight();
                
                if(getabsoluteDirection() > 75){
                    autonomousStep++; 
                }
                break;
            }
            case 9: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 10: {
                //Forward 16' 2.625"
                //Intake and Transfer on

                //Just a placeholder, won't go to next case for now:
                    moveStop();
                
                break;
            }
            case 11: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 12: {
                //Backwards 16' 2.625"
                
               
                break;
            }
            case 13: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 14: {
                //Left 90 degrees

                
                break;
            }
            case 15: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 16: {
                //Backwards 5.2'

                
            }
            case 17: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 18: {
                //Left 90 degrees

                
                break;
            }
            case 19: {
                moveStop();
                resetNavX();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 20: {
                //Transfer and Shoot (3 balls)
                
                //When done:
                    autonomousStep++;
                
                break;
            }
            case 21: {
                //FINISHED!!!
                moveStop();
                resetNavX();
                resetEncoders();
                //autonomousStep++;
                break;
            }
        }
        SmartDashboard.putNumber("How Far left", howFarLeft());
        SmartDashboard.putNumber("How Far right", howFarRight());    
        SmartDashboard.putNumber("Absolute Direction", getabsoluteDirection());    
    }
}