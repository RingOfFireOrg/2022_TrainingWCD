package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;

public class Container {
  
	public static PID drive;
	public CANSparkMax frontLeftMotor;
    public CANSparkMax frontRightMotor;
    public CANSparkMax backRightMotor;
    public CANSparkMax backLeftMotor;
    public CANSparkMax upperShooter;
    public CANSparkMax lowerShooter;
    public VictorSP intake;
    public CANSparkMax turretMotor;
    public CANEncoder leftEncoder, rightEncoder;
    private static Container theTrueContainer;

    private Container() {
        /*frontLeftMotor = new CANSparkMax(RobotMap.DT_LEFT_FORWARD, MotorType.kBrushless);
        frontLeftMotor.setInverted(false);
        frontRightMotor = new CANSparkMax(RobotMap.DT_RIGHT_FORWARD, MotorType.kBrushless);
        frontRightMotor.setInverted(true);
        backRightMotor = new CANSparkMax(RobotMap.DT_RIGHT_BACK, MotorType.kBrushless);
        backRightMotor.setInverted(true);
        backLeftMotor = new CANSparkMax(RobotMap.DT_LEFT_BACK, MotorType.kBrushless);
        backLeftMotor.setInverted(false);
        leftEncoder = frontLeftMotor.getEncoder();
        rightEncoder = frontRightMotor.getEncoder();
        upperShooter = new CANSparkMax(RobotMap.UPPER_SHOOTER, MotorType.kBrushless);
        lowerShooter = new CANSparkMax(RobotMap.LOWER_SHOOTER, MotorType.kBrushless);
        turretMotor = new CANSparkMax(RobotMap.TURRET_SPINNER, MotorType.kBrushless);
        intake = new VictorSP(RobotMap.INTAKE);
    }
    public double getLeftInches() {
        return leftEncoder.getPosition() / RobotMap.DRIVEBASE_GEAR_RATIO * Math.PI * RobotMap.DRIVE_WHEEL_DIAMETER_IN;
    }

    public double getRightInches() {
        return rightEncoder.getPosition() / RobotMap.DRIVEBASE_GEAR_RATIO * Math.PI * RobotMap.DRIVE_WHEEL_DIAMETER_IN;
    }

    public static Container getInstance() {
        if (theTrueContainer != null) {
            return theTrueContainer;
        }                   
        else {
            theTrueContainer = new Container();
            return theTrueContainer;
        }
    }
}
