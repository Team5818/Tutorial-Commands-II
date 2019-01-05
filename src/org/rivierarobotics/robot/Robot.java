package org.rivierarobotics.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.rivierarobotics.i2c.api.Vl53lx;
import org.rivierarobotics.i2c.arcompat.Port;
import org.rivierarobotics.i2c.arcompat.RoboRioPort;
import org.rivierarobotics.i2c.impl.vl53l1x.DistanceMode;
import org.rivierarobotics.i2c.impl.vl53l1x.Vl53l1xI2c;

import java.util.concurrent.TimeUnit;

public class Robot extends IterativeRobot {

    public static Robot inst;

    public DriveTrain driveTrain;
    public Arm arm;
    private Vl53lx sensor;

    @Override
    public void robotInit() {
        inst = this;
        driveTrain = new DriveTrain();
        arm = new Arm();

        sensor = new Vl53l1xI2c(RoboRioPort.ONBOARD);
        sensor.getI2c().setAddress((byte) 0x29);
        sensor.setTimeout(10, TimeUnit.SECONDS);
        sensor.setAddress((byte) 0x29);
        while (!sensor.initialize()) {
            DriverStation.reportError("Unable to initialize VL53LX", false);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        sensor.setDistanceMode(DistanceMode.LONG);
        sensor.setMeasurementTimingBudget(50_000);
        sensor.startContinuous(50);

        while (true) {
            String output = sensor.read() + (sensor.timeoutOccurred() ? " (TIMEOUT!)" : "");
            DriverStation.reportError("SOUT: " + output, false);
            SmartDashboard.putString("Sensor output", output);
        }
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
