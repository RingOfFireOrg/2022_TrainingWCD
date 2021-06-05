package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends TeleopModule{
    
    private double intakeInSpeed = 0.6;
    private double intakeOutSpeed = -0.6;
    public enum IntakeModes {
        OUT, IN, OFF
    }

    IntakeModes mode;

    public Intake() {
    }

    public void teleopInit() {
    }

    public void teleopControl() {
        if(ControlSystems.getInstance().intakeForward.get()) {
            Container.getInstance().intake.set(intakeInSpeed);
        }
        else if (ControlSystems.getInstance().intakeReverse.get()) {
            Container.getInstance().intake.set(intakeOutSpeed);
        }
        else {
            Container.getInstance().intake.set(0);
        }
        //Container.getInstance().intake.set(ControlSystems.getInstance().manipulatorStickSpeed());
}
}