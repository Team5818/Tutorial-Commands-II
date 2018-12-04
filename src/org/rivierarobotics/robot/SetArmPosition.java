package org.rivierarobotics.robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetArmPosition extends Command {

    private Arm arm = Robot.inst.arm;
    private int targetPosition;
    private int direction;

    public SetArmPosition(int targetPosition) {
        this.targetPosition = targetPosition;
        requires(arm);
    }

    @Override
    protected void initialize() {
        direction = sign(arm.getPosition() - targetPosition);
    }

    @Override
    protected void execute() {
        moveTowardPosition(targetPosition);
    }

    @Override
    protected boolean isFinished() {
        if (direction < 0) {
            return arm.getPosition() <= targetPosition;
        } else {
            return arm.getPosition() >= targetPosition;
        }
    }

    private static int sign(int n) {
        if (n == 0) {
            return 0;
        } else if (n > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * How hard we want to move towards a position.
     */
    private static final double MOVE_POWER = 0.00001;

    private void moveTowardPosition(int position) {
        int currentPos = arm.getPosition();
        int diff = position - currentPos;
        // Figure out which way we're going.
        int direction = sign(diff);
        // Figure out how hard we're going that way.
        double strength = Math.min(diff * MOVE_POWER, 1.0);
        // Pass that information to the motor.
        arm.setPower(direction * strength);
    }

}
