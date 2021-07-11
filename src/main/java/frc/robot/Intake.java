package frc.robot;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class Intake extends TeleopModule{
    
    private double intakeInSpeed = -1;
    private double intakeOutSpeed = 1;
    public enum IntakeModes {
        OUT, IN, OFF
    }

    IntakeModes iMode;

    public Intake() {
        iMode = IntakeModes.OFF;
    }

    public void teleopInit() {
    }
    @Override
    public void teleopControl() {
        if(ControlSystems.getInstance().intakeIn.get()) {
            //iMode = IntakeModes.IN;
            Container.getInstance().intake.set(intakeInSpeed);
        }
        else if(ControlSystems.getInstance().intakeOut.get()) {
            //iMode = IntakeModes.OUT;
            Container.getInstance().intake.set(intakeOutSpeed);
        }
        else {
            //iMode = IntakeModes.OFF;
            Container.getInstance().intake.set(0);
        }
        //Container.getInstance().intake.set(ControlSystems.getInstance().manipulatorStickSpeed());
    }
    @Override
    public void periodic() {
        if(iMode == IntakeModes.IN) {
            //Container.getInstance().intake.set(intakeInSpeed);
        }
        else if(iMode == IntakeModes.OUT) {
            //Container.getInstance().intake.set(intakeOutSpeed);
        }
        else if(iMode == IntakeModes.OFF) {
            //Container.getInstance().intake.set(0);
        } 
    }
}