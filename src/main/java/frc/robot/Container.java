package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Container {
  
	public static PID drive;
	public CANSparkMax frontLeftMotor;
    public CANSparkMax frontRightMotor;
    public CANSparkMax backRightMotor;
    public CANSparkMax backLeftMotor;
    public CANSparkMax upperShooter;
    public CANSparkMax lowerShooter;
    public TalonSRX intake;
    public CANSparkMax turretMotor;
    private static Container theTrueContainer;

    private Container() {
        frontLeftMotor = new CANSparkMax(RobotMap.DT_LEFT_FORWARD, MotorType.kBrushless);
        frontLeftMotor.setInverted(false);
        frontRightMotor = new CANSparkMax(RobotMap.DT_RIGHT_FORWARD, MotorType.kBrushless);
        frontRightMotor.setInverted(true);
        backRightMotor = new CANSparkMax(RobotMap.DT_RIGHT_BACK, MotorType.kBrushless);
        backRightMotor.setInverted(true);
        backLeftMotor = new CANSparkMax(RobotMap.DT_LEFT_BACK, MotorType.kBrushless);
        backLeftMotor.setInverted(false);
        /*upperShooter = new CANSparkMax(RobotMap.UPPER_SHOOTER, MotorType.kBrushless);
        lowerShooter = new CANSparkMax(RobotMap.LOWER_SHOOTER, MotorType.kBrushless);
        turretMotor = new CANSparkMax(RobotMap.TURRET_SPINNER, MotorType.kBrushless);
        intake = new TalonSRX(RobotMap.INTAKE);*/
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
