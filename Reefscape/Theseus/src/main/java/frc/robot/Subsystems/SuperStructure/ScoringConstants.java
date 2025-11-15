package frc.robot.Subsystems.SuperStructure;

import java.util.OptionalDouble;

public class ScoringConstants {
  //TODO Update all values for elevator
  public static enum ElevatorPosition{
    FLOOR_ALGEA(1),
    PROCESSOR(5),
    L1(20), //TODO Test this Value
    L2(48),
    L3(77),
    L4(133),
    ALGEA_L2(42),
    ALGEA_L3(70),
    STATION(45),
    BARGE(145);

    private double elevatorHeight;

    private ElevatorPosition(double elevatorHeight){
      this.elevatorHeight = elevatorHeight;
    }

    public double getElevatorHeight(){
      return elevatorHeight;
    }
  }

  public static enum ClawPosition{
    CORAL_INTAKE(32),
    CORAL_EXPEL(10),
    ALGEA(6);

    private double pinchRotation;

    private ClawPosition(double pinchRotation){
      this.pinchRotation = pinchRotation;
    }

    public double getPinchRotation(){
      return pinchRotation;
    }
  }

  public static enum PivotPosition{
    FLOOR(-100),
    PROCESSOR(-53),
    STRAIGHT_OUT(-62),
    L1(0), //TODO Find this value
    PLACE_CORAL(-125),
    REEF_ALGEA(-81),
    L4(-164),
    STATION(-50),
    BARGE(-16);

    private double pivotRotation;

    private PivotPosition(double pivotRotation){
      this.pivotRotation = pivotRotation;
    }

    public double getPivotRotation(){
      return pivotRotation;
    }
  }

  public static enum ClawVelocity{
    INTAKE(1),
    L1(0),//TODO Find this value
    CORAL(-0.9),
    ALGEA_PROCESSOR(-0.2),
    ALGEA_BARGE(-0.58, OptionalDouble.of(0)),
    DISPENSE(-1),
    ALGEA_AUTO(-0.84);

    private double intakeVelocity;
    private OptionalDouble rightVelocity;

    private ClawVelocity(double intakeVelocity, OptionalDouble rightVelocity){
      this.intakeVelocity = intakeVelocity;
      this.rightVelocity = rightVelocity;
    }
    private ClawVelocity(double intakeVelocity){
      this.intakeVelocity = intakeVelocity;
    }

    public double getIntakeVelocity(){
      return intakeVelocity;
    }

    public double getRightVelocity(){
      return rightVelocity.isPresent() ? rightVelocity.getAsDouble() : intakeVelocity; 
    }
  }
   /**
    * @param Elevator_Position The Elevator height
    * @param Pivot_Position The Pivot rotation
    * @param Claw_Position The Pinch rotation
    * @param Dispense_Speed The Claw dispense speed
    */
  
  public static enum ScoringPositions{
    FLOOR_ALGEA(ElevatorPosition.FLOOR_ALGEA, PivotPosition.FLOOR, ClawPosition.ALGEA),
    PROCESSOR(ElevatorPosition.PROCESSOR, PivotPosition.PROCESSOR, ClawPosition.ALGEA),
    L1(ElevatorPosition.L1, PivotPosition.L1, ClawPosition.CORAL_EXPEL),
    L2(ElevatorPosition.L2, PivotPosition.PLACE_CORAL, ClawPosition.CORAL_EXPEL),
    L3(ElevatorPosition.L3, PivotPosition.PLACE_CORAL, ClawPosition.CORAL_EXPEL),
    L4(ElevatorPosition.L4, PivotPosition.PLACE_CORAL, ClawPosition.CORAL_EXPEL),
    L2_ALGEA(ElevatorPosition.ALGEA_L2, PivotPosition.REEF_ALGEA, ClawPosition.ALGEA),
    L3_ALGEA(ElevatorPosition.ALGEA_L3, PivotPosition.REEF_ALGEA, ClawPosition.ALGEA),
    STATION(ElevatorPosition.STATION, PivotPosition.STATION, ClawPosition.CORAL_INTAKE),
    BARGE(ElevatorPosition.BARGE, PivotPosition.BARGE, ClawPosition.ALGEA),
    AUTO_ALGEA(ElevatorPosition.BARGE, PivotPosition.BARGE, ClawPosition.ALGEA);

    private ElevatorPosition elevator;
    private PivotPosition pivot;
    private ClawPosition claw;

    private ScoringPositions(ElevatorPosition elevator, PivotPosition pivot, ClawPosition claw){
      this.elevator = elevator;
      this.pivot = pivot;
      this.claw = claw;
    }

    public double getElevatorPosition(){
      return elevator.getElevatorHeight();
    }
    public ElevatorPosition getElevatorEnum(){
      return elevator;
    }

    public double getPivotPosition(){
      return pivot.getPivotRotation();
    }

    public double getPinchPosition(){
      return claw.getPinchRotation();
    }

  }
  
}
