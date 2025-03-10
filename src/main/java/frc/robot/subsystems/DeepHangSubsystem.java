
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DeepHangSubsystem extends SubsystemBase {
  /** Creates a new DeepHangSubsystem. */
  private final TalonFX hangKraken;
  private final TalonFXConfiguration hangKrakenConfig;
  private final VoltageOut voltControl;
  private final VelocityVoltage velControl;
  private final PIDController hangPidController;
  private double prevError, command;

  public DeepHangSubsystem() {
    hangKraken = new TalonFX(Constants.HANG_ID);

    hangKrakenConfig = new TalonFXConfiguration();
    hangKraken.getConfigurator().apply(hangKrakenConfig);

    voltControl = new VoltageOut(0);
    hangKraken.setControl(voltControl.withOutput(1));

    velControl = new VelocityVoltage(0);
    hangKraken.setControl(velControl.withVelocity(500));

    hangKraken.setNeutralMode(NeutralModeValue.Brake);

    hangPidController = new PIDController(0, 0, 0);
  }

  public void setHangPos(double angle){
    hangKraken.setPosition(angle);
  }

  public StatusSignal<Angle> getHangPos(){
    return hangKraken.getPosition();
  }

  public void setHangSetpoint(double point){
    hangPidController.setSetpoint(point);
  }

  public double getHangSetpoint(){
    return hangPidController.getSetpoint();
  }

  public void setHangSpeed (double speed){
    hangKraken.set(speed);
  }

  public boolean atSetpoint (){
    return hangPidController.atSetpoint();
  }

  public void stopHang(){
    hangKraken.set(0);
  }

  @Override
  public void periodic() {
    double currError = getHangSetpoint() - getHangPos().getValueAsDouble();

    command = hangPidController.calculate(getHangPos().getValueAsDouble(), getHangSetpoint());
    if(command > Constants.HANG_RAISE_SPEED){
      command = Constants.HANG_RAISE_SPEED;
    } else if(command < Constants.HANG_LOWER_SPEED){
      command = Constants.HANG_LOWER_SPEED;
    }

    if(currError < 0 && prevError > 0){
      hangPidController.reset();
    } else if(currError > 0 && prevError < 0){
      hangPidController.reset();
    }

    prevError = currError;

    setHangSpeed(currError);

    SmartDashboard.putNumber("Kraken Position", getHangPos().getValueAsDouble());
    SmartDashboard.putNumber("Hang PID setpoint", getHangSetpoint());
    // This method will be called once per scheduler run
  }
}
