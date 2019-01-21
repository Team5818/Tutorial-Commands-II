package org.rivierarobotics.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

    private DriveSide left;
    private DriveSide right;

    public DriveTrain() {
        left = new DriveSide(true);
        right = new DriveSide(false);

    }

    public void setPower(double l, double r) {
        left.setPower(l);
        right.setPower(r);
    }

    @Override
    protected void initDefaultCommand() {
    }

}
