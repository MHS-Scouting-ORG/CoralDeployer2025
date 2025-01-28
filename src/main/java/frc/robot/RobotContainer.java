// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.CoralIntakeCommand;
import frc.robot.subsystems.CoralIntakeSubsystem;
import frc.robot.commands.CoralDepolyerCommand;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final CoralIntakeSubsystem coralIntakeSub = new CoralIntakeSubsystem();
  private final Joystick stick = new Joystick(0);
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
    new JoystickButton(xbox, XboxController.Button.kA.value).onTrue(new CoralIntakeCommand(coralIntakeSub));
    new JoystickButton(xbox, XboxController.Button.kB.value).onTrue(new CoralDepolyerCommand(coralIntakeSub));
    new JoystickButton(xbox, XboxController.Button.kRightBumper.value).onTrue(new InstantCommand(() -> coralIntakeSub.setCoralPivotPIDSetpoint(0)));
    new JoystickButton(xbox, XboxController.Button.kX.value).onTrue(new InstantCommand(() -> coralIntakeSub.setCoralPivotPIDSetpoint(45)));
    new JoystickButton(xbox, XboxController.Button.kLeftBumper.value).onTrue(new InstantCommand(() -> coralIntakeSub.setCoralPivotPIDSetpoint(-45)));
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
