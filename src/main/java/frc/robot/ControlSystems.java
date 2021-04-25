package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ControlSystems {

    Joystick rightstick = new Joystick(0);
    Joystick leftstick = new Joystick(1);
    Joystick manipulatorStick = new Joystick(2);

    public double rightSpeed = rightstick.getY();
    public double leftSpeed = leftstick.getY();
    public double manipulatorStickSpeed = manipulatorStick.getY();
    
}
