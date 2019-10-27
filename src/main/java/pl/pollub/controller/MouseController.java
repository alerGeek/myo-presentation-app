package pl.pollub.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.modefactory.ModeFactory;
import pl.pollub.types.ModeType;
import pl.pollub.modefactory.mode.mousemode.MouseMode;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class MouseController implements AbstractModeController {
    @FXML private AnchorPane mouseView;
    @FXML private Label currentPosition;

    private final ModeType modeType = ModeType.MOUSE;
    private final MouseMode mode = (MouseMode) ModeFactory.createMode(modeType);

    public void initialize(URL location, ResourceBundle resources) {
        this.currentPosition.textProperty().bind(mode.getProperties().getCurrentLocation());
    }

    public void startMode() {
        mode.startMode();
        mode.getDataCollector().getCurrentPositionService().restart();
    }

    public void stopMode() {
        mode.stopMode();
        mode.getDataCollector().getCurrentPositionService().cancel();
    }
}
