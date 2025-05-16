// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

/*  Imports */
import org.littletonrobotics.junction.AutoLog;

public interface DrivetrainIO {

  @AutoLog
  public static class DrivetrainIOInputs {
    //  Voltage to Motors
    public double leftAppliedVolts = 0.0;
    public double rightAppliedVolts = 0.0;

    //  Amperage of Motors
    public double[] leftCurrentAmps = new double[] {};
    public double[] rightCurrentAmps = new double[] {};

    //  Position in Radians
    public double leftPositionRad = 0.0;
    public double rightPositionRad = 0.0;

    //  Speed/Velocity in Radians Per Second
    public double leftVelocityRadPerSec = 0.0;
    public double rightVelocityRadPerSec = 0.0;

  }
  /* */
  public default void updateInputs(DrivetrainIOInputs inputs){};

  /* */
  public default void setVoltage(double left, double right){};

  /* */
  public default void setVelocity(double left, double right){};
  
}
