// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class CoralIntakeSubsystem extends SubsystemBase {
  /** Creates a new CoralIntakeSubsystem. */
  private final DigitalInput opticalSensor;
  private final SparkMax coralIntake;

  public CoralIntakeSubsystem() {

    opticalSensor = new DigitalInput(Constants.CORAL_OPTICAL_SENSOR_ID);
    coralIntake = new SparkMax(Constants.CORAL_INTAKE_ID, MotorType.kBrushless);
    coralIntake.setVoltage(1);
    
  }
  
  // return current value of Optical Switch
  public boolean getOpticalSensor() {
    return opticalSensor.get();
  }

  // set Coral Intake sped to speed
  public void setIntakeSpeed(double speed) {
    coralIntake.set(speed);
  }

  public void stopIntake(){
    coralIntake.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Optical Sensor", getOpticalSensor());
  }
}
