package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class Robot extends TimedRobot {

  Joystick rightstick = new Joystick(0);
  Joystick leftstick = new Joystick(1);
  Joystick manipulatorStick = new Joystick(2);

  public CANSparkMax frontLeftMotor;
  public CANSparkMax frontRightMotor;
  public CANSparkMax backRightMotor;
  public CANSparkMax backLeftMotor;
  public CANSparkMax neoPrototypeMotor;


  @Override
  public void robotInit() {
    
    neoPrototypeMotor = new CANSparkMax(RobotMap.TURRET_SPINNER, MotorType.kBrushless);
  }

  @Override
  public void robotPeriodic() {

    double rightSpeed = rightstick.getY();
    double leftSpeed = leftstick.getY();
    double manipulatorStickSpeed = manipulatorStick.getY();

    neoPrototypeMotor.set(manipulatorStickSpeed);
    //neoDrive.drive(rightSpeed, leftSpeed, 1.0, true);
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopPeriodic() {
    
  }

  @Override
  public void testPeriodic() {
    
  }
}
