package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.wpilibj.Joystick;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ControlSystems {
    
    private static ControlSystems thetrueControlSystem;
    Joystick rightstick;
    Joystick leftstick;
    Joystick manipulatorStick;

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
