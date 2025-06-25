package frc.robot.Subsystems.Elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Subsystems.Elevator.ElevatorConstants.Config;

public class ElevatorIOTalonFX implements ElevatorIO{
  private final TalonFX elevatorMotor = new TalonFX(ElevatorConstants.elevatorID);
  private final TalonFXConfiguration config = Config.ELEVATOR_CONFIG;
  private final PositionVoltage positionPID = new PositionVoltage(0).withSlot(0);

  public ElevatorIOTalonFX(){
    elevatorMotor.setPosition(0);
    elevatorMotor.getConfigurator().apply(config);
  }

  public void updateInputs(ElevatorIOInputs inputs){
    inputs.position = elevatorMotor.getPosition().refresh().getValueAsDouble();
    inputs.voltage = elevatorMotor.getMotorVoltage().refresh().getValueAsDouble();
    inputs.velocity = elevatorMotor.getVelocity().refresh().getValueAsDouble();
    inputs.current = elevatorMotor.getStatorCurrent().refresh().getValueAsDouble();
    inputs.temperature = elevatorMotor.getDeviceTemp().refresh().getValueAsDouble();
  }

  public void setVoltage(double voltage){
    elevatorMotor.setVoltage(voltage);
  }

  public void setPosition(double position){
    if(position > ElevatorConstants.MAX_EXTENSION){
      position = ElevatorConstants.MAX_EXTENSION;
    } else if(position>ElevatorConstants.MIN_EXTENSION){
      position = ElevatorConstants.MIN_EXTENSION;
    }
    elevatorMotor.setControl(positionPID.withPosition(position));
  }

  public void stop(){
    elevatorMotor.set(0);
  }
}
