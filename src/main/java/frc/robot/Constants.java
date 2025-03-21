// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Speeds
    public static final double CORAL_INTAKE_SPEED = 0.8;
    public static final double CORAL_DEPLOY_SPEED = 0.8;
    public static final double CORAL_OUTTAKE_SPEED = 0;

    public static final double CORAL_PIVOT_UP_SPEED = 0.6;
    public static final double CORAL_PIVOT_DOWN_SPEED = -0.6;

    public static final double HANG_RAISE_SPEED = 0.4;
    public static final double HANG_LOWER_SPEED = -0.4;

    // ID
    public static final int CORAL_INTAKE_ID = 8;
    public static final int CORAL_OPTICAL_SENSOR_ID = 4;
    public static final int CORAL_PIVOT_ID = 7;

    public static final int HANG_ID = 3;

    public static final int HIGHLIMIT_ID = 1;
    public static final int LOWLIMIT_ID = 2;

    // PIVOT PID VALUES

    public static final double kPIVOT_P = 0.1;
    public static final double kPIVOT_I = 0;
    public static final double kPIVOT_D = 0;

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
   
  

  }
}
