package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Intake.IntakeModes;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SerialPort;

public class Autonomous {
    private SpeedControllerGroup leftMotors, rightMotors;
    private CANEncoder leftEncoder, rightEncoder;
    private int autonomousStep = -1;
    private double FEET = 8.50; // To go one FEET, the robot encoder has to read ~8.50 inches of the wheel
    private double leftEncoderOffset = 0;
    private double rightEncoderOffset = 0;

    private double rotateToAngleRate;
    private float directionOffset = 0;
    private Vision vision;

    private VictorSP intake;
    private CANSparkMax lowerShooter;
    private CANSparkMax upperShooter;

    private int shooterTimer = 0;

    public void autonomousInit() {
        vision = new Vision();
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor,
                Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor,
                Container.getInstance().backLeftMotor);

        intake = Container.getInstance().intake;
        lowerShooter = Container.getInstance().lowerShooter;
        upperShooter = Container.getInstance().upperShooter;
    }

    public double howFarLeft() {
        return Container.getInstance().getLeftInches()-leftEncoderOffset;
    }
    public double howFarRight() {
        return Container.getInstance().getRightInches()-rightEncoderOffset;
    }

    public double howFarLeftReal() {
        return Container.getInstance().getLeftInches();
    }
    public double howFarRightReal() {
        return Container.getInstance().getRightInches();
    }

    public void moveForward() {
        leftMotors.set(-0.2);
        rightMotors.set(-0.2);
    }
    public void moveBackward() {
        leftMotors.set(0.2);
        rightMotors.set(0.2);
    }
    public void turnLeft() {
        leftMotors.set(0.075);
        rightMotors.set(-0.075);
    }
    public void turnRight() {
        leftMotors.set(-0.075);
        rightMotors.set(0.075);
    }
    public void moveStop() {
        leftMotors.set(0);
        rightMotors.set(0);
    }

    public void resetEncoders() {
        leftEncoderOffset = howFarLeftReal();
        rightEncoderOffset = howFarRightReal();
    }

    public void resetNavX() {
        directionOffset = getrealabsoluteDirection();
    }

    public float getabsoluteDirection() {
        return Container.getInstance().ahrs.getYaw()-directionOffset;
    }

    public float getrealabsoluteDirection() {
        return Container.getInstance().ahrs.getYaw();
    }

    public void Shoot() {
        lowerShooter.set(Shooter.lowerShooterCoefficient);
        upperShooter.set(Shooter.upperShooterCoefficient);
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
                
                autonomousStep++;

                /*if (visionVals[0] > -5 && visionVals[0] < 5){
                    if(visionVals[3] == 1){
                        autonomousStep++;
                    }
                }else if(visionVals[0] < -5){
                    turnLeft();
                }else{
                    turnRight();
                }*/

                break;
            }
            case 1: {
                moveStop();
                autonomousStep++;
                break;
            }
            case 2: {
                //Transfer and Shoot (3 balls)
                
                //Currently commented out for testing the rest of auto
                
                /*Shoot();

                if(shooterTimer > 180){
                    shooterTimer = 0;
                    autonomousStep++;
                }

                shooterTimer++;*/

                autonomousStep++;

                break;
            }
            case 3: {
                moveStop();
                autonomousStep++;
                break;
            }
            case 4: {
                //Right 90 degrees
                turnRight();
                
                if(getabsoluteDirection() > 80){
                    autonomousStep++;
                }
                break;
            }
            case 5: {
                moveStop();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 6: {
                //Forward 5.2'
                
                moveForward();

                if (howFarRight() < -FEET*5.21){
                    autonomousStep++;
                }
                /*if (howFarRight() < -FEET){
                    autonomousStep++;
                }*/
                
                break;
            }
            case 7: {
                moveStop();
                
                autonomousStep++;
                break;
            }
            case 8: {
                //Right 90 degrees
                turnRight();
                
                if(Math.abs(getabsoluteDirection()) > 165){
                    autonomousStep++;
                }
                break;
            }
            case 9: {
                moveStop();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 10: {
                //Forward 16' 2.625"
                //Intake

                moveForward();

                if (howFarRight() < -FEET*16.14){
                    autonomousStep++;
                }
                /*if (howFarRight() < -FEET*2){
                    autonomousStep++;
                }*/

                break;
            }
            case 11: {
                moveStop();
                resetEncoders();
                autonomousStep++;
                break;
            }
            case 12: {
                //Backwards 16' 2.625"
                
                moveBackward();

                if (howFarRight() > FEET*16.14){
                    autonomousStep++;
                }
                /*if (howFarRight() > FEET*2){
                    autonomousStep++;
                }*/

                break;
            }
            case 13: {
                moveStop();
                 
                autonomousStep++;
                break;
            }
            case 14: {
                //Left 90 degrees

                turnLeft();
                
                if(Math.abs(getabsoluteDirection()) < 100){
                    autonomousStep++; 
                }

                break;
            }
            case 15: {
                moveStop();
                resetEncoders();
                 
                autonomousStep++;
                break;
            }
            case 16: {
                //Backwards 5.2'

                moveBackward();

                if (howFarRight() > FEET*5.21){
                    autonomousStep++;
                }
                /*if (howFarRight() > FEET){
                    autonomousStep++;
                }*/

                break;
            }
            case 17: {
                moveStop();
                 
                autonomousStep++;
                break;
            }
            case 18: {
                //Left 90 degrees

                turnLeft();
                
                if(getabsoluteDirection() < 10){
                    autonomousStep++; 
                }

                break;
            }
            case 19: {
                moveStop();
                 
                autonomousStep++;
                break;
            }
            case 20: {
                //Transfer and Shoot (3 balls)
                
                /*Shoot();

                if(shooterTimer > 180){
                    shooterTimer = 0;
                    autonomousStep++;
                }

                shooterTimer++;*/

                autonomousStep++;
                
                break;
            }
            case 21: {
                //FINISHED!!!
                moveStop();
                
                break;
            }
        }
        SmartDashboard.putNumber("How Far left", howFarLeft());
        SmartDashboard.putNumber("How Far right", howFarRight());    
        SmartDashboard.putNumber("Absolute Direction", getabsoluteDirection());
        SmartDashboard.putNumber("Current Step: ", autonomousStep);
    }
}