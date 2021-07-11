package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class Vision extends TeleopModule {

    private final int VISIONRANGE = 2;

    private SpeedControllerGroup leftMotors, rightMotors;

    public void teleopInit() {
        rightMotors = new SpeedControllerGroup(Container.getInstance().frontRightMotor, Container.getInstance().backRightMotor);
        leftMotors = new SpeedControllerGroup(Container.getInstance().frontLeftMotor, Container.getInstance().backLeftMotor);
    }

    public void teleopControl() {
        if (ControlSystems.getInstance().aimButton.get()) {
            aimToTarget();
        }
    }


    public void turnLeft(){
        leftMotors.set(0.075);
        rightMotors.set(-0.092);
    }
    public void turnRight(){
        leftMotors.set(-0.075);
        rightMotors.set(0.092);
    }
    public void moveStop(){
        leftMotors.set(0);
        rightMotors.set(0);
    }

    public double[] updateVisionVals() {

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");

        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double v = tv.getDouble(0.0);
        
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("LimelightTarget", v);

        double[] arr = {x, y, area, v};
        return arr;

    }

    public boolean aimToTarget() {
        
        double[] visionVals = updateVisionVals();
        //nice
            if (visionVals[0] < -VISIONRANGE) {
                turnLeft();
            } else if ( visionVals[0] > VISIONRANGE) {
                turnRight();
            }else {
                if(visionVals[3] == 1){
                    moveStop();
                    return true;
                }
            } 
                return false;
        }
        public void periodic() {
        }
}