package pl.pollub.model.devicepropertieslisteners;

import com.thalmic.myo.Hub;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;

import java.util.List;

public class HubListener implements ChangeListener<Hub> {
    private final List<ToggleButton> allButtons;
    private final List<ToggleButton> listenersButtons;
    private final ToggleButton addMyoButton;

    public HubListener(List<ToggleButton> allButtons, List<ToggleButton> listenersButtons, ToggleButton addMyoButton) {
        this.allButtons = allButtons;
        this.listenersButtons = listenersButtons;
        this.addMyoButton = addMyoButton;
    }

    public void changed(ObservableValue<? extends Hub> observable, Hub oldValue, Hub newValue) {
        Platform.runLater(() -> {
                    if (newValue == null) {
                        allButtons.forEach(
                                button -> {
                                    button.setDisable(true);
                                    button.setSelected(false);
                                }
                        );
                    } else {
                        allButtons.forEach(
                                button -> {
                                    button.setDisable(false);
                                    button.setSelected(false);
                                }
                        );
                    }
                }
        );
    }
}