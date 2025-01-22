// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class AlgaeIntakeSubsystem extends SubsystemBase {
  /** Creates a new AlgaeIntakeSubsystem. */
  private TalonFX algaeIntake;
  private PIDController pid;
  private double setpoint;
  private boolean pidOn = false;
  

public AlgaeIntakeSubsystem(){
  algaeIntake = new TalonFX(9);
  pid = new PIDController(0.05, 0.001, 0);
} 
public void startAlgaeIntake(){
  algaeIntake.set(0.5);
}
public void stopAlgaeIntake(){
  algaeIntake.set(0);
}
public void reverseAlgaeIntake(){
  algaeIntake.set(-0.5);
}
public double getEncoder(){
  return algaeIntake.getPosition().getValueAsDouble();
}
public void resetEncoder(){
  algaeIntake.setPosition(0);
}
public void setSetpoint(double newSetpoint){
  setpoint = newSetpoint;
}
public boolean pidFinished(){
  return pid.atSetpoint();
}
public void setpidOn(){
  pidOn = true;
}
public void setpidOff(){
  pidOn = false;
}
public void periodic(){
  
  
  if (pidOn) {
    double output = pid.calculate(getEncoder(), setpoint);
    algaeIntake.set(output);
  }
  


  SmartDashboard.putNumber("encoder", getEncoder());
  SmartDashboard.putBoolean("pidON", pidOn);
}


  





}

 