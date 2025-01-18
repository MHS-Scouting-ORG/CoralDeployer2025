package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIntakeSubsystem extends SubsystemBase {
  
  private final TalonFX coralIntake;
  private final SparkMax coralSwitch;
  private final RelativeEncoder coralSwitchEnc;

  public CoralIntakeSubsystem() {
    coralIntake = new TalonFX(9);
    coralSwitch = new SparkMax(1, MotorType.kBrushless);
    coralSwitchEnc = coralSwitch.getEncoder();
  }

  public void coralSwitchUp(double speed){
    coralSwitch.set(speed);
  }

  public void coralSwitchDown(double speed){
    coralSwitch.set(-speed);
  }

  public void stopCoralSwitch(){
    coralSwitch.stopMotor();
  }

  public double getCoralSwitchEnc(){
    return coralSwitchEnc.getPosition();
  }

  public void intake(double speed){ // speed = amount of voltage on motor
    coralIntake.setVoltage(speed);
  }

  public void outtake(double speed){
    coralIntake.setVoltage(-speed);
  }

  public void stopCoralIntake(){
    coralIntake.setVoltage(0);
    coralIntake.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
