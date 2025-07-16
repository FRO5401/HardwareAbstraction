package frc.robot.Subsystems.Elevator;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Current;
import frc.robot.Constants.PIDformat;
import frc.robot.Util.RobotMode;

public class ElevatorConstants {

  public static final int ELEVATOR_ID = 13;

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
        4, // An error of 1 rotation results in 2.4 V output
        3, // no output for integrated error
        0.4, // A velocity of 1 rps results in 0.1 V output
        0, 
        0.1, 
        0.1, 
        2.4, 
        0
      );
    };

  public static final double SPEED_MODIFIER = 166;
  public static final double MAX_EXTENSION = 160;
  public static final double MIN_EXTENSION = -1;
  public static final double GEAR_RATIO = 25; 

  public static final double CHAIN_LENGTH_DRIVE_METERS = Units.inchesToMeters(0.35);
  public static final double CHAIN_LENGTH_SUPPORT = Units.inchesToMeters(0.25);
  public static final double CHAIN_SPROCKET_DRIVE = 12;
  public static final double CHAIN_SPROCKET_SUPPORT = 22;

  public static final Current STATOR_LIMIT = Amps.of(120);
  public static final Current SUPPLY_LIMIT = Amps.of(80);

  public class Config{
    public static final Slot0Configs CLOSED_LOOP_CONFIG = new Slot0Configs()
      .withKP(PID_VALUES.kP())
      .withKI(PID_VALUES.kI())
      .withKD(PID_VALUES.kD())
      .withKA(PID_VALUES.kA())
      .withKV(PID_VALUES.kV())
      .withKG(PID_VALUES.kG())
      .withGravityType(GravityTypeValue.Elevator_Static);

    public static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIG = new CurrentLimitsConfigs()
      .withStatorCurrentLimit(STATOR_LIMIT)
      .withSupplyCurrentLimit(SUPPLY_LIMIT);

    public static final SoftwareLimitSwitchConfigs SOFT_LIMIT_CONFIG = new SoftwareLimitSwitchConfigs()
      .withForwardSoftLimitEnable(true)
      .withForwardSoftLimitThreshold(MAX_EXTENSION)
      .withReverseSoftLimitEnable(true)
      .withReverseSoftLimitThreshold(MIN_EXTENSION);

    public static final MotorOutputConfigs OUTPUT_CONFIG = new MotorOutputConfigs()
      .withInverted(InvertedValue.CounterClockwise_Positive)
      .withNeutralMode(NeutralModeValue.Brake);

    public static final FeedbackConfigs FEEDBACK_CONFIG = new FeedbackConfigs()
      .withSensorToMechanismRatio(GEAR_RATIO);

    public static final TalonFXConfiguration ELEVATOR_CONFIG = new TalonFXConfiguration()
      .withSlot0(CLOSED_LOOP_CONFIG)
      .withCurrentLimits(CURRENT_LIMITS_CONFIG)
      .withSoftwareLimitSwitch(SOFT_LIMIT_CONFIG)
      .withMotorOutput(OUTPUT_CONFIG)
      .withFeedback(FEEDBACK_CONFIG);

  }
}
