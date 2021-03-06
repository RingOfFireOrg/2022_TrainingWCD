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
    private int shooterPause = 0;
    private int moveTimer = 0;
    private int intakeInOutTimer = 0;
    private boolean targetFound = false;
 
    private double intakeInSpeed = 1;
    private double intakeOutSpeed = -1;
    private double transferInSpeed = 1;
    private double transferOutSpeed = -1;
 
    private String autonomousMode = "right";
 
    double[] visionVals;
 
    public void autonomousInit() {
        vision = new Vision();
        visionVals = vision.updateVisionVals();
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
        leftMotors.set(-0.4);
        rightMotors.set(-0.5);
    }
    public void moveBackward() { //nice
        leftMotors.set(0.4);
        rightMotors.set(0.5);
    }
    public void turnLeft() {
        leftMotors.set(0.075);
        rightMotors.set(-0.09375);
    }
    public void turnRight() {
        leftMotors.set(-0.075);
        rightMotors.set(0.09375);
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
        Container.getInstance().intake.set(intakeInSpeed*-1);
    }
 
    public void intakeOut(){
        Container.getInstance().intake.set(intakeInSpeed);
    }
 
    public void intakeStop(){
        Container.getInstance().intake.set(0);
    }
 
    public void transferIn(){
        Container.getInstance().transfer.set(transferInSpeed*-0.8);
        Container.getInstance().transfer2.set(transferInSpeed*-1);
    }
 
    public void transferOut(){
        Container.getInstance().transfer.set(transferOutSpeed*-0.8);
        Container.getInstance().transfer2.set(transferOutSpeed*-1);
    }
 
    public void transferStop(){
        Container.getInstance().transfer.set(0);
        Container.getInstance().transfer2.set(0);
    }
 
    public void visionAim(){
        visionVals = vision.updateVisionVals();
        if (visionVals[0] > -3 && visionVals[0] < 3){
            if(visionVals[3] == 1){
                moveStop();
                targetFound = true;
            }
        }else if(visionVals[0] < -3){
            turnLeft();
        }else{
            turnRight();
        }
    }
 
    public void autonomousPeriodic() {
        // Write the actual auto code here
        
        if(autonomousMode == "right"){
            switch (autonomousStep) {
                case -1: {
                    //Setup
                    resetNavX();
                    resetEncoders();
                    autonomousStep++;
                    break;
                }
                case 0: {
                    moveForward();
 
                    if (howFarRight() < -FEET*0.3){
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
                    moveBackward();
 
                    if(howFarRight() > 0){
                        autonomousStep++;
                    }
 
                    break;
                }
                case 3: {
                    moveStop();
                    resetEncoders();
                    autonomousStep++;
                    break;
                }
                case 4: {
                    //Turn to target
                    
                    visionAim();
 
                    if(targetFound == true){
                        autonomousStep++;
                        targetFound = false;
                    }
 
                    break;
                }
                case 5: {
                    moveStop();
                    autonomousStep++;
                    break;
                }
                case 6: {
                    //Transfer and Shoot (3 balls)
                                    
                    shoot();
 
                    if(shooterPause > 15){
                    transferIn();
                    }
                    shooterPause++;
 
                    shooterTimer++;
 
                    if(shooterTimer > 170){
                        shooterTimer = 0;
                        autonomousStep++;
                    }
 
                    //autonomousStep++;
 
                    break;
                }
                case 7: {
                    moveStop();
                    shooterStop();
                    transferStop();
                    autonomousStep++;
                    break;
                }
                case 8: {
                    //Right 90 degrees
                    turnRight();
                    
                    if(getabsoluteDirection() > 120){
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
                    //Forward 5.2'
                    
                    moveForward();
 
                    if (howFarRight() < -FEET*5.7){
                        autonomousStep++;
                    }
                    
                    break;
                }
                case 11: {
                    moveStop();
                    
                    autonomousStep++;
                    break;
                }
                case 12: {
                    //Right 90 degrees
                    turnRight();
                    
                    if(Math.abs(getabsoluteDirection()) > 164){
                        autonomousStep++;
                    }
                    break;
                }
                case 13: {
                    moveStop();
                    resetEncoders();
                    autonomousStep++;
                    break;
                }
                case 14: {
                    //Forward 16' 2.625"
                    //Intake
 
                    intakeIn();
                    moveForward();
 
                    if (howFarRight() < -FEET*16.85){
                        autonomousStep++;
                    }
 
                    break;
                }
                case 15: {
                    moveStop();
                    resetEncoders();
                    intakeStop();
                    autonomousStep++;
                    break;
                }
                case 16: {
                    //Backwards 16' 2.625"
                    
                    /*moveBackward();
 
                    if (howFarRight() > FEET*16.04){
                        autonomousStep++;
                    }*/
 
                    turnRight();
                    
                    if(Math.abs(getabsoluteDirection()) < 28){
                        autonomousStep++;
                    }
                    break;
 
                    /*if(intakeInOutTimer < 3){
                        intakeOut();
                    }else if(intakeInOutTimer < 15){
                        intakeIn();
                    }else{
                        intakeStop();
                        intakeInOutTimer = 0;  
                    }
                    intakeInOutTimer++;
 
                    break;*/
                }
                case 17: {
                    moveStop();
                    resetEncoders();
                    autonomousStep++;
                    break;
                }
                case 18: {
                    //Left 90 degrees
 
                    /*turnLeft();
                    
                    if(Math.abs(getabsoluteDirection()) < 100){
                        autonomousStep++; 
                    }*/
 
                    moveForward();
 
                    if (howFarRight() < -FEET*21){
                        autonomousStep++;
                    }
 
                    if(intakeInOutTimer < 8){
                        intakeOut();
                    }else if(intakeInOutTimer < 35){
                        intakeIn();
                    }else{
                        intakeStop();
                        intakeInOutTimer = 0;  
                    }
                    intakeInOutTimer++;
 
                    break;
                }
                case 19: {
                    moveStop();
                    resetEncoders();
                    intakeIn();
                    
                    autonomousStep++;
                    break;
                }
                case 20: {
                    //Backwards 5.2'
 
                    /*moveBackward();
 
                    if (howFarRight() > FEET*5.0){
                        autonomousStep++; 
                    }*/
                    autonomousStep++;
 
                    break;
                }
                case 21: {
                    moveStop();
                    
                    autonomousStep++;
                    break;
                }
                case 22: {
                    //Left 90 degrees
 
                    /*turnLeft();
                    
                    if(getabsoluteDirection() < 15){
                        autonomousStep++; 
                    }*/
                    autonomousStep++;
 
                    break;
                }
                case 23: {
                    moveStop();
                    
                    autonomousStep++;
                    targetFound = false;
                    break;
                }
                case 24: {     
                    //Turn to target
                    
                    //autonomousStep++;
 
                    visionAim();
 
                    if(intakeInOutTimer < 8){
                        intakeOut();
                    }else if(intakeInOutTimer < 35){
                        intakeIn();
                    }else{
                        intakeStop();
                        intakeInOutTimer = 0;  
                    }
                    intakeInOutTimer++;
 
                    if(targetFound == true){
                        autonomousStep++;
                    }
 
                    break;
                }
                case 25: {
                    moveStop();
                    autonomousStep++;
                    shooterPause = 0;
                    shooterTimer = 0;
                    intakeIn();
                    break;
                }
                case 26: {
                    //Shoot and Transfer
 
                    visionAim();
                    
                    shoot();
                    
                    if(shooterPause > 15){
                    transferIn();
                    }
                    shooterPause++;
 
                    shooterTimer++;
 
                    if(shooterTimer > 160){
                        shooterTimer = 0;
                        //autonomousStep++;
                    }
                    break;
                }
                case 27: {
                    //FINISHED!!!
                    moveStop();
                    shooterStop();
                    transferStop();
                    intakeStop();
                    Container.getInstance().ahrs.reset();
                    break;
                }
            }
        }else if(autonomousMode == "middle"){
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
 
                    visionAim();
 
                    break;
                }
                case 1: {
                    moveStop();
                    autonomousStep++;
                    break;
                }
                case 2: {
                    transferIn();
                    shoot();
 
                    shooterTimer++;
 
                    if(shooterTimer > 100){
                        shooterTimer = 0;
                        autonomousStep++;
                    }
                }
                case 3: {
                    moveStop();
                    shooterStop();
                    transferStop();
                    autonomousStep++;
                    break;
                }
                case 4: {
                    if(getabsoluteDirection() < 165){
                        turnRight();
                    }else{
                        autonomousStep++;
                    }
                }
                case 5: {
                    moveStop();
                    resetEncoders();
                    autonomousStep++;
                    break;
                }
                case 6: {
                    if(howFarRight() < FEET*3){
                        moveForward();
                    }else{
                        autonomousStep++;
                    }
                }
                case 7: {
                    moveStop();
                    break;
 
                    //Done
                }
            }
        }else if(autonomousMode == "left"){
            switch (autonomousStep) {
                case -1: {
                    //Setup
                    resetNavX();
                    resetEncoders();
                    autonomousStep++;
                    break;
                }
                case 0: {
                    if(howFarRight() < FEET*8){
                        moveForward();
                    }else{
                        autonomousStep++;
                    }
                }
                case 1: {
                    moveStop();
                    autonomousStep++;
                    break;
                }
                case 2: {
                    if(howFarRight() > 0){
                        moveBackward();
                    }else{
                        autonomousStep++;
                    }
                }
                case 3: {
                    moveStop();
                    autonomousStep++;
                    break;
                }
                case 4: {
                    if(getabsoluteDirection() < -80){
                        turnLeft();
                    }else{
                        autonomousStep++;
                    }
                }
                case 5: {
                    moveStop();
                    break;
 
                    //Done!
                }
            }
        }
        
        SmartDashboard.putNumber("How Far left", howFarLeft());
        SmartDashboard.putNumber("How Far right", howFarRight());
        SmartDashboard.putNumber("Absolute Direction", getabsoluteDirection());
        SmartDashboard.putNumber("Current Autonomous Step: ", autonomousStep);
        SmartDashboard.putString("Current Autonomous Mode: ", autonomousMode);
    }
}