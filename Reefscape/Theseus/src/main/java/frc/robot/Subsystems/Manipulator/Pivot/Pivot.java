// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Manipulator.Pivot;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pivot extends SubsystemBase {

  private PivotIO pivotIO;
  private PivotIOInputsAutoLogged inputs = new PivotIOInputsAutoLogged();

  /** Creates a new Pivot. */
  public Pivot(PivotIO pivotIO) {
    this.pivotIO = pivotIO;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    pivotIO.updateInputs(inputs);
    Logger.processInputs("/Pivot/", inputs);
  }

  public Command setPosition(double position){
    return runOnce(()->{
      pivotIO.setPosition(position);
    });
  }

  public Command stopPivot(){
    return runOnce(()->{
      pivotIO.stop();
    });
  }

  public double getPosition(){
    return inputs.position;
  }
}
