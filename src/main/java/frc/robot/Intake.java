package frc.robot;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class Intake extends TeleopModule{
    
    private double intakeInSpeed = 0.6;
    private double intakeOutSpeed = -0.6;
    public enum IntakeModes {
        OUT, IN, OFF
    }

    IntakeModes mode;

    public Intake() {
        mode = IntakeModes.OFF;
    }

    public void teleopInit() {
    }
    @Override
    public void teleopControl() {
        if(ControlSystems.getInstance().intakeForward.get()) {
            mode = IntakeModes.IN;
        }
        else if(ControlSystems.getInstance().intakeReverse.get()) {
            mode = IntakeModes.OUT;
        }
        else {
            mode = IntakeModes.OFF;
        }
        //Container.getInstance().intake.set(ControlSystems.getInstance().manipulatorStickSpeed());
    }
    @Override
    public void periodic() {
        if(mode == IntakeModes.IN) {
            Container.getInstance().intake.set(intakeInSpeed);
        }
        else if(mode == IntakeModes.OUT) {
            Container.getInstance().intake.set(intakeOutSpeed);
        }
        else {
            Container.getInstance().intake.set(0);
        } 
    }
}