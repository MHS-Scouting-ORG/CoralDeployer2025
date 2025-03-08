
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DeepHangSubsystem extends SubsystemBase {
  /** Creates a new DeepHangSubsystem. */
  private final TalonFX hangKraken;
  private final TalonFXConfiguration hangKrakenConfig;
  private final VoltageOut voltControl;
  private final VelocityVoltage velControl;

  public DeepHangSubsystem() {
    hangKraken = new TalonFX(Constants.HANG_ID);
    hangKrakenConfig = new TalonFXConfiguration();
    hangKraken.getConfigurator().apply(hangKrakenConfig);
    voltControl = new VoltageOut(0);
    hangKraken.setControl(voltControl.withOutput(4));
    velControl = new VelocityVoltage(0);
    hangKraken.setControl(velControl.withVelocity(500));
    hangKraken.setNeutralMode(NeutralModeValue.Brake);
  }

  public void raiseHang(double speed){
    hangKraken.set(speed);
  }

  public void lowerHang(double speed){
    hangKraken.set(-speed);
  }

  public void stopHang(){
    hangKraken.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
