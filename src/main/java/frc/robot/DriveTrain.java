  
package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends TeleopModule {

    SpeedControllerGroup leftMotors, rightMotors;
    private CANEncoder leftEncoder, rightEncoder;
    private double speedCoefficient = .4;
    private double originalSpeed = speedCoefficient;
    private double motorCoefficient = 1.225;
    // private double feet = 8.5;

    public DriveTrain() {
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor, Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor, Container.getInstance().backLeftMotor);
            
        if(ControlSystems.getInstance().gamepadHalfSpeed.get() || ControlSystems.getInstance().joystickHalfSpeed.get()){
            speedCoefficient *= (originalSpeed / 2);
        }
        else {
            speedCoefficient = originalSpeed;
        }
    }

    // private void moveForward() {
    //     leftMotors.set(-0.2);
    //     rightMotors.set(-0.25);
    // }
    // private void moveBackward() {
    //     leftMotors.set(0.2);
    //     rightMotors.set(0.25);
    // }
    // private void moveStop() {
    //     leftMotors.set(0);
    //     rightMotors.set(0);
    // }

    // public void robotJog() {

    // }

    @Override
    public void teleopControl() {
        double leftInputSpeed = speedCoefficient * ControlSystems.getInstance().dGamepadLeftY();
        double rightInputSpeed = speedCoefficient * ControlSystems.getInstance().dGamepadRightY();
        if(rightInputSpeed * motorCoefficient > 1){rightInputSpeed = 1;}
        leftMotors.set(leftInputSpeed); // left and right for gamepad
        rightMotors.set(rightInputSpeed*motorCoefficient); //this is because the right motors are a bit slow

        // leftMotors.set(ControlSystems.getInstance().leftSpeed()); // jesse we need to code, left and right for joystick
        // rightMotors.set(ControlSystems.getInstance().rightSpeed());

        // if (ControlSystems.getInstance().gamepadJogButton.get() || ControlSystems.getInstance().joystickJogButton.get()) {
            
        // }
    }
    @Override
    public void teleopInit() {
    }
    public void periodic() {
        
    }
}
