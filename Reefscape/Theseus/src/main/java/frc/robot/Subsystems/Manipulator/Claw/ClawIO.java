package frc.robot.Subsystems.Manipulator.Claw;

import org.littletonrobotics.junction.AutoLog;

public interface ClawIO {
  @AutoLog
  public class ClawIOInputs{
    public double pinchLeftPosition = 0;
    public double pinchLeftCurrent = 0;
    public double pinchLeftTemperature = 0;

    public double pinchRightPosition = 0;
    public double pinchRightCurrent = 0;
    public double pinchRightTemperature = 0;

    public double intakeLeftVelocity = 0;
    public double intakeLeftTemperature = 0;
    public double intakeLeftCurrent = 0;

    public double intakeRightVelocity = 0;
    public double intakeRightTemperature = 0;
    public double intakeRightCurrent = 0;

    public boolean algeaCurrentSpike = false;
    public boolean coralBeamBreak = false;
  }

  public default void updateInputs(ClawIOInputs inputs){}

  public default void setPinchPosition(double position){}

  public default void setIntakeVelocity(double velocity){}

  public default void setIntakeVelocity(double leftVelocity, double rightVelocity){}

  public default void stopClaw(){}

  public default void stopIntake(){}

}
