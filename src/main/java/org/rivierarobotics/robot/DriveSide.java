package org.rivierarobotics.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveSide {

    private WPI_TalonSRX motor1;
    private WPI_TalonSRX motor2;

    public DriveSide(boolean isLeft) {
        if (isLeft) {
            motor1 = new WPI_TalonSRX(1);
            motor2 = new WPI_TalonSRX(2);
        } else {
            motor1 = new WPI_TalonSRX(3);
            motor2 = new WPI_TalonSRX(4);
            motor1.setInverted(true);
            motor2.setInverted(true);
        }
    }

    public void setPower(double pow) {
        motor1.set(pow);
        motor2.set(pow);
    }

}
