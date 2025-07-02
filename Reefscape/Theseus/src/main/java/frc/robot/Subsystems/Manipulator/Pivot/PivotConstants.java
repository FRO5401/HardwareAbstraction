package frc.robot.Subsystems.Manipulator.Pivot;

import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class PivotConstants {
  public static final int PIVOT_ID=14;
  public static final double GEAR_RATIO = 13.8;
  public static final int STALL_LIMIT = 50;

  public static final double KP=1;
  public static final double KI=0;
  public static final double KD=0;

  public class Config{
    public static final ClosedLoopConfig CLOSED_LOOP_CONFIG = new ClosedLoopConfig()
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      .pid(KP, KI, KD);

    public static final EncoderConfig ENCODER_CONFIG = new EncoderConfig()
      .inverted(true)
      .positionConversionFactor(GEAR_RATIO)
      .velocityConversionFactor(GEAR_RATIO);

    public static final SparkMaxConfig PIVOT_CONFIG = new SparkMaxConfig();

    public Config(){
      PIVOT_CONFIG
        .apply(CLOSED_LOOP_CONFIG)
        .apply(ENCODER_CONFIG)
        .smartCurrentLimit(STALL_LIMIT)
        .idleMode(IdleMode.kBrake)
        .disableFollowerMode();
    }
    
  }
}
