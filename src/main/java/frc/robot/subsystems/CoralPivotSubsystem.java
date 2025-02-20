package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;

public class CoralPivotSubsystem extends SubsystemBase {

  private final TalonSRX coralIntake, coralPivot;
  private PIDController pivotPIDController;
  private double setpoint, error, prevError;
  private boolean pidStatus;

  public CoralPivotSubsystem() {

    coralIntake = new TalonSRX(Constants.CORAL_INTAKE_ID);
    coralPivot = new TalonSRX(Constants.CORAL_PIVOT_ID);
    coralIntake.configForwardLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen, Constants.CORAL_INTAKE_ID);
    pivotPIDController = new PIDController(0.0006,0.0001, 0);
    pivotPIDController.setTolerance(25);
    pidStatus = false;

    coralIntake.configFactoryDefault();
    coralPivot.configFactoryDefault();

    coralIntake.setInverted(true);
    coralIntake.setNeutralMode(NeutralMode.Brake);
    coralPivot.setNeutralMode(NeutralMode.Brake);
  }

  public void resetPivotEnc(){
    coralPivot.getSensorCollection().setQuadraturePosition(0, 0);
  }

  // set Coral Pivot speed to speed
  public void setPivotSpeed(double speed) {
    coralPivot.set(TalonSRXControlMode.PercentOutput, speed);
    if(coralPivot.getSensorCollection().getQuadraturePosition()<= -600 && speed < 0){
      coralPivot.set(TalonSRXControlMode.PercentOutput, 0);
    } else if (coralPivot.getSensorCollection().getQuadraturePosition() >=-50 && speed > 0){
      coralPivot.set(TalonSRXControlMode.PercentOutput, 0);
    }
  }


  // set Coral PIDstatus to stat
  public void setPIDStatus(boolean stat){
    pidStatus = stat;
  }

  // set Coral PID setpoint to setpoint
  public void setCoralPivotPIDSetpoint(double setpoint){
    this.setpoint = setpoint;
  }

  // return Coral Encoder
  public double getCoralSwitchEnc() {
    return coralPivot.getSensorCollection().getQuadraturePosition();
  }

  public double getSetpoint(){
    return setpoint;
  }

  // return current value of PID status
  public boolean getPIDStatus(){
    return pidStatus;
  }

  // return current value of Limit Switch
  public boolean getLimitSwitch() {
    return coralIntake.isFwdLimitSwitchClosed() == 1;
  }


  // return true if at setpoint
  public boolean atSetpoint(){
    return pivotPIDController.atSetpoint();
  }

  @Override
  public void periodic() {

    if(getLimitSwitch()){
      resetPivotEnc();
    }


    if(getPIDStatus()){
      error = pivotPIDController.calculate(getCoralSwitchEnc(), setpoint);
      if(error > Constants.CORAL_PIVOT_SPEED && !atSetpoint()){
        error = Constants.CORAL_PIVOT_SPEED;
      }else if(error < -Constants.CORAL_PIVOT_SPEED && !atSetpoint()){
        error = -Constants.CORAL_PIVOT_SPEED;
      }

      if(error < 0 && prevError > 0){
        pivotPIDController.reset();
      } else if(error > 0 && prevError < 0){
        pivotPIDController.reset();
      }

      prevError = error;
    }

    if(atSetpoint()){
      error = 0;
    }

    setPivotSpeed(error);

    SmartDashboard.putBoolean("Limit Switch", getLimitSwitch());
    SmartDashboard.putNumber("Intake Pivot Enc", getCoralSwitchEnc());
    SmartDashboard.putNumber("Pivot PID Error", error);
    SmartDashboard.putNumber("Setpoint", setpoint);
    SmartDashboard.putBoolean("PID Status", pidStatus);
    SmartDashboard.putBoolean("At Setpoint", atSetpoint());

  //coralIntake.set(TalonSRXControlMode.PercentOutput, intakeSpeed);
  // coralPivot.set(TalonSRXControlMode.PercentOutput, pivotSpeed);
  }
}