
package frc.robot;

public class Constants {
  public static final int CANdleID = 19;

  /**
   * @param kp
   * @param kI
   * @param kD
   * @param kF
   * @param kA
   * @param kV
   * @param kG
   * @param iZone
   */
  public record PIDformat (
    double kP,
    double kI,
    double kD,
    double kF,
    double kA,
    double kV,
    double kG,
    double iZone
  ) {}
}
