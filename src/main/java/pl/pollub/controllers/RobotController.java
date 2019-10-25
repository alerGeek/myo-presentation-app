package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pl.pollub.model.factory.ModeFactory;
import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.modes.RobotMode;

import java.net.URL;
import java.util.ResourceBundle;

public class RobotController implements AbstractModeController {
    @FXML private AnchorPane robotView;

    private final ModeType modeType = ModeType.PRESENTATION;
    private RobotMode mode = (RobotMode) ModeFactory.createMode(modeType);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        robotView.setVisible(false);
    }

    public void startMode() {
        mode.startMode();
    }

    public void stopMode() {
        mode.stopMode();
    }

}
