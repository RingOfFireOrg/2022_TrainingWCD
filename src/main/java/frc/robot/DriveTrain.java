  
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
    private double speedCoefficient = .8;

    public DriveTrain() {
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor, Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor, Container.getInstance().backLeftMotor);
            
        if(ControlSystems.getInstance().halfSpeed.get()){
            speedCoefficient = .4;
        }
        else {
            speedCoefficient = .8;
        }
    }

    @Override
    public void teleopControl() {
        double leftInputSpeed = speedCoefficient * ControlSystems.getInstance().dGamepadLeftY();
        double rightInputSpeed = speedCoefficient * ControlSystems.getInstance().dGamepadRightY();
        rightMotors.set(rightInputSpeed);
        leftMotors.set(leftInputSpeed);
    }
    @Override
    public void teleopInit() {
    }
    public void periodic() {
        
    }
}
