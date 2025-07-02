package frc.robot.Subsystems.Manipulator.Claw;

import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class ClawConstants {
  public static final int BEAM_BREAK_ID = 0;
  public static final double DEBOUNCE_TIME = 0.15;

  public static final int INTAKE_LEFT_ID = 20;
  public static final int INTAKE_RIGHT_ID = 18;
  public static final int PINCH_LEFT_ID = 17; 
  public static final int PINCH_RIGHT_ID = 15;

  public static final double PINCH_kP = .1;
  public static final double PINCH_kI = 0;
  public static final double PINCH_kD = 0;
  public static final double PINCH_KF = 10;

  public static final double HOLD_CORAL = 32;
  public static final double HOLD_ALGEA = 6;
  public static final double TELEOP_REPEL_ALGEA = -0.58; //-0.7
  public static final double TELEOP_REPEL_CORAL = -.9; //-0.7
  public static final double AUTO_REPEL_ALGEA = -.84;
  public static final double INTAKE_SPEED = 1;

  public static final double PINCH_GEAR_RATIO = 16;
  public static final double INTAKE_GEAR_RATIO = 1;
  public static final int PINCH_STALL_LIMIT = 25;
  public static final int INTAKE_STALL_LIMIT = 40;

  public static final double CURRENT_SPIKED = 21;

  public class Config {
    public static final EncoderConfig PINCH_ENCODER_CONFIG = new EncoderConfig()
      .positionConversionFactor(PINCH_GEAR_RATIO)
      .velocityConversionFactor(PINCH_GEAR_RATIO);

    public static final EncoderConfig INTAKE_ENCODER_CONFIG = new EncoderConfig()
      .positionConversionFactor(INTAKE_GEAR_RATIO)
      .velocityConversionFactor(INTAKE_GEAR_RATIO);

    public static final ClosedLoopConfig PINCH_CLOSED_LOOP_CONFIG = new ClosedLoopConfig()
      .p(PINCH_kP)
      .i(PINCH_kI)
      .d(PINCH_kD)
      .velocityFF(PINCH_KF)
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder);
    
    public static final SparkMaxConfig INTAKE_LEFT_CONFIG = new SparkMaxConfig();
    public static final SparkMaxConfig INTAKE_RIGHT_CONFIG = new SparkMaxConfig();
    public static final SparkMaxConfig PINCH_LEFT_CONFIG = new SparkMaxConfig();
    public static final SparkMaxConfig PINCH_RIGHT_CONFIG = new SparkMaxConfig();
    
    public Config(){
      PINCH_LEFT_CONFIG
        .apply(PINCH_CLOSED_LOOP_CONFIG)
        .apply(PINCH_ENCODER_CONFIG)
        .smartCurrentLimit(PINCH_STALL_LIMIT)
        .idleMode(IdleMode.kBrake)
        .inverted(false)
        .disableFollowerMode();
      
      PINCH_RIGHT_CONFIG
        .apply(PINCH_LEFT_CONFIG)
        .follow(PINCH_LEFT_ID)
        .inverted(true);
      
      INTAKE_LEFT_CONFIG
        .apply(INTAKE_ENCODER_CONFIG)
        .smartCurrentLimit(INTAKE_STALL_LIMIT)
        .idleMode(IdleMode.kBrake)
        .inverted(false)
        .disableFollowerMode();
      
      INTAKE_RIGHT_CONFIG
        .apply(INTAKE_LEFT_CONFIG)
        .inverted(true);

    }
  }
}
