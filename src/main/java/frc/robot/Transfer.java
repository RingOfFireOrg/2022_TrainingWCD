package frc.robot;

public class Transfer extends TeleopModule{
    
    private double transferInSpeed = 1;
    private double transferOutSpeed = -1;
    public enum TransferModes {
        OUT, IN, OFF
    }

    TransferModes tMode;

    public Transfer() {
        tMode = TransferModes.OFF;
    }

    public void teleopInit() {
    }
    @Override
    public void teleopControl() {
        if(ControlSystems.getInstance().transferIn.get()) {
            //tMode = TransferModes.IN;
            Container.getInstance().transfer.set(transferInSpeed);
        }
        else if(ControlSystems.getInstance().transferOut.get()) {
            //tMode = TransferModes.OUT;
            Container.getInstance().transfer.set(transferOutSpeed);
        }
        else {
            //tMode = TransferModes.OFF;
            Container.getInstance().transfer.set(0);
        }
        //Container.getInstance().intake.set(ControlSystems.getInstance().manipulatorStickSpeed());
        Container.getInstance().transfer2.set(ControlSystems.getInstance().mGamepadRightY());
    }
    @Override
    public void periodic() {
        if(tMode == TransferModes.IN) {
            //Container.getInstance().transfer.set(transferInSpeed);
        }
        else if(tMode == TransferModes.OUT) {
            //Container.getInstance().transfer.set(transferOutSpeed);
        }
        else if(tMode == TransferModes.OFF) {
            //Container.getInstance().transfer.set(0);
        } 
    }
}
