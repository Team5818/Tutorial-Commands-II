package org.rivierarobotics.robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveForward extends TimedCommand {

    private DriveTrain dt = Robot.inst.driveTrain;
    private double power;

    public DriveForward(double power, double time) {
        super(time);
        this.power = power;
        requires(dt);
    }

    @Override
    protected void execute() {
        dt.setPower(power, power);
    }

}
