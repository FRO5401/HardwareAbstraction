package frc.robot.Subsystems.Elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {

  @AutoLog
  public class ElevatorIOInputs{

    public double position = 0;
    public double velocity = 0;
    public double temperature = 0;
    public double voltage;
    public double current = 0;
  }

  public default void updateInputs(ElevatorIOInputs inputs){};
  
  public default void setPosition(double position){}

  public default void stop(){}

  public default void setVoltage(double voltage){}
}
