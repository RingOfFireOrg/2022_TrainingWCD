  
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

public class DriveTrain extends Container {

    SpeedControllerGroup leftMotors, rightMotors;
    private CANEncoder leftEncoder, rightEncoder;

    public DriveTrain() {
        CANSparkMax rightForward = new CANSparkMax(RobotMap.DT_RIGHT_FORWARD, MotorType.kBrushless);
        rightForward.setInverted(true);
        CANSparkMax rightBack = new CANSparkMax(RobotMap.DT_RIGHT_BACK, MotorType.kBrushless);
        rightBack.setInverted(true);
        CANSparkMax leftForward = new CANSparkMax(RobotMap.DT_LEFT_FORWARD, MotorType.kBrushless);
        leftForward.setInverted(false);
        CANSparkMax leftBack = new CANSparkMax(RobotMap.DT_LEFT_BACK, MotorType.kBrushless);
        leftBack.setInverted(false);
        rightMotors = new SpeedControllerGroup(rightForward, rightBack);
        leftMotors = new SpeedControllerGroup(leftForward, leftBack);
    }
  /*  public double getLeftInches() {
        return leftEncoder.getPosition() / RobotMap.DRIVEBASE_GEAR_RATIO * Math.PI * RobotMap.DRIVE_WHEEL_DIAMETER_IN;
    }

    public double getRightInches() {
        return rightEncoder.getPosition() / RobotMap.DRIVEBASE_GEAR_RATIO * Math.PI * RobotMap.DRIVE_WHEEL_DIAMETER_IN;
    }*/

    @Override
    public void teleopControl() {
        double leftInputSpeed = .8 * ControlSystems.getInstance().leftSpeed();
        double rightInputSpeed = .8 * ControlSystems.getInstance().rightSpeed();
        rightMotors.set(rightInputSpeed);
        leftMotors.set(leftInputSpeed);
    }
    @Override
    public void teleopInit() {
    }
}
