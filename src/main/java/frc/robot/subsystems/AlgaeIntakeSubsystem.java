// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class AlgaeIntakeSubsystem extends SubsystemBase {
  /** Creates a new AlgaeIntakeSubsystem. */
  private SparkMax algaeIntake;

  private double algaeIntakeRPM;
  private double algaeIntakePosition;

  public AlgaeIntakeSubsystem() {
    algaeIntake = new SparkMax(0, MotorType.kBrushless);

      configureSparkMAX(algaeIntake, Constants.ALGAE_INTAKE_INVERT);

      SmartDashboard.putNumber("Algae Intake Speed", algaeIntake.get());
  }
  private void configureSparkMAX(SparkMax max, boolean reverse) {
		SparkMaxConfig config = new SparkMaxConfig();
    config.inverted(reverse).idleMode(IdleMode.kBrake);
    max.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
