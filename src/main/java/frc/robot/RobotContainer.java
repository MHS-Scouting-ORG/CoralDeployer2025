// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CoralIntakeCommand;
import frc.robot.commands.L2AndL3PosCommand;
import frc.robot.commands.L4PosCommand;
import frc.robot.commands.CoralDeployerCommand;
import frc.robot.subsystems.CoralIntakeSubsystem;
import frc.robot.subsystems.CoralPivotSubsystem;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private SparkMax pivotMotor = new SparkMax(7, MotorType.kBrushless);
  private SparkMax intakeMotor = new SparkMax(8, MotorType.kBrushless);

  private final CoralIntakeSubsystem coralIntakeSub = new CoralIntakeSubsystem(intakeMotor);
  private final CoralPivotSubsystem coralPivotSub = new CoralPivotSubsystem(intakeMotor.getForwardLimitSwitch(), pivotMotor);
  private final XboxController xbox = new XboxController(0);
  private final Command setPoint2CurrEnc = new InstantCommand(() -> coralPivotSub.setCoralPivotSetpoint(coralPivotSub.getCoralPivotEnc()));
  // Replace with CommandPS4Controller or CommandJoystick if needed
      

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    
  }

  private void configureBindings() {
    // Intake
    new JoystickButton(xbox, XboxController.Button.kStart.value).onTrue(new ParallelCommandGroup(
      new CoralIntakeCommand(coralIntakeSub), new L2AndL3PosCommand(coralPivotSub)));
      
    new JoystickButton(xbox, XboxController.Button.kX.value).whileTrue(new CoralDeployerCommand(coralIntakeSub));
    
    // Pivot
    new JoystickButton(xbox, XboxController.Button.kY.value).onTrue(new L4PosCommand(coralPivotSub));
    new JoystickButton(xbox, XboxController.Button.kB.value).onTrue(new L2AndL3PosCommand(coralPivotSub));

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

  public Command setpoint2CurrError(){
    return setPoint2CurrEnc;
  }
}