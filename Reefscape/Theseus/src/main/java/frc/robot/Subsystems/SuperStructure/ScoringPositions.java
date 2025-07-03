package frc.robot.Subsystems.SuperStructure;

import java.util.OptionalDouble;

public class ScoringPositions {
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
    CORAL(32),
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

  public static enum ClawSpeeds{
    // If .empty excludes that value: .of(#) is the value your adding
    //    This allows for the overloading of our intake, different sides different speeds
    INTAKE(1, OptionalDouble.empty()),
    L1(0, OptionalDouble.empty()),//TODO Find this value
    CORAL(-0.9, OptionalDouble.empty()),
    ALGEA_PROCESSOR(-0.2, OptionalDouble.empty()),
    ALGEA_BARGE(-0.58, OptionalDouble.of(0)),
    DISPENSE(-1, OptionalDouble.empty()),
    ALGEA_AUTO(-0.84, OptionalDouble.empty());

    private double intakeVelocity;
    private OptionalDouble rightVelocity;

    private ClawSpeeds(double intakeVelocity, OptionalDouble rightVelocity){
      this.intakeVelocity = intakeVelocity;
      this.rightVelocity = rightVelocity;
    }

    public double getIntakeVelocity(){
      return intakeVelocity;
    }

    public double getRightVelocity(){
      return rightVelocity.isPresent() ? rightVelocity.getAsDouble() : intakeVelocity; 
    }
  }
  /* 
  public static enum CombinedPositions{
    FLOOR_ALGEA(ElevatorPosition.FLOOR_ALGEA, PivotPosition.FLOOR, ClawPosition.ALGEA, ClawSpeeds.INTAKE),
    PROCESSOR(),
    L1(),
    L2(),
    L3(),
    L4(),
    L2_ALGEA(),
    L3_ALGEA(),
    STATION(),
    BARGE();

    private ElevatorPosition elevator;
    private PivotPosition pivot;
    private ClawPosition claw;
    private ClawSpeeds clawVelocity;

    private CombinedPositions(ElevatorPosition elevator, PivotPosition pivot, ClawPosition claw, ClawSpeeds clawVelocity){

    }
  }
  */
}
