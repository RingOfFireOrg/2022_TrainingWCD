package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
    
    private Autonomous autonomous;

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
        /*SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("LimelightTarget", v);*/

        double[] arr = {x, y, area, v};
        return arr;

    }

    public void aimToTarget() {
        double[] visionVals = updateVisionVals();

        if (visionVals[0] > -3 && visionVals[0] < 3){
            if(visionVals[3] == 1){
                autonomous.moveStop();
                
            }
        }else if(visionVals[0] < -3){
            autonomous.turnLeft();
        }else{
            autonomous.turnRight();
        }

    }
}