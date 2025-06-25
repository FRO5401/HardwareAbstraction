package frc.robot.Subsystems.Elevator;

public interface ElevatorIO {

  public default void updateInputs(ElevatorIOInputs inputs){};

  public class ElevatorIOInputs{
    public double position = 0;
    public double velocity = 0;
    public double temperature = 0;
    public double voltage;
    public double current = 0;
  }
  
  public default void setPosition(double position){}

  public default void stop(){}

  public default void setVoltage(double voltage){}
}
