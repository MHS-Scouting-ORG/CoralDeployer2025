package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralIntakeSubsystem;


public class L1CoralDeployerCommand extends Command {
    private CoralIntakeSubsystem coralIntakeSub; 
  
    public L1CoralDeployerCommand(CoralIntakeSubsystem coralIntakeSub) {
      this.coralIntakeSub = coralIntakeSub;
      addRequirements(this.coralIntakeSub);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      coralIntakeSub.setIntakeSpeed(Constants.CORAL_DEPLOY_SPEED - 0.5);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      coralIntakeSub.setIntakeSpeed(0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}
