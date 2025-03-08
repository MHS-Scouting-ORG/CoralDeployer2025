// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.CoralIntakeCommand;
import frc.robot.commands.CoralDeployerCommand;
import frc.robot.subsystems.CoralIntakeSubsystem;
import frc.robot.subsystems.DeepHangSubsystem;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final CoralIntakeSubsystem coralIntakeSub = new CoralIntakeSubsystem();
  private final DeepHangSubsystem hangSub = new DeepHangSubsystem();
  private final XboxController xbox = new XboxController(0);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
      

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    
  }

  private void configureBindings() {
    new JoystickButton(xbox, XboxController.Button.kStart.value).onTrue(new CoralIntakeCommand(coralIntakeSub));
    new JoystickButton(xbox, XboxController.Button.kX.value).whileTrue(new CoralDeployerCommand(coralIntakeSub));
    new JoystickButton(xbox, XboxController.Button.kY.value).whileTrue(new InstantCommand(() -> hangSub.raiseHang(Constants.HANG_RAISE_SPEED)));
    new JoystickButton(xbox, XboxController.Button.kY.value).whileFalse(new InstantCommand(() -> hangSub.stopHang()));
    new JoystickButton(xbox, XboxController.Button.kA.value).whileTrue(new InstantCommand(() -> hangSub.lowerHang(Constants.HANG_LOWER_SPEED)));
    new JoystickButton(xbox, XboxController.Button.kA.value).whileFalse(new InstantCommand(() -> hangSub.stopHang()));
    //new JoystickButton(xbox, XboxController.Button.kX.value).whileTrue(new InstantCommand(() -> coralIntakeSub.setPivotSpeed(-Constants.CORAL_PIVOT_SPEED)));
    //new JoystickButton(xbox, XboxController.Button.kX.value).whileFalse(new InstantCommand(() -> coralIntakeSub.setPivotSpeed(0)));
    //new JoystickButton(xbox, XboxController.Button.kY.value).whileTrue(new InstantCommand(() -> coralIntakeSub.setPivotSpeed(Constants.CORAL_PIVOT_SPEED)));
    //new JoystickButton(xbox, XboxController.Button.kY.value).whileFalse(new InstantCommand(() -> coralIntakeSub.setPivotSpeed(0)));

    //new JoystickButton(stick, 3).onTrue(coralSwitchCmd);
  }
 
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
