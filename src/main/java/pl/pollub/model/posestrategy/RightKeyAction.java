package pl.pollub.model.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.tool.RobotManager;

import java.awt.*;
import java.awt.event.KeyEvent;

@Setter
@Getter
public class RightKeyAction implements KeyActionStrategy {
    private static final int KEY_CODE = KeyEvent.VK_RIGHT;
    private Robot robot = RobotManager.getRobot();

    public int pressKey() {
        this.robot.keyPress(KEY_CODE);
        this.robot.keyRelease(KEY_CODE);
        return KEY_CODE;
    }
}