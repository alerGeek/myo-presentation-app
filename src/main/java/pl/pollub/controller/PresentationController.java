package pl.pollub.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pl.pollub.modefactory.ModeFactory;
import pl.pollub.types.ModeType;
import pl.pollub.modefactory.mode.presentationmode.PresentationMode;

import java.net.URL;
import java.util.ResourceBundle;

public class PresentationController implements AbstractModeController {
    @FXML private AnchorPane presentationView;

    private final ModeType modeType = ModeType.PRESENTATION;
    private PresentationMode mode = (PresentationMode) ModeFactory.createMode(modeType);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presentationView.setVisible(false);
    }

    public void startMode() {
        mode.startMode();
    }

    public void stopMode() {
        mode.stopMode();
    }

}
