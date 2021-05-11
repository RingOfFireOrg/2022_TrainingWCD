package frc.robot;

import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.ControlSystems;

public class Shooter extends TeleopModule{

    double upperShooterCoefficient = 1;
    double lowerShooterCoefficient = 1;

    public Shooter() { 
    }

    /*public void lowerTrigger() {
        if manipulatorStickTrigger() {
            lowerShooter.set(ControlSystems.getInstance().manipulatorStickSpeed());
        }
    }*/

    public void teleopInit() {
    }

    public void teleopControl() {
        Container.getInstance().upperShooter.set(ControlSystems.getInstance().manipulatorStickSpeed() * upperShooterCoefficient);

        if(ControlSystems.getInstance().manipulatorStickTrigger()) {
            Container.getInstance().lowerShooter.set(ControlSystems.getInstance().manipulatorStickSpeed() * lowerShooterCoefficient);
        }
    }
}
