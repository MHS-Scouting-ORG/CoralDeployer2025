package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralIntakeSubsystem extends SubsystemBase {
  private final TalonSRX coralIntake, coralPivot;
  private final DigitalInput opticalSensor, leftLimitSwitch, rightLimitSwitch;
  private boolean pidStatus;
  private double pivotSpeed; 
  private double intakeSpeed;

  public CoralIntakeSubsystem() {
    pidStatus = false;

    coralIntake = new TalonSRX(0);
    coralPivot = new TalonSRX(12);
    opticalSensor = new DigitalInput(0);
    leftLimitSwitch = new DigitalInput(1);
    rightLimitSwitch  = new DigitalInput(6);

    coralIntake.setNeutralMode(NeutralMode.Brake);
    coralPivot.setNeutralMode(NeutralMode.Brake);

    coralIntake.neutralOutput();
    coralPivot.neutralOutput();

    coralPivot.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    coralPivot.config_kD(0, getCoralSwitchEnc());
  }


  public double getCoralSwitchEnc(){
    return coralPivot.getSelectedSensorPosition();
  }

  public void coralIntake(double speed){ // speed = amount of voltage on motor
    coralIntake.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public void coralOuttake(double speed){
    coralIntake.set(TalonSRXControlMode.PercentOutput, -speed);
  }

  public void stopCoralIntake(){
    coralIntake.set(TalonSRXControlMode.PercentOutput, 0);
  }

  public boolean getOpticalSensor(){
    return opticalSensor.get();
  }

  public void pivotSpeed(double speed){
  pivotSpeed = speed;
  }

  public void intakeSpeed(double speed){
    intakeSpeed = speed;
  }

  public boolean getLeftLimitSwitch(){
    return leftLimitSwitch.get();
  }
  
  public boolean getRightLimitSwitch(){
    return rightLimitSwitch.get();
  }


  @Override
  public void periodic() {
   
   if(pidStatus == true){
    }
    if(getLeftLimitSwitch()){
      //stopCoralPivot();
    }
    else if(getLeftLimitSwitch()){
      //stopCoralPivot();
    }
    SmartDashboard.putBoolean("opticalSensor", getOpticalSensor());
  }
}
