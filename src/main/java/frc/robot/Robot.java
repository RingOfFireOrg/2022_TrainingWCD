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

protected DriveTrain driveTrain;
protected Turret turret;
protected Shooter shooter;
protected Vision vision;
protected Autonomous auto;


  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    turret = new Turret();
    shooter = new Shooter();
    vision = new Vision();
    auto = new Autonomous();

    driveTrain.teleopInit();
    turret.teleopInit();
    shooter.teleopInit();
    //vision.teleopInit();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    auto.autonomousInit();
  }

  @Override
  public void autonomousPeriodic() {
    auto.autonomousPeriodic();
  }

  @Override
  public void teleopPeriodic() {
    driveTrain.teleopControl();
    turret.teleopControl();
    shooter.teleopControl();
    //vision.teleopControl();
  }

  @Override
  public void testPeriodic() {
    
  }
}
