package frc.robot.Subsystems.Manipulator.Claw;

import org.littletonrobotics.junction.AutoLog;

public interface ClawIO {
  @AutoLog
  public class ClawIOInputs{
    public double pinchMasterPosition = 0;
    public double pinchMasterCurrent = 0;
    public double pinchMasterTemperature = 0;

    public double pinchFollowPosition = 0;
    public double pinchFollowCurrent = 0;
    public double pinchFollowTemperature = 0;

    public double intakeRightVelocity = 0;
    public double intakeRightTemperature = 0;
    public double intakeRightCurrent = 0;

    public double intakeLeftVelocity = 0;
    public double intakeLeftTemperature = 0;
    public double intakeLeftCurrent = 0;

    public boolean isCurrentSpiked = false;
  }

  public default void updateInputs(ClawIOInputs inputs){}

  public default void setPinchPosition(double position){}

  public default void intakeVelocity(double velocity){}

  public default void intakeVelocity(double rightVelocity, double leftVelocity){}

  public default void stopClaw(){}

  public default void isCurrentSpiked(){}
}
