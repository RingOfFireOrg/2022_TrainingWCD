package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends TeleopModule{
    
    private double intakeInSpeed = 0.6;
    private double intakeOutSpeed = -0.6;

    public void teleopInit() {
    }

    public void teleopControl() {
        if(ControlSystems.getInstance().intakeForward.get()) {
            Container.getInstance().intake.set(ControlMode.PercentOutput, intakeInSpeed);
        }
        else if (!ControlSystems.getInstance().intakeReverse.get()) {
            Container.getInstance().intake.set(ControlMode.PercentOutput, intakeOutSpeed);
        }
        else {
            Container.getInstance().intake.set(ControlMode.PercentOutput, 0);
        }
}
}