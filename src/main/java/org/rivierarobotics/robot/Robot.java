package org.rivierarobotics.robot;

import com.armabot.lidar.api.Vl53l0x;
import com.armabot.lidar.api.Vl53l1x;
import com.armabot.lidar.api.Vl6180x;
import com.armabot.lidar.arcompat.RoboRioPort;
import com.armabot.lidar.impl.vl53l0x.Vl53l0xI2c;
import com.armabot.lidar.impl.vl6180x.Vl6180xI2c;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import java.util.concurrent.TimeUnit;

public class Robot extends TimedRobot {

    public static Robot inst;

    public DriveTrain driveTrain;
    public Arm arm;
    private Vl53l1x l1xSensor;
    private Vl6180x l0xSensor;
    private final NetworkTableEntry l1x = Shuffleboard.getTab("Sensors")
            .add("VL53L1X", "No data").getEntry();
    private final NetworkTableEntry l0x = Shuffleboard.getTab("Sensors")
            .add("VL618X", "No data").getEntry();

    @Override
    public void robotInit() {
        inst = this;
        driveTrain = new DriveTrain();
        arm = new Arm();
//        var output = new DigitalOutput(0);
//        output.set(false);
//        SleepEasy.forUnit(1, TimeUnit.SECONDS);
//        output.set(true);

//        while (true) {
//            l1xSensor = new Vl53l1xI2c(RoboRioPort.ONBOARD);
//            l1xSensor.getI2c().setAddress((byte) 0x29);
//            l1xSensor.setTimeout(10, TimeUnit.SECONDS);
//            var error = l1xSensor.initialize();
//            error.ifPresent(err ->
//                    DriverStation.reportError("Unable to initialize VL53L1X: " + err, false)
//            );
//            if (error.isEmpty()) {
//                break;
//            }
//            l1xSensor.close();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//            }
//        }
//        l1xSensor.setDistanceMode(DistanceMode.LONG);
//        l1xSensor.setMeasurementTimingBudget(50_000);
//        l1xSensor.startContinuous(50);

        l0xSensor = new Vl6180xI2c(RoboRioPort.ONBOARD);
        l0xSensor.getI2c().setAddress((byte) 0x29);
        l0xSensor.setTimeout(10, TimeUnit.SECONDS);
        while (true) {
            var error = l0xSensor.initialize();
            error.ifPresent(err ->
                    DriverStation.reportError("Unable to initialize VL53L0X: " + err, false)
            );
            if (error.isEmpty()) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        l0xSensor.configureDefault();
        l0xSensor.startRangeContinuous(100);
    }

    @Override
    public void disabledPeriodic() {
        String output;
        if (l1xSensor != null && l1xSensor.dataReady()) {
            output = l1xSensor.read() + (l1xSensor.timeoutOccurred() ? " (TIMEOUT!)" : "");
            l1x.setString(output);
        }
        if (l0xSensor != null && l0xSensor.dataReadyRange()) {
            output = l0xSensor.readRangeContinuousMillimeters() + (l0xSensor.timeoutOccurred() ? " (TIMEOUT!)" : "");
            l0x.setString(output);
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
