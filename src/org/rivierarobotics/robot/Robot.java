package org.rivierarobotics.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {

    public static Robot inst;

    public DriveTrain driveTrain;
    public Arm arm;

    @Override
    public void robotInit() {
        inst = this;
        driveTrain = new DriveTrain();
        arm = new Arm();
    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().add(new FirstAutoCommandGroup());
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

}
