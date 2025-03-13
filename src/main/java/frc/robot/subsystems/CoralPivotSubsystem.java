package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralPivotSubsystem extends SubsystemBase {

  private final DigitalInput highLimit, lowLimit;
  private final RelativeEncoder coralPivotEnc;
  private final SparkMax coralPivot;
  private final PIDController coralPivotPidController;
  private double prevError, command;

  public CoralPivotSubsystem() {
    
    coralPivot = new SparkMax(Constants.CORAL_PIVOT_ID, MotorType.kBrushless);

    coralPivotEnc = coralPivot.getEncoder();

    highLimit = new DigitalInput(Constants.HIGHLIMIT_ID);
    lowLimit = new DigitalInput(Constants.LOWLIMIT_ID);

    coralPivotPidController = new PIDController(0, 0, 0);

  }

  public boolean getHighLimit(){
    return highLimit.get();
  }

  public boolean getLowLimit(){
    return lowLimit.get();
  }

  public void setCoralPivotSetpoint(double point){
    coralPivotPidController.setSetpoint(point);
  }

  public double getCoralPivotSetpoint(){
    return coralPivotPidController.getSetpoint();
  }

  public boolean atSetpoint (){
    return coralPivotPidController.atSetpoint();
  }

  public double getCoralPivotEnc (){
    return coralPivotEnc.getPosition();
  }

  public void setCoralPivotSpeed (double val){
    coralPivot.set(val);
  }

  @Override
  public void periodic() {
    double currError = getCoralPivotSetpoint() - getCoralPivotEnc();

    command = coralPivotPidController.calculate(getCoralPivotEnc(), getCoralPivotSetpoint());
    if(command > Constants.CORAL_PIVOT_UP_SPEED){
      command = Constants.CORAL_PIVOT_UP_SPEED;
    } else if(command < Constants.CORAL_PIVOT_DOWN_SPEED){
      command = Constants.CORAL_PIVOT_DOWN_SPEED;
    }

    if(currError < 0 && prevError > 0){
      coralPivotPidController.reset();
    } else if(currError > 0 && prevError < 0){
      coralPivotPidController.reset();
    }

    prevError = currError;

    setCoralPivotSpeed(currError);

    SmartDashboard.putNumber("Kraken Position", getCoralPivotEnc());
    SmartDashboard.putNumber("Hang PID setpoint", getCoralPivotSetpoint());
    SmartDashboard.putNumber("Command Output", command);
    // This method will be called once per scheduler run
  }
}
