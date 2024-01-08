package pl.pollub.util;

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

    public static void moveMouse(float dx, float dy) {
        Point currentPosition = MouseInfo.getPointerInfo().getLocation();
        getRobot().mouseMove(currentPosition.x + (int) dx, currentPosition.y + (int) dy);
    }

    public static int simulateMouseButtonEvent(int keyCode) {
        getRobot().mousePress(keyCode);
        getRobot().mouseRelease(keyCode);
        return keyCode;
    }

    public static int simulateKeyEvent(int keyCode) {
        getRobot().keyPress(keyCode);
        getRobot().keyRelease(keyCode);
        return keyCode;
    }

}