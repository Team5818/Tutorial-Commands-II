package org.rivierarobotics.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

    public static final int NINETY_DEGREES = 5000;

    private WPI_TalonSRX motor;

    public Arm() {
        motor = new WPI_TalonSRX(5);
    }

    public int getPosition() {
        return motor.getSensorCollection().getQuadraturePosition();
    }

    public void setPower(double power) {
        motor.set(power);
    }

    @Override
    protected void initDefaultCommand() {
    }

}
