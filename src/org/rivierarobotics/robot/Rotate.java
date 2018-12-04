package org.rivierarobotics.robot;

import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command {

    private DriveTrain dt = Robot.inst.driveTrain;
    private double power;
    private double degrees;
    private double startDegrees;

    public Rotate(double degrees) {
        this.degrees = degrees;
        requires(dt);
    }

    @Override
    protected void initialize() {
        startDegrees = 0 /* driveTrain.getDegrees() */;
        if (startDegrees < degrees) {
            // we need to rotate clockwise
            power = 0.5;
        } else {
            // we need to rotate counter-clockwise
            power = -0.5;
        }
    }

    @Override
    protected void execute() {
        dt.setPower(power, -power);
    }

    @Override
    protected boolean isFinished() {
        // Note: normally we would have some algorithm to figure out
        // how far we have rotated using the distance of the wheels
        // but for simplicity we'll ignore that for now.
        double degreesTraveled = 0 /* driveTrain.getDegrees() */ - startDegrees;

        return degreesTraveled >= degrees;
    }

}
