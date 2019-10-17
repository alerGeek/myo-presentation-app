package pl.pollub.model.listener.propertieslisteners;

import com.thalmic.myo.Hub;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;

import java.util.List;

public class HubListener implements ChangeListener<Hub> {
    public HubListener(List<ToggleButton> allButtons, List<ToggleButton> listenersButtons, ToggleButton addMyoButton) {
        this.allButtons = allButtons;
        this.listenersButtons = listenersButtons;
        this.addMyoButton = addMyoButton;
    }


    private final List<ToggleButton> allButtons;
    private final List<ToggleButton> listenersButtons;
    private final ToggleButton addMyoButton;

    public void changed(ObservableValue<? extends Hub> observable, Hub oldValue, Hub newValue) {
        Platform.runLater(() -> {
            if (newValue == null) {
                this.addMyoButton.setDisable(false);
//                this.listenersButtons.forEach(());
            } else {
//                this.allButtons.forEach(());
            }
        });
    }
}
