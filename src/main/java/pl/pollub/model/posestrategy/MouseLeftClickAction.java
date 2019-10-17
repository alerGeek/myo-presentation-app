package pl.pollub.model.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.tool.RobotManager;

import java.awt.*;
import java.awt.event.MouseEvent;

@Getter
@Setter
public class MouseLeftClickAction implements KeyActionStrategy {
    private static final int MOUSE_KEY_CODE = MouseEvent.BUTTON2_DOWN_MASK;

    private Robot robot = RobotManager.getRobot();


    public int pressKey() {
        this.robot.mousePress(MOUSE_KEY_CODE);
        this.robot.mouseRelease(MOUSE_KEY_CODE);
        return MOUSE_KEY_CODE;
    }
}