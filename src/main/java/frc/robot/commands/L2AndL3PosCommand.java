// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralPivotSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class L2AndL3PosCommand extends Command {
  /** Creates a new L1CoralPivotCommand. */
  private CoralPivotSubsystem coralPivotSub;
  public L2AndL3PosCommand(CoralPivotSubsystem coralPivotSub) {
    this.coralPivotSub = coralPivotSub;
    addRequirements(this.coralPivotSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    coralPivotSub.setPIDStat(false);
    // use the commented code 
    // then delete the line of code above this comment and inside the execute method 
    // if the coral pivot speed starts to break the gears
    // I made this just in case this problem occured
    // This creates a soft limit WITH the NEW kP value that is OVERSHOOTS from the setpoint
    // If this causes difficulty to intake coral then call me in to fix it

    // coralPivotSub.setPIDStat(true);
    // coralPivotSub.setCoralPivotSetpoint(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    coralPivotSub.setCoralPivotSpeed(-0.35);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    coralPivotSub.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return coralPivotSub.getPivotLimitSwitch(); // add coralPivotSub.atSetpoint() || if the problem occured
  }
}
