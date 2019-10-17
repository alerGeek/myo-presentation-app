package pl.pollub.tool;

import java.awt.*;


public class RobotWrapper {
    public Robot getRobot() {
        return this.robot;
    }

    private final Robot robot = createRobot();
    private int count = 0;

    private Robot createRobot() {
        if (this.robot == null) {
            Robot tempRobot = null;
            try {
                tempRobot = new Robot();
                System.out.println("Nowy robot: " + (++count));
            } catch (AWTException e) {
                e.printStackTrace();
            }
            return tempRobot;
        }
        return this.robot;
    }
}