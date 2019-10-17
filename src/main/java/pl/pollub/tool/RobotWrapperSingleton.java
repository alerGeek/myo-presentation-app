package pl.pollub.tool;

import lombok.Getter;

import java.awt.*;

@Getter
public enum RobotWrapperSingleton {
    INSTANCE;

    private final Robot robot;
    private int count = 0;

    RobotWrapperSingleton() {
        this.robot = createRobot();
        setRobotPreferences();
    }

    private Robot createRobot() {
        if (this.robot == null) {
            Robot tempRobot = null;
            try {
                tempRobot = new Robot();
                System.out.println("Nowy robot singleton: " + (++count));
            } catch (AWTException e) {
                e.printStackTrace();
            }
            return tempRobot;
        }
        return this.robot;
    }

    private void setRobotPreferences() {
        robot.setAutoDelay(20);
        robot.setAutoWaitForIdle(true);
    }
}