// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AlgaePIDcmd;
import frc.robot.commands.Autos;
import frc.robot.commands.CoralSwitchCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.CoralIntakeSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.AlgaeIntakeSubsystem;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final CoralIntakeSubsystem coralIntakeSub = new CoralIntakeSubsystem();
  private final CoralSwitchCommand coralSwitchCmd = new CoralSwitchCommand(coralIntakeSub);
  private final Joystick stick = new Joystick(0);
  private final AlgaeIntakeSubsystem algaeIntakeSub = new AlgaeIntakeSubsystem();
  private final AlgaePIDcmd algaeRestingPositionCmd = new AlgaePIDcmd(algaeIntakeSub, 0);
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
    

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(stick, 1).whileTrue(new InstantCommand(() -> coralIntakeSub.intake(0.1)));
    new JoystickButton(stick, 1).whileFalse(new InstantCommand(() -> coralIntakeSub.stopCoralIntake()));
    new JoystickButton(stick, 2).whileTrue(new InstantCommand(() -> coralIntakeSub.outtake(0.1)));
    new JoystickButton(stick, 2).whileFalse(new InstantCommand(() -> coralIntakeSub.stopCoralIntake()));
    //new JoystickButton(stick, 3).onTrue(coralSwitchCmd);
  new JoystickButton(stick, 4).whileTrue(new InstantCommand(() -> algaeIntakeSub.startAlgaeIntake()));
    new JoystickButton(stick, 4).whileFalse(new InstantCommand(() -> algaeIntakeSub.stopAlgaeIntake()));
    new JoystickButton(stick, 5).whileTrue(new InstantCommand(() -> algaeIntakeSub.reverseAlgaeIntake()));
    new JoystickButton(stick, 5).whileFalse(new InstantCommand(() -> algaeIntakeSub.stopAlgaeIntake()));
    new JoystickButton (stick, 6).onTrue(new AlgaePIDcmd(algaeIntakeSub, 50));
    new JoystickButton(stick, 7).onTrue(new InstantCommand(() ->  algaeIntakeSub.resetEncoder()));
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
