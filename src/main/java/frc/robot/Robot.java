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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;




public class Robot extends TimedRobot {

protected DriveTrain driveTrain;
protected Turret turret;
protected Shooter shooter;
protected Vision vision;
protected Autonomous auto;
protected Intake intake;
protected Transfer transfer;


  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    turret = new Turret();
    vision = new Vision();
    shooter = new Shooter();
    auto = new Autonomous();
    intake = new Intake();
    transfer = new Transfer();

    driveTrain.teleopInit();
    turret.teleopInit();
    shooter.teleopInit();
    intake.teleopInit();
    transfer.teleopInit();
  }

  @Override
  public void robotPeriodic() {
    intake.periodic();
    transfer.periodic();
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
    intake.teleopControl();
    transfer.teleopControl();



    vision.updateVisionVals();
  }

  @Override
  public void testPeriodic() {
    
  }
}
