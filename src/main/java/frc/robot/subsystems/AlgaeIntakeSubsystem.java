// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class AlgaeIntakeSubsystem extends SubsystemBase {
  /** Creates a new AlgaeIntakeSubsystem. */
  private TalonSRX algaeIntake;

  public AlgaeIntakeSubsystem(){
    algaeIntake = new TalonSRX(0);
  }

  public void startAlgaeIntake(){
    algaeIntake.set(TalonSRXControlMode.Velocity, 0.5);
  }

  public void stopAlgaeIntake(){
    algaeIntake.set(TalonSRXControlMode.Velocity, 0);
  }
  public void reverseAlgaeIntake(){
    algaeIntake.set(TalonSRXControlMode.Velocity, -0.5);
  }
  





}

 