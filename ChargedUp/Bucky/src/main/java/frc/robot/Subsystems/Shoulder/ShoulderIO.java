package frc.robot.Subsystems.Shoulder;

import org.littletonrobotics.junction.AutoLog;

public interface ShoulderIO {

    public default void updateInputs(ShoulderIOInputs inputs){};

    @AutoLog
    public class ShoulderIOInputs {

        public double position = 0;

        public double speed = 0;

        public double voltage;

        public double current = 0;

        public double temperature = 0;

    }

    public default void setPosition(double position) {}

    public default void setVoltage(double voltage){}

    public default void stop(){}

} 
