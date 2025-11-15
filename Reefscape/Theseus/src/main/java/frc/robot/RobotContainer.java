// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Subsystems.CANdleSystem;
import frc.robot.Subsystems.Elevator.Elevator;
import frc.robot.Subsystems.Elevator.ElevatorIO;
import frc.robot.Subsystems.Elevator.ElevatorIOTalonFX;
import frc.robot.Subsystems.Manipulator.Claw.Claw;
import frc.robot.Subsystems.Manipulator.Claw.ClawIO;
import frc.robot.Subsystems.Manipulator.Claw.ClawIOSpark;
import frc.robot.Subsystems.Manipulator.Pivot.Pivot;
import frc.robot.Subsystems.Manipulator.Pivot.PivotIO;
import frc.robot.Subsystems.Manipulator.Pivot.PivotIOSpark;
import frc.robot.Subsystems.SuperStructure.SuperStructure;
import frc.robot.Util.RobotMode;

public class RobotContainer {
  private final Elevator elevator;
  private final Pivot pivot;
  private final Claw claw;
  private final SuperStructure superStructure;
  private final CANdleSystem candle;

  private final Trigger hasAlgea;
  private final Trigger hasCoral;

  public RobotContainer() {
    candle = new CANdleSystem();

    switch(RobotMode.currentMode){
      case REAL -> {
        
        elevator = new Elevator(new ElevatorIOTalonFX());
        pivot = new Pivot(new PivotIOSpark());
        claw = new Claw(new ClawIOSpark());
        
      }
      //case SIM -> {

      //}
      default -> {

        elevator = new Elevator(new ElevatorIO() {});
        pivot = new Pivot(new PivotIO() {});
        claw = new Claw(new ClawIO(){});

      }

    }

    superStructure = new SuperStructure(elevator, pivot, claw, candle);

    hasAlgea = superStructure.hasAlgea();
    hasCoral = superStructure.hasCoral();

    configureBindings();
  }

  private void configureBindings() {

    hasAlgea.onTrue(superStructure.stowAlgea());

    hasCoral.onTrue(superStructure.stowCoral());
    
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
