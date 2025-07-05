package frc.robot.Subsystems.Manipulator.Pivot;

import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Constants.PIDformat;
import frc.robot.Util.RobotMode;

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class PivotConstants {
  public static final int PIVOT_ID=14;
  public static final double GEAR_RATIO = 13.8;
  public static final int STALL_LIMIT = 50;

public static final PIDformat PID_VALUES = 
  switch(RobotMode.currentMode){
      case SIM -> new PIDformat(
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
      );
      default -> new PIDformat(
        1, // An error of 1 rotation results in 2.4 V output
        0, // no output for integrated error
        0, // A velocity of 1 rps results in 0.1 V output
        0, 
        0, 
        0, 
        0, 
        0
      );
    };

  public class Config{
    public static final ClosedLoopConfig CLOSED_LOOP_CONFIG = new ClosedLoopConfig()
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      .p(PID_VALUES.kP())
      .i(PID_VALUES.kI())
      .d(PID_VALUES.kD());

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
