package frc.robot.Subsystems.Manipulator.Pivot;

import org.littletonrobotics.junction.AutoLog;

public interface PivotIO {
  @AutoLog
  public class PivotIOInputs{
    public double position = 0;
    public double velocity = 0;
    public double current = 0;
    public double voltage = 0;
    public double temperature = 0;
  }
  public default void updateInputs(PivotIOInputs inputs){}

  public default void setPosition(double position){}

  public default void stop(){}
}
