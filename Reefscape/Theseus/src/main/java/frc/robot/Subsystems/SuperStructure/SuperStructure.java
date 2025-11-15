// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.SuperStructure;

import java.util.Map;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.CANdleSystem;
import frc.robot.Subsystems.CANdleSystem.AnimationTypes;
import frc.robot.Subsystems.Elevator.Elevator;
import frc.robot.Subsystems.Manipulator.Claw.Claw;
import frc.robot.Subsystems.Manipulator.Pivot.Pivot;
import frc.robot.Subsystems.SuperStructure.ScoringConstants.ClawPosition;
import frc.robot.Subsystems.SuperStructure.ScoringConstants.ClawVelocity;
import frc.robot.Subsystems.SuperStructure.ScoringConstants.ElevatorPosition;
import frc.robot.Subsystems.SuperStructure.ScoringConstants.PivotPosition;
import frc.robot.Subsystems.SuperStructure.ScoringConstants.ScoringPositions;

public class SuperStructure extends SubsystemBase {

  private final Elevator elevator;
  private final Pivot pivot;
  private final Claw claw;
  private final CANdleSystem candle;
  private final String outputKey = "SuperStructure/";

  private double elevatorTarget;
  private double pivotTarget;
  private final double debounceAlgeaTime = 0.1;
  private final double debounceCoralTime = 0.2;

  /** Creates a new SuperStructure. */
  public SuperStructure(Elevator elevator, Pivot pivot, Claw claw, CANdleSystem candle) {
    this.elevator = elevator;
    this.pivot = pivot;
    this.claw = claw;
    this.candle = candle;
  }

  @Override
  public void periodic() {
    Logger.recordOutput(outputKey+"Elevator Position", elevator.getPosition());
    Logger.recordOutput(outputKey+"Pivot Rotation", pivot.getPosition());
    Logger.recordOutput(outputKey+"Has Algea", hasAlgea());
    Logger.recordOutput(outputKey+"Has Coral", hasCoral());
    // This method will be called once per scheduler run
  }

  public Trigger hasAlgea(){
    return claw.hasAlgea().debounce(debounceAlgeaTime);
  }

  public Trigger hasCoral(){
    return claw.hasCoral().debounce(debounceCoralTime);
  }

  public Command stowAlgea(){
    return Commands.parallel(
      pivot.setPosition(PivotPosition.STRAIGHT_OUT.getPivotRotation()),
      claw.setPinchPosition(ClawPosition.CORAL_INTAKE.getPinchRotation()),
      candle.setLights(AnimationTypes.HasAlgea)
    );
  }

  public Command stowCoral(){
    return Commands.parallel(
      claw.stopIntake(),
      candle.setLights(AnimationTypes.HasCoral)
    );
  }

  public Command setPoint(ScoringPositions pose){
    return Commands.parallel(    
      elevator.setPosition(pose.getElevatorPosition()),
      pivot.setPosition(pose.getPivotPosition()),
      claw.setPinchPosition(pose.getPinchPosition())
    );
  }

  public Command setPoint(ScoringPositions pose, AnimationTypes animation){
    return Commands.parallel(    
      elevator.setPosition(pose.getElevatorPosition()),
      pivot.setPosition(pose.getPivotPosition()),
      claw.setPinchPosition(pose.getPinchPosition()),
      candle.setLights(animation)
    );
  }

  public Command PositionAndIntake(ScoringPositions pose){
    return Commands.sequence(
      Commands.parallel(
        elevator.setPosition(pose.getElevatorPosition()),
        pivot.setPosition(pose.getPivotPosition()),
        claw.setPinchPosition(pose.getPinchPosition())
      ),
      Intake()
    );
  }

  public Command PositionAndExpel(ScoringPositions pose){
    elevatorTarget = pose.getElevatorPosition();
    pivotTarget = pose.getPivotPosition();

    return Commands.sequence(
      Commands.parallel(
        elevator.setPosition(pose.getElevatorPosition()),
        pivot.setPosition(pose.getPivotPosition())
      ), 

      Commands.waitUntil(elevator.atSetpoint(elevatorTarget)),
      Commands.waitUntil(pivot.atSetPoint(pivotTarget)),

      expelCommand(pose.getElevatorEnum()),
      claw.setPinchPosition(pose.getPinchPosition())
    );
  }

  public Command PositionAndExpel(ScoringPositions pose, AnimationTypes animation){
    elevatorTarget = pose.getElevatorPosition();
    pivotTarget = pose.getPivotPosition();
    
    return Commands.sequence(
      Commands.parallel(
        elevator.setPosition(elevatorTarget),
        pivot.setPosition(pivotTarget),
        candle.setLights(animation)
      ), 
       
      Commands.waitUntil(elevator.atSetpoint(elevatorTarget)),
      Commands.waitUntil(pivot.atSetPoint(pivotTarget)),

      expelCommand(pose.getElevatorEnum()),
      claw.setPinchPosition(pose.getPinchPosition())
    );
  }

  public Command Intake(){
    return Commands.parallel(
      claw.setIntakeVelocity(ClawVelocity.INTAKE.getIntakeVelocity()),
      candle.setLights(AnimationTypes.Looking)
    );
  }

  public Command expelCommand(ElevatorPosition elevatorPose){
    return new SelectCommand<>(
      // Maps elevator state to different manipulator speeds
      Map.ofEntries(
        Map.entry( ElevatorPosition.FLOOR_ALGEA, 
          claw.setIntakeVelocity(ClawVelocity.ALGEA_PROCESSOR.getIntakeVelocity())
        ),
        Map.entry( ElevatorPosition.PROCESSOR, 
          claw.setIntakeVelocity(ClawVelocity.ALGEA_PROCESSOR.getIntakeVelocity())
        ),
        Map.entry( ElevatorPosition.L1, 
          claw.setIntakeVelocity(ClawVelocity.L1.getIntakeVelocity())
        ),
        Map.entry( ElevatorPosition.L2, 
          claw.setIntakeVelocity(ClawVelocity.CORAL.getIntakeVelocity())
        ),
        Map.entry( ElevatorPosition.L3, 
          claw.setIntakeVelocity(ClawVelocity.CORAL.getIntakeVelocity())
        ),
        Map.entry( ElevatorPosition.L4, 
          claw.setIntakeVelocity(ClawVelocity.CORAL.getIntakeVelocity())
        ),
        Map.entry( ElevatorPosition.BARGE, 
          claw.setIntakeVelocity(
            ClawVelocity.ALGEA_BARGE.getIntakeVelocity(), 
            ClawVelocity.ALGEA_BARGE.getRightVelocity()
          )
        )
      ),
      elevatorPose::getElevatorHeight);
  }

}