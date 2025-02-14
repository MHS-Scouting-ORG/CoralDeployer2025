package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.CoralIntakeSubsystem;

public class CoralIntakeCommand extends Command {

    private CoralIntakeSubsystem coralIntakeSub; 
    private Timer timer = new Timer();
  
    public CoralIntakeCommand(CoralIntakeSubsystem coralIntakeSub) {
      this.coralIntakeSub = coralIntakeSub;
      addRequirements(this.coralIntakeSub);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      coralIntakeSub.setIntakeSpeed(Constants.CORAL_INTAKE_SPEED);
      timer.reset();
    }

  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      if(coralIntakeSub.getOpticalSensor()){
        coralIntakeSub.setIntakeSpeed(-Constants.CORAL_OUTTAKE_SPEED);
        timer.start();
      }
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      coralIntakeSub.setIntakeSpeed(0);
      timer.stop();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return timer.get() >= .3;
    }
  }
  
