package pl.pollub.tool;

import java.awt.*;

public class RobotWrapper {
    private final Robot robot = createRobot();

    public Robot getRobot() {
        return this.robot;
    }

    private Robot createRobot() {
        if (this.robot == null) {
            Robot tempRobot = null;
            try {
                tempRobot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            return tempRobot;
        }
        return this.robot;
    }
}