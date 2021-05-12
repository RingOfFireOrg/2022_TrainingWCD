package frc.robot;

public class Shooter extends TeleopModule{

    private double upperShooterCoefficient = 1;
    private double lowerShooterCoefficient = 1;

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
