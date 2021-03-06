  
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

    public DriveTrain() {
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor, Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor, Container.getInstance().backLeftMotor);

    }
    @Override
    public void teleopControl() {
        if(ControlSystems.getInstance().halfSpeed.get()){
            speedCoefficient = (originalSpeed / 2);
        }
        else {
            speedCoefficient = originalSpeed;
        }
        double leftInputSpeed = speedCoefficient * ControlSystems.getInstance().dGamepadLeftY();
        double rightInputSpeed = speedCoefficient * ControlSystems.getInstance().dGamepadRightY();
        if(rightInputSpeed * motorCoefficient > 1){rightInputSpeed = 1;}
        rightMotors.set(rightInputSpeed*motorCoefficient); //this is because the right motors are a bit slow        
        leftMotors.set(leftInputSpeed);
    }
    @Override
    public void teleopInit() {
    }
    public void periodic() {
        
    }
}
