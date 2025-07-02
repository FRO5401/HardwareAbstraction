package frc.robot.Subsystems.Manipulator.Claw;

public class ClawConstants {
  public static final int INTAKE_MOTOR_LEFT = 20;
  public static final int INTAKE_MOTOR_RIGHT=18;
  public static final int ROTATE_MOTOR_LEFT=17; 
  public static final int ROTATE_MOTOR_RIGHT=15;

  public static final double ROTATE_KP = .1;
  public static final double ROTATE_kI = 0;
  public static final double ROTATE_kD = 0;
  public static final double ROTATE_KF = 10;

  public static final double HOLD_CORAL = 32;
  public static final double HOLD_ALGEA = 6;
  public static final double TELEOP_REPEL_ALGEA = -0.58; //-0.7
  public static final double TELEOP_REPEL_CORAL = -.9; //-0.7
  public static final double AUTO_REPEL_ALGEA = -.84;
  public static final double INTAKE_SPEED = 1;
}
