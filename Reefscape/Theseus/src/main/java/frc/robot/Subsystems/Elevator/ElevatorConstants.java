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

import edu.wpi.first.units.measure.Current;

public class ElevatorConstants {

  public static final int elevatorID = 13;

  public static final double SPEED_MODIFIER = 166;
  public static final double BARGE = 145;
  public static final double L4 = 133;
  public static final double L3 = 77;    
  public static final double L2 = 48;
  public static final double STATION = 45;
  public static final double PROCESSOR = 5;
  public static final double FLOOR = 1;

  public static final double KP = 4; // An error of 1 rotation results in 2.4 V output
  public static final double KI = 3; // no output for integrated error
  public static final double KD = .4; // A velocity of 1 rps results in 0.1 V output
  public static final double KA= .1;
  public static final double KV= .1;
  public static final double KG= 2.4;

  public static final double MAX_EXTENSION = 160;
  public static final double MIN_EXTENSION = -1;
  public static final double GEAR_RATIO = 25; 
  public static final Current STATOR_LIMIT = Amps.of(120);
  public static final Current SUPPLY_LIMIT = Amps.of(80);

  public class Config{
    public static final Slot0Configs CLOSED_LOOP_CONFIG = new Slot0Configs()
      .withKP(KP)
      .withKI(KI)
      .withKD(KD)
      .withKA(KA)
      .withKP(KP)
      .withKV(KV)
      .withKG(KG)
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
