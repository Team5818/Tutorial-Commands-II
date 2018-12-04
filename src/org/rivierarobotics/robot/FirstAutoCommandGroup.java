package org.rivierarobotics.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FirstAutoCommandGroup extends CommandGroup {

    public FirstAutoCommandGroup() {
        addSequential(new DriveForward(0.5, 1));
        addSequential(new Rotate(90));
        addSequential(new DriveForward(0.5, 1));
        addSequential(new SetArmPosition(Arm.NINETY_DEGREES));
    }
}
