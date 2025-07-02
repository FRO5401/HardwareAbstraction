// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Manipulator.Claw;

import java.util.function.BooleanSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Claw extends SubsystemBase {

  private ClawIO clawIO;
  private ClawIOInputsAutoLogged inputs = new ClawIOInputsAutoLogged();

  /** Creates a new Claw. */
  public Claw(ClawIO clawIO) {
    this.clawIO = clawIO;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    clawIO.updateInputs(inputs);
    Logger.processInputs("/Claw/", inputs);
  }

  public Command setPinchPosition(double position){
    return runOnce(()->{
      clawIO.setPinchPosition(position);
    });
  }

  public Command setIntakeVelocity(double velocity){
    return runOnce(()->{
      clawIO.setIntakeVelocity(velocity);
    });
  }

  public Command setIntakeVelocity(double leftVelocity, double rightVelocity){
    return runOnce(()->{
      clawIO.setIntakeVelocity(leftVelocity, rightVelocity);
    });
  }

  public Command stopClaw(){
    return runOnce(()->{
      clawIO.stopClaw();
    });
  }

  public Trigger hasAlgea(){
    return new Trigger(()->inputs.algeaCurrentSpike);
  }

  public Trigger hasCoral(){
    return new Trigger(()->inputs.coralBeamBreak);
  }
}
