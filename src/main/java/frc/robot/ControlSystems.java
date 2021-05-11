package frc.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ControlSystems {
    
    private static ControlSystems thetrueControlSystem;
    Joystick rightstick;
    Joystick leftstick;
    Joystick manipulatorStick;
    XboxController manipulatorController;

    public ControlSystems() {

        rightstick = new Joystick(0);
        leftstick = new Joystick(1);
        manipulatorStick = new Joystick(2);
    }
    public double rightSpeed() {
        return rightstick.getY();
    } 
    public double leftSpeed() {
        return leftstick.getY();
    }
    public double manipulatorStickSpeed() {
        return manipulatorStick.getY();
    }
    public double manipulatorStickTwist() {
        return manipulatorStick.getTwist();
    }
    public Boolean manipulatorStickTrigger() {
        return manipulatorStick.getTrigger();
    }
    public double gamepadLeftY() {
        return manipulatorController.getRawAxis(RobotMap.MANIPULATOR_LEFT_JOYSTICK_Y);
    }

    public double gamepadRightY() {
        return manipulatorController.getRawAxis(RobotMap.MANIPULATOR_RIGHT_JOYSTICK_Y);
    }

    public double gamepadLeftTrigger() {
        return manipulatorController.getRawAxis(RobotMap.MANIPULATOR_LEFT_TRIGGER);
    }

    public double gamepadRightTrigger() {
        return manipulatorController.getRawAxis(RobotMap.MANIPULATOR_RIGHT_TRIGGER);
    }

    public static ControlSystems getInstance() {
        if (thetrueControlSystem != null) {
            return thetrueControlSystem;
        }                   
        else {
            thetrueControlSystem = new ControlSystems();
            return thetrueControlSystem;
        }
    }

}
