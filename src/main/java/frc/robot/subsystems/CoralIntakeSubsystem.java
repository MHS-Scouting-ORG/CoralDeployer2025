package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIntakeSubsystem extends SubsystemBase {
  
  private final TalonFX coralIntake;
  private final SparkMax coralSwitch;

  public CoralIntakeSubsystem() {
    coralIntake = new TalonFX(9);
    coralSwitch = new SparkMax(1, MotorType.kBrushless);
  }

  public void intake(double speed){ // speed = amount of voltage on motor
    coralIntake.setVoltage(speed);
  }

  public void outtake(double speed){
    coralIntake.setVoltage(-speed);
  }

  public void stop(){
    coralIntake.setVoltage(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
