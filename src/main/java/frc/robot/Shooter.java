package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends TeleopModule {

    static double upperShooterCoefficient = 0.8;
    static double lowerShooterCoefficient = 0.8;

    public Shooter() { 
    }

    public void lowerTrigger() {
        /*if (ControlSystems.getInstance().manipulatorStickTrigger()) {
            Container.getInstance().lowerShooter.set(ControlSystems.getInstance().manipulatorStickSpeed() * lowerShooterCoefficient);
        }*/
    }
    public void teleopInit() {
    }

    public void teleopControl() {
        Container.getInstance().upperShooter.set(ControlSystems.getInstance().gamepadRightTrigger() * upperShooterCoefficient);

       // if(ControlSystems.getInstance().manipulatorStickTrigger()) {
            Container.getInstance().lowerShooter.set(ControlSystems.getInstance().gamepadLeftTrigger() * lowerShooterCoefficient);
        //} 
        SmartDashboard.putNumber("Left Trigger: ", ControlSystems.getInstance().gamepadRightTrigger() * upperShooterCoefficient);
        SmartDashboard.putNumber("Right Trigger: ", ControlSystems.getInstance().gamepadLeftTrigger() * lowerShooterCoefficient);
        SmartDashboard.putNumber("Lower Shooter Encoder Val: ", ControlSystems.getInstance().gamepadRightTrigger() * upperShooterCoefficient);
        SmartDashboard.putNumber("Upper Shooter Encoder Val: ", ControlSystems.getInstance().gamepadLeftTrigger() * lowerShooterCoefficient);
    }
    
    public void periodic() {
        
    }
}
