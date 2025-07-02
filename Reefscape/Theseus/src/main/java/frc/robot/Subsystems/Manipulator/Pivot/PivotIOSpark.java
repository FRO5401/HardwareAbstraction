package frc.robot.Subsystems.Manipulator.Pivot;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Subsystems.Manipulator.Pivot.PivotConstants.Config;

public class PivotIOSpark implements PivotIO {
  private final SparkMax pivot = new SparkMax(PivotConstants.PIVOT_ID, MotorType.kBrushless);
  private final SparkMaxConfig config = Config.PIVOT_CONFIG;
  private final RelativeEncoder encoder = pivot.getEncoder();
  private final SparkClosedLoopController PIDcontroller = pivot.getClosedLoopController();

  public PivotIOSpark(){
    pivot.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    encoder.setPosition(0);
  }

  @Override
  public void updateInputs(PivotIOInputs inputs){
    inputs.position = encoder.getPosition();
    inputs.velocity = encoder.getVelocity();
    inputs.temperature = pivot.getMotorTemperature();
    inputs.voltage = pivot.getBusVoltage() * pivot.getAppliedOutput();
    inputs.current = pivot.getOutputCurrent();
  }

  @Override
  public void setPosition(double position){
    PIDcontroller.setReference(position, ControlType.kPosition);
  }

  @Override 
  public void stop(){
    pivot.stopMotor();
  }
}
