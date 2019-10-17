package pl.pollub.model.listener.propertieslisteners;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import pl.pollub.model.MyoState;

import java.util.List;

public class MyoStateListener implements ChangeListener<MyoState> {
    private final List<ToggleButton> allButtons;
    private final List<ToggleButton> listenersButtons;
    private final ToggleButton addMyoButton;

    public MyoStateListener(List<ToggleButton> allButtons, List<ToggleButton> listenersButtons, ToggleButton addMyoButton) {
        this.allButtons = allButtons;
        this.listenersButtons = listenersButtons;
        this.addMyoButton = addMyoButton;
    }


    public void changed(ObservableValue<? extends MyoState> observable, MyoState oldValue, MyoState newValue) {
        Platform.runLater(() -> {
            switch (newValue) {
                case MYO_NOT_FOUND:
                    this.addMyoButton.setDisable(false);
                    this.addMyoButton.setSelected(false);

                    this.listenersButtons.forEach(button -> {
                        button.setDisable(true);
                        button.setSelected(false);
                    });
                    break;
                case MYO_UNKNOWN:
                    this.addMyoButton.setDisable(false);
                    this.addMyoButton.setSelected(false);

                    this.listenersButtons.forEach(button -> {
                        button.setDisable(true);
                        button.setSelected(false);
                    });
                    break;
                case MYO_CONNECTED:
                    this.addMyoButton.setDisable(true);
                    this.addMyoButton.setSelected(true);

                    this.listenersButtons.forEach(button -> {
                        button.setDisable(false);
                        button.setSelected(false);
                    });
                    break;
                case MYO_SEARCHING:
                    this.addMyoButton.setDisable(true);
                    this.addMyoButton.setSelected(true);

                    this.listenersButtons.forEach(button -> {
                        button.setDisable(true);
                        button.setSelected(false);
                    });
                    break;
                case MYO_FOUND:
                    this.addMyoButton.setDisable(true);
                    this.addMyoButton.setSelected(true);

                    this.listenersButtons.forEach(button -> {
                        button.setDisable(false);
                        button.setSelected(false);
                    });
                    break;
            }
        });
    }
}