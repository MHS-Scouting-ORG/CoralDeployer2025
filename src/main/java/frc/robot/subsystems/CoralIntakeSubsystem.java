package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;

public class CoralIntakeSubsystem extends SubsystemBase {
  private final TalonSRX coralIntake, coralPivot;
  private final DigitalInput opticalSensor, limitSwitch;
  private PIDController pivotPIDController;
  private int setpoint;
  private boolean pidStatus;

  public CoralIntakeSubsystem() {
    coralIntake = new TalonSRX(Constants.CORAL_INTAKE_ID);
    coralPivot = new TalonSRX(Constants.CORAL_PIVOT_ID);
    opticalSensor = new DigitalInput(Constants.CORAL_OPTICAL_SENSOR_ID);
    limitSwitch = new DigitalInput(Constants.CORAL_LIMIT_SWITCH_ID);
    pivotPIDController = new PIDController(0.1, 0, 0);
    pivotPIDController.setTolerance(50);
    pidStatus = false;

    coralIntake.setInverted(true);
    coralIntake.setNeutralMode(NeutralMode.Brake);
    coralPivot.setNeutralMode(NeutralMode.Brake);

    coralIntake.neutralOutput();
    coralPivot.neutralOutput();
    coralPivot.configSelectedFeedbackSensor(FeedbackDevice.Analog);
  }

  public double getCoralSwitchEnc() {
    return coralPivot.getSensorCollection().getQuadraturePosition();
  }

  public void resetPivotEnc(){
    coralPivot.getSensorCollection().setQuadraturePosition(0, 0);
  }

  public boolean getOpticalSensor() {
    return opticalSensor.get();
  }

  public boolean atSetpoint(){
    return coralPivot.getSensorCollection().getQuadraturePosition() == setpoint;
  }

  public void setPivotSpeed(double speed) {
    coralPivot.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public void setIntakeSpeed(double speed) {
    coralIntake.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public boolean getPIDStatus(){
    return pidStatus;
  }

  public void setPIDStatus(boolean stat){
    pidStatus = stat;
  }

  public void setCoralPivotPIDSetpoint(int setpoint){
    setpoint = this.setpoint;
  }

  public boolean getLimitSwitch() {
    return !limitSwitch.get();
  }

  @Override
  public void periodic() {


    if(getLimitSwitch()){
      resetPivotEnc();
    }

    if(pidStatus){
      double error = pivotPIDController.calculate(getCoralSwitchEnc(), setpoint);
      if(error > Constants.CORAL_PIVOT_SPEED){
        error = Constants.CORAL_PIVOT_SPEED;
      }else if(error < -Constants.CORAL_PIVOT_ID){
        error = -Constants.CORAL_PIVOT_SPEED;
      }

      setPivotSpeed(error);
    }

     //coralIntake.set(TalonSRXControlMode.PercentOutput, intakeSpeed);
   // coralPivot.set(TalonSRXControlMode.PercentOutput, pivotSpeed);
    

    SmartDashboard.putBoolean("opticalSensor", getOpticalSensor());
    SmartDashboard.putBoolean("Limit Switch", getLimitSwitch());
    SmartDashboard.putNumber("Intake Pivot Enc", getCoralSwitchEnc());
    SmartDashboard.putNumber("Pivot PID Error", pivotPIDController.calculate(getCoralSwitchEnc(), setpoint));
    SmartDashboard.putNumber("Setpoint", setpoint);
    SmartDashboard.putBoolean("PID Status", pidStatus);
  }
}