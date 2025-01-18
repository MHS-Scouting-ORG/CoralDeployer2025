// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralIntakeSubsystem;

public class CoralSwitchCommand extends Command {

  private CoralIntakeSubsystem coralIntakeSub;

  public CoralSwitchCommand(CoralIntakeSubsystem coralIntakeSub) {
    this.coralIntakeSub = coralIntakeSub;
    addRequirements(this.coralIntakeSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (coralIntakeSub.getCoralSwitchEnc() >= -5) {
      while (coralIntakeSub.getCoralSwitchEnc() >= -10) { 
          coralIntakeSub.coralSwitchDown(0.1);
      }
    } else if(coralIntakeSub.getCoralSwitchEnc() <= 5){
      while (coralIntakeSub.getCoralSwitchEnc() < 0) { 
          coralIntakeSub.coralSwitchUp(0.1);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    coralIntakeSub.stopCoralSwitch();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
