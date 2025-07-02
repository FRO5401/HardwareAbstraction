package frc.robot.Subsystems.Manipulator.Claw;

import java.util.function.BooleanSupplier;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Subsystems.Manipulator.Claw.ClawConstants.Config;

public class ClawIOSpark implements ClawIO {
  private final SparkMax pinchLeft = new SparkMax(ClawConstants.PINCH_LEFT_ID, MotorType.kBrushless);
  private final SparkMax pinchRight = new SparkMax(ClawConstants.PINCH_RIGHT_ID, MotorType.kBrushless);
  private final SparkMax intakeLeft = new SparkMax(ClawConstants.INTAKE_LEFT_ID, MotorType.kBrushless);
  private final SparkMax intakeRight = new SparkMax(ClawConstants.INTAKE_RIGHT_ID, MotorType.kBrushless);

  private final SparkMaxConfig pinchLeftConfig = Config.PINCH_LEFT_CONFIG;
  private final SparkMaxConfig pinchRightConfig = Config.PINCH_RIGHT_CONFIG;
  private final SparkMaxConfig intakeLeftConfig = Config.INTAKE_LEFT_CONFIG;
  private final SparkMaxConfig intakeRightConfig = Config.INTAKE_RIGHT_CONFIG;

  private final RelativeEncoder pinchLeftEncoder = pinchLeft.getEncoder();
  private final RelativeEncoder pinchRightEncoder = pinchRight.getEncoder();

  private final SparkClosedLoopController leftPIDcontroller = pinchLeft.getClosedLoopController();
  private final SparkClosedLoopController rightPIDcontroller = pinchRight.getClosedLoopController();

  private final Debouncer currentFilter = new Debouncer(ClawConstants.DEBOUNCE_TIME, DebounceType.kBoth);
  private final DigitalInput beamBreak = new DigitalInput(ClawConstants.BEAM_BREAK_ID);

  public ClawIOSpark(){
    pinchLeft.configure(pinchLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    pinchRight.configure(pinchRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    intakeLeft.configure(intakeLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    intakeRight.configure(intakeRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    pinchLeftEncoder.setPosition(0);
    pinchRightEncoder.setPosition(0);
  }

  @Override
  public void updateInputs(ClawIOInputs inputs){
    inputs.intakeLeftCurrent = intakeLeft.getOutputCurrent();
    inputs.intakeLeftVelocity = intakeLeft.getEncoder().getVelocity();
    inputs.intakeLeftTemperature = intakeLeft.getMotorTemperature();

    inputs.intakeRightCurrent = intakeRight.getOutputCurrent();
    inputs.intakeRightVelocity = intakeRight.getEncoder().getVelocity();
    inputs.intakeRightTemperature = intakeRight.getMotorTemperature();

    inputs.pinchLeftCurrent = pinchLeft.getOutputCurrent();
    inputs.pinchLeftPosition = pinchLeftEncoder.getPosition();
    inputs.pinchLeftTemperature = pinchLeft.getMotorTemperature();

    inputs.pinchRightCurrent = pinchRight.getOutputCurrent();
    inputs.pinchRightPosition = pinchRightEncoder.getPosition();
    inputs.pinchRightTemperature = pinchRight.getMotorTemperature();

    inputs.coralBeamBreak = getBeamBreak();
    inputs.algeaCurrentSpike = isCurrentSpiked().getAsBoolean();
  }

  @Override
  public void setPinchPosition(double position){
    leftPIDcontroller.setReference(position, ControlType.kPosition);
  }

  @Override 
  public void setIntakeVelocity(double velocity){
    intakeLeft.set(velocity);
    intakeRight.set(velocity);
  }

  @Override
  public void setIntakeVelocity(double leftVelocity, double rightVelocity){
    intakeLeft.set(leftVelocity);
    intakeRight.set(rightVelocity);
  }

  @Override
  public void stopClaw(){
    pinchLeft.stopMotor();
    pinchRight.stopMotor();

    intakeLeft.stopMotor();
    intakeRight.stopMotor();
  }

  // finds average of both intake motors
  public double intakeCurrent(){
    return 
      (intakeLeft.getOutputCurrent() + intakeRight.getOutputCurrent()) / 2;
  }

  public boolean getBeamBreak(){
    return beamBreak.get();
  }

  public BooleanSupplier isBeamBroken(){
    return ()->getBeamBreak();
  }

  public BooleanSupplier isCurrentSpiked(){
    return ()->currentFilter.calculate(intakeCurrent() > ClawConstants.CURRENT_SPIKED);
  }
}
