package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.ControlSystems;

public class Shooter extends Container{

    double upperShooterCoefficient = 1;
    double lowerShooterCoefficient = 1;

    public Shooter() {
        upperShooter = new CANSparkMax(RobotMap.UPPER_SHOOTER, MotorType.kBrushless);
        lowerShooter = new CANSparkMax(RobotMap.LOWER_SHOOTER, MotorType.kBrushless);   
    }

    /*public void lowerTrigger() {
        if manipulatorStickTrigger() {
            lowerShooter.set(ControlSystems.getInstance().manipulatorStickSpeed());
        }
    }*/

    public void teleopInit() {
     
    }

    public void teleopControl() {
        upperShooter.set(ControlSystems.getInstance().manipulatorStickSpeed() * upperShooterCoefficient);
        lowerShooter.set(ControlSystems.getInstance().manipulatorStickSpeed() * lowerShooterCoefficient);

    }
}
