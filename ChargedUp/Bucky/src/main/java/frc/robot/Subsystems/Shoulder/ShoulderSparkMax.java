package frc.robot.Subsystems.Shoulder;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ShoulderSparkMax implements ShoulderIO {

    private final SparkMax master = new SparkMax(ShoulderConstants.MASTER_ID, MotorType.kBrushless);
    private final SparkMax follower = new SparkMax(ShoulderConstants.FOLLOWER_ID, MotorType.kBrushless);
    
    
}
