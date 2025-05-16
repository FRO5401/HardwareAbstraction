package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public final class Constants {
    public static final Mode simMode = Mode.SIM;
    public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

    public static enum Mode {
        /** Running on a real robot. */
        REAL,

        /** Running a physics simulator. */
        SIM,

        /** Replaying from a log file. */
        REPLAY
    }

    public final static class DriveTrainConstants{
        /*  Motor IDs */
        public static final int RIGHT_DRIVE_1 = 1;
        public static final int RIGHT_DRIVE_2 = 3;
        public static final int RIGHT_DRIVE_3 = 5;

        public static final int LEFT_DRIVE_1 = 2;
        public static final int LEFT_DRIVE_2 = 4;
        public static final int LEFT_DRIVE_3 = 6;

    }
}
