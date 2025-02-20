// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralPivotSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class PivotRightCommand extends Command {
  /** Creates a new PivotLeftCommand. */
  private CoralPivotSubsystem coralPivotSub;
  private Timer timer;
  public PivotRightCommand(CoralPivotSubsystem coralPivotSub) {
    this.coralPivotSub = coralPivotSub;
    timer = new Timer();
    addRequirements(this.coralPivotSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    coralPivotSub.setPIDStatus(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    coralPivotSub.setCoralPivotPIDSetpoint(-50);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return coralPivotSub.atSetpoint() || coralPivotSub.getLimitSwitch();
  }
}
