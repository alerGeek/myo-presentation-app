package pl.pollub.tool;

import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class RobotManager implements Initializable {
    public static final RobotWrapper ROBOT_WRAPPER = new RobotWrapper();

    public void initialize(URL location, ResourceBundle resources) {
        setRobotPreferences();
    }

    public static Robot getRobot() {
        return ROBOT_WRAPPER.getRobot();
    }

    private void setRobotPreferences() {
        ROBOT_WRAPPER.getRobot().setAutoDelay(20);
        ROBOT_WRAPPER.getRobot().setAutoWaitForIdle(true);
    }

    public static void moveRobot(int x, int y) {
        ROBOT_WRAPPER.getRobot().mouseMove(x, y);
    }
}