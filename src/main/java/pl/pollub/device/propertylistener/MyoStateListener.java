package pl.pollub.device.propertylistener;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import pl.pollub.type.MyoState;

import java.util.List;

public class MyoStateListener implements ChangeListener<MyoState> {
    private final StringProperty communicate;
    private final List<ToggleButton> allButtons;
    private final List<ToggleButton> listenersButtons;
    private final ToggleButton addMyoButton;

    public MyoStateListener(StringProperty communicate, List<ToggleButton> allButtons, List<ToggleButton> listenersButtons, ToggleButton addMyoButton) {
        this.communicate = communicate;
        this.allButtons = allButtons;
        this.listenersButtons = listenersButtons;
        this.addMyoButton = addMyoButton;
    }

    public void changed(ObservableValue<? extends MyoState> observable, MyoState oldValue, MyoState newValue) {
        Platform.runLater(() -> {
                    switch (newValue) {
                        case MYO_NOT_FOUND:
                        case MYO_UNKNOWN:
                            addMyoButton.setDisable(false);
                            addMyoButton.setSelected(false);

                            listenersButtons.forEach(button -> {
                                button.setDisable(true);
                                button.setSelected(false);
                            });
                            break;
                        case MYO_CONNECTED:
                        case MYO_FOUND:
                            addMyoButton.setDisable(true);
                            addMyoButton.setSelected(true);

                            listenersButtons.forEach(button -> {
                                button.setDisable(false);
                                button.setSelected(false);
                            });
                            break;
                        case MYO_SEARCHING:
                            addMyoButton.setDisable(true);
                            addMyoButton.setSelected(true);

                            listenersButtons.forEach(button -> {
                                button.setDisable(true);
                                button.setSelected(false);
                            });
                            break;
                    }
                    communicate.setValue(newValue.getCommunicateText());
                }
        );
    }
}