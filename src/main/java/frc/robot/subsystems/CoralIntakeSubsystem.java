package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIntakeSubsystem extends SubsystemBase {
  
  private final TalonSRX coralIntake;
  private final TalonSRX coralPivot;

  public CoralIntakeSubsystem() {
    coralIntake = new TalonSRX(3);
    coralPivot = new TalonSRX(1);
  }

  public void coralSwitchUp(double speed){
    coralPivot.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public void coralSwitchDown(double speed){
    coralPivot.set(TalonSRXControlMode.PercentOutput, -speed);
  }

  public void stopCoralSwitch(){
    coralPivot.set(TalonSRXControlMode.PercentOutput, 0);
  }

  public double getCoralSwitchEnc(){
    return coralPivot.getSelectedSensorPosition();
  }

  public void intake(double speed){ // speed = amount of voltage on motor
    coralIntake.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public void outtake(double speed){
    coralIntake.set(TalonSRXControlMode.PercentOutput, -speed);
  }

  public void stopCoralIntake(){
    coralIntake.set(TalonSRXControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    
  }
}
