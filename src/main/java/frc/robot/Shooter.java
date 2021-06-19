package frc.robot;

public class Shooter extends TeleopModule{

    static double upperShooterCoefficient = 1;
    static double lowerShooterCoefficient = 1;

    public Shooter() { 
    }

    public void lowerTrigger() {
        if (ControlSystems.getInstance().manipulatorStickTrigger()) {
            Container.getInstance().lowerShooter.set(ControlSystems.getInstance().manipulatorStickSpeed() * lowerShooterCoefficient);
        }
    }

    public void teleopInit() {
    }

    public void teleopControl() {
        Container.getInstance().upperShooter.set(ControlSystems.getInstance().gamepadRightTrigger() * upperShooterCoefficient);

        if(ControlSystems.getInstance().manipulatorStickTrigger()) {
            Container.getInstance().lowerShooter.set(ControlSystems.getInstance().gamepadLeftTrigger() * lowerShooterCoefficient);
        }

    }
    public void periodic() {
        
    }
}
