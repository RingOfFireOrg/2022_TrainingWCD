package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Intake.IntakeModes;

import javax.swing.TransferHandler.TransferSupport;

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

    private int shooterTimer = 0;

    private double intakeInSpeed = 1;
    private double intakeOutSpeed = -1;
    private double transferInSpeed = 1;
    private double transferOutSpeed = -1;

    private int autonomousMode = 1;

    public void autonomousInit() {
        vision = new Vision();
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor,
                Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor,
                Container.getInstance().backLeftMotor);

        //intake = Container.getInstance().intake;
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
        rightMotors.set(-0.25);
    }
    public void moveBackward() {
        leftMotors.set(0.2);
        rightMotors.set(0.25);
    }
    public void turnLeft() {
        leftMotors.set(0.075);
        rightMotors.set(-0.092);
    }
    public void turnRight() {
        leftMotors.set(-0.075);
        rightMotors.set(0.092);
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

    public void shoot() {
        Container.getInstance().upperShooter.set(0.8);
        Container.getInstance().lowerShooter.set(0.8);
    }

    public void shooterStop() {
        Container.getInstance().upperShooter.set(0);
        Container.getInstance().lowerShooter.set(0);
    }

    public void intakeIn(){
        Container.getInstance().intake.set(intakeInSpeed);
    }

    public void intakeStop(){
        Container.getInstance().intake.set(intakeOutSpeed);
    }

    public void transferIn(){
        Container.getInstance().transfer.set(transferInSpeed);
    }

    public void transferOut(){
        Container.getInstance().transfer.set(transferOutSpeed);
    }

    public void transferStop(){
        Container.getInstance().transfer.set(0);
    }


    public void autonomousPeriodic() {
        // Write the actual auto code here
        
        if(autonomousMode == 1){
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

                    if(vision.aimToTarget()) {
                        autonomousStep++;
                    }

                    break;
                }
                case 1: {
                    moveStop();
                    autonomousStep++;
                    break;
                }
                case 2: {
                    //Transfer and Shoot (3 balls)
                                    
                    //shoot();

                    transferIn();

                    shooterTimer++;

                    if(shooterTimer > 50){
                        shooterTimer = 0;
                        //autonomousStep++;
                    }

                    //autonomousStep++;

                    break;
                }
                case 3: {
                    moveStop();
                    shooterStop();
                    transferStop();
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
                    resetEncoders();
                    autonomousStep++;
                    break;
                }
                case 6: {
                    //Forward 5.2'
                    
                    moveForward();

                    /*if (howFarRight() < -FEET*5.21){
                        autonomousStep++;
                    }*/
                    if (howFarRight() < -FEET*5.21){
                        //moveStop();
                        autonomousStep++;
                    }
                    
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
                    
                    if(Math.abs(getabsoluteDirection()) > 163){
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

                    intakeIn();
                    moveForward();

                    /*if (howFarRight() < -FEET*16.14){
                        autonomousStep++;
                    }*/
                    if (howFarRight() < -FEET*16.14){
                        autonomousStep++;
                    }

                    break;
                }
                case 11: {
                    moveStop();
                    resetEncoders();
                    intakeStop();
                    autonomousStep++;
                    break;
                }
                case 12: {
                    //Backwards 16' 2.625"
                    
                    moveBackward();

                    /*if (howFarRight() > FEET*16.14){
                        autonomousStep++;
                    }*/
                    if (howFarRight() > FEET*16.14){
                        autonomousStep++;
                    }

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

                    /*if (howFarRight() > FEET*5.21){
                        autonomousStep++;
                    }*/
                    if (howFarRight() > FEET*5.21){
                        autonomousStep++;
                    }

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
                    
                    if(getabsoluteDirection() < 15){
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
                    //Turn to target
                    
                    //autonomousStep++;

                    if(vision.aimToTarget()) {
                        autonomousStep++;
                    }

                    break;
                }
                case 21: {
                    moveStop();
                    autonomousStep++;
                    break;
                }
                case 22: {
                    //Shoot and Transfer
                    
                    shoot();
                    transferIn();

                    shooterTimer++;

                    if(shooterTimer > 50){
                        shooterTimer = 0;
                        autonomousStep++;
                    }
                }
                case 23: {
                    //FINISHED!!!
                    moveStop();
                    shooterStop();
                    transferStop();
                    intakeStop();
                    Container.getInstance().ahrs.reset();
                    break;
                }
            }
        }else if(autonomousMode == 2){
            
        }
        SmartDashboard.putNumber("How Far left", howFarLeft());
        SmartDashboard.putNumber("How Far right", howFarRight());
        SmartDashboard.putNumber("Absolute Direction", getabsoluteDirection());
        SmartDashboard.putNumber("Current Autonomous Step: ", autonomousStep);
    }
}
