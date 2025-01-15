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
   public static final double ALGAE_INTAKE_MAX_POWER = 0.1;
    public static final double ALGAE_INTAKE_MIN_POWER = 0.05;
    public static final double ALGAE_INTAKE_kP = 0.025;
   
   
    
    public static final double DEPLOY_SPEED = 0.1;
    public static final boolean ALGAE_INTAKE_INVERT = false;
    public static final double ALGAE_INTAKE_SPEED = 0.8;
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
   
  

  }
}
