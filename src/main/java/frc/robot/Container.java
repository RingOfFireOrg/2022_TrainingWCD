package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

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
    public VictorSP transfer;
    //public CANSparkMax turretMotor;
    public CANEncoder leftEncoder, rightEncoder;
    public CANEncoder lowerShooterEncoder, upperShooterEncoder;
    private static Container theTrueContainer;
    public VictorSP shooterAngler;

    public AHRS ahrs;

    private Container() {
        frontLeftMotor = new CANSparkMax(RobotMap.DT_LEFT_FORWARD, MotorType.kBrushless);
        frontLeftMotor.setInverted(true);
        frontRightMotor = new CANSparkMax(RobotMap.DT_RIGHT_FORWARD, MotorType.kBrushless);
        frontRightMotor.setInverted(false);
        backRightMotor = new CANSparkMax(RobotMap.DT_RIGHT_BACK, MotorType.kBrushless);
        backRightMotor.setInverted(false);
        backLeftMotor = new CANSparkMax(RobotMap.DT_LEFT_BACK, MotorType.kBrushless);
        backLeftMotor.setInverted(true);
        leftEncoder = frontLeftMotor.getEncoder();
        rightEncoder = frontRightMotor.getEncoder();
        upperShooter = new CANSparkMax(RobotMap.UPPER_SHOOTER, MotorType.kBrushless);
        lowerShooter = new CANSparkMax(RobotMap.LOWER_SHOOTER, MotorType.kBrushless);
        //turretMotor = new CANSparkMax(RobotMap.TURRET_SPINNER, MotorType.kBrushless);
        intake = new VictorSP(RobotMap.INTAKE);
        transfer = new VictorSP(RobotMap.TRANSFER);
        ahrs = new AHRS(SerialPort.Port.kUSB);
        lowerShooterEncoder = lowerShooter.getEncoder();
        upperShooterEncoder = upperShooter.getEncoder();
        shooterAngler = new VictorSP(RobotMap.SHOOTER_ANGLER);
		ahrs.reset();
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
