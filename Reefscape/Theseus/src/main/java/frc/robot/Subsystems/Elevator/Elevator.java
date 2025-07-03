// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Elevator;

import java.util.function.BooleanSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Util.EqualUtil;

public class Elevator extends SubsystemBase {

  private ElevatorIO elevatorIO;
  private ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();

  /** Creates a new Elevator. */
  public Elevator(ElevatorIO elevatorIO) {
    this.elevatorIO = elevatorIO;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    elevatorIO.updateInputs(inputs);
    Logger.processInputs("/Elevator/", inputs);
  }

  public Command stopElevator(){
    return runOnce(() -> {
      elevatorIO.stop();
    });
  }

  public Command setPosition(double position){
    return runOnce(()->{
      elevatorIO.setPosition(position);
    });
  }

  public double getPosition(){
    return inputs.position;
  }

  public BooleanSupplier atSetpoint(double target){
    return ()->EqualUtil.epsilonEquals(target, getPosition());
  }
}
