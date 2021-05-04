package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.ControlSystems;

public class Turret extends Container {
    
    
    //ControlSystems controlSystemsObject  = new ControlSystems();
    public void teleopInit() {
        turretMotor = new CANSparkMax(RobotMap.TURRET_SPINNER, MotorType.kBrushless);
    }

    public void teleopControl() {
        turretMotor.set(ControlSystems.getInstance().manipulatorStickTwist() * 0.5);
    }
}           
